import {CategoryCard} from "./CategoryCard";
import {useContext, useEffect} from "react";
import {PaginationContext} from "../../../context/PaginationContext";
import CategoryController from "../../../controllers/categoryController";


export default function CategoryList() {
    const {pagination, getPage} = useContext(PaginationContext);

    useEffect(() => {
        getPage(new CategoryController());
    }, [getPage])
    return (
        <div className={"container"} style={{minHeight: '40vh'}}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>

                    {pagination.content.map((category) =>
                        <CategoryCard category={category} key={category.id} />
                    )}

                </div>
            </div>
        </div>
    )
}