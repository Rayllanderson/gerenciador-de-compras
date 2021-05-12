import {ProductCardHeader} from '../styles'

export function CardHeader (){
    return (
        <ProductCardHeader>
            <p className={"card-header-item"}>Nome</p>
            <p className={"card-header-item"}>Comprado</p>
            <p className={"card-header-item"}>Valor</p>
            <p className={"card-header-item"}>Valor Pago</p>
        </ProductCardHeader>
    )
}