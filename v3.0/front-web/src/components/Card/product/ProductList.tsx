import {CardItem} from "./CardItem";
import {CardHeader} from "./CardHeader";
import {ProductCard} from "../styles";
import {useContext, useEffect} from "react";
import {PaginationContext} from "../../../contexts/PaginationContext";
import ProductController from "../../../controllers/productController";
import {ProductResponseBody} from "../../../interfaces/productInterface";


interface Props {
    categoryId: string
}

export default function ProductList({categoryId}: Props) {

    const {pagination, loadPage} = useContext(PaginationContext);

    useEffect(() => {
        loadPage(new ProductController(categoryId));
    }, [loadPage, categoryId])
    return (
        <div className={"container"} style={{maxWidth: 750, margin: '0 auto'}}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <CardHeader/>

                <div className="row row-cols-1 ">
                    <div className="col">
                        <ProductCard className="card">
                            {pagination.content.map((product:ProductResponseBody | {}) =>
                                product &&
                                <CardItem product={product as ProductResponseBody} key={JSON.stringify(product)}/>
                            )}
                        </ProductCard>
                    </div>
                </div>
            </div>
        </div>
    )
}