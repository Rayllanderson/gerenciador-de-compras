import {CardItem} from "./CardItem";
import {CardHeader} from "./CardHeader";
import {ProductCardBody, ProductCard} from "../styles";

export default function ProductList() {
    return (
        <div className={"container"} style={{maxWidth: 750, margin: '0 auto'}}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <CardHeader/>

                <div className="row row-cols-1 ">
                    <div className="col">
                        <ProductCard className="card">
                            <CardItem id={'1'} bought/>
                            <CardItem id={'2'} bought/>
                            <CardItem id={'3'}/>
                            <CardItem id={'3'} bought/>
                            <CardItem id={'3'}/>
                            <CardItem id={'3'}/>
                            <CardItem id={'3'}/>
                            <CardItem id={'3'} bought/>
                            <CardItem id={'3'} bought/>
                        </ProductCard>
                    </div>
                </div>
            </div>
        </div>
    )
}