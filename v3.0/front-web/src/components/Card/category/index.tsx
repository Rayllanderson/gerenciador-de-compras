import {CategoryCard} from "./categoryCard";
import {useContext, useEffect} from "react";
import {CategoryContext} from "../../../context/CategoryContext";


export default function CategoryList() {
    const {getCategoriesPageable, pagination} = useContext(CategoryContext);

    useEffect(() => {
        getCategoriesPageable();
    }, [])
    return (
        <div className={"container"}>
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