import {CategoryCard} from "./CategoryCard";
import {useContext, useEffect} from "react";
import {PaginationContext} from "../../../contexts/PaginationContext";
import CategoryController from "../../../controllers/categoryController";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {CategoryCardLoader} from "../../Loader/card/category";
import {LoadingContext} from "../../../contexts/LoadingContex";
import {EmptyMessage} from '../../Text/styles';


export default function CategoryList() {
    const {pagination, loadPage} = useContext(PaginationContext);
    const {isLoading} = useContext(LoadingContext);
    const loaderLength  = [1, 2, 3, 4, 5, 6];
    useEffect(() => {
        loadPage(new CategoryController());
    }, [loadPage])

    return (
        <div className={"container"} style={{minHeight: '40vh'}}>
            <div style={{animation: 'appearFromBottom 1s'}}>
                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>
                    {isLoading ? loaderLength.map((loader) => <CategoryCardLoader key={loader}/>) : (
                        pagination.content.length === 0 ? <EmptyMessage className={'container'}> Nenhuma lista encontrada </EmptyMessage> :
                        pagination.content.map((category: CategoryResponseBody | {}) =>
                            category &&
                            <CategoryCard category={category as CategoryResponseBody} key={JSON.stringify(category)}/>
                        )
                    )}
                </div>
            </div>
        </div>
    )
}