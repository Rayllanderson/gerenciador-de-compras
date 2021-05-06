import {CategoryCard} from "./CategoryCard";
import {useContext, useEffect} from "react";
import {PaginationContext} from "../../../contexts/PaginationContext";
import CategoryController from "../../../controllers/categoryController";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";


export default function CategoryList() {
    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(new CategoryController());
    }, [loadPage])

    const allProducts: CategoryResponseBody = {
        id: '',
        name: 'Todos os produtos',
        budget: '',
        completedPercentage: 50
    }

    return (
        <div className={"container"} style={{minHeight: '40vh'}}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>
                    {pagination.content.map((category: CategoryResponseBody | {}) =>
                        category &&
                        <CategoryCard category={category as CategoryResponseBody} key={JSON.stringify(category)}/>
                    )}

                </div>
            </div>
        </div>
    )
}