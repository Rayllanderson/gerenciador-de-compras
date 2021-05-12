import {CardItem} from "./CardItem";
import {CardHeader} from "./CardHeader";
import {ProductCard} from "../styles";
import {useContext, useEffect} from "react";
import {PaginationContext} from "../../../contexts/PaginationContext";
import ProductController from "../../../controllers/productController";
import {ProductResponseBody} from "../../../interfaces/productInterface";
import {EmptyMessage} from '../../Text/styles';
import {LoadingContext} from "../../../contexts/LoadingContex";
import {ProductListLoader} from "../../Loader/product";


interface Props {
    categoryId: string
}

export default function ProductList({categoryId}: Props) {

    const {pagination, loadPage} = useContext(PaginationContext);
    const {isLoading} = useContext(LoadingContext);
    const loaderLength = [1, 2, 3, 4, 5, 6];

    useEffect(() => {
        loadPage(new ProductController(categoryId));
    }, [loadPage, categoryId])

    return (
        <div className={"container"} style={{maxWidth: 750, margin: '0 auto'}}>
            <div className={'appearFromBottom'}>

                <CardHeader/>

                <div className="row row-cols-1 " style={{minHeight: '40vh'}}>
                    <div className="col">
                        <ProductCard className="card">
                            {
                                pagination.content.length === 0 ?
                                    <EmptyMessage className={'container'}> Nenhum produto encontrado </EmptyMessage>
                                    :
                                    isLoading ? loaderLength.map(loader => <ProductListLoader key={loader}/>) :
                                    pagination.content.map((product: ProductResponseBody | {}) =>
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