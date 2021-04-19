import {ProductCard} from "./ProductCard";

export default function ProductList(){
    return (
        <div className={"container"}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>
                    <ProductCard id={'1'}/>
                    <ProductCard id={'2'}/>
                </div>
            </div>
        </div>
    )
}