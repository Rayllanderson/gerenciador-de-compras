import {ProductCardBody} from "../styles";
import {CardAddon} from "../CardAddon";
import {FiCheck, FiX} from "react-icons/all";
import {ProductResponseBody} from "../../../interfaces/productInterface";
import {DeleteButton, EditButton} from "../../Buttons";
import {useContext} from "react";
import {VisibilityCardItemContext} from "../../../context/CardItemVisibilityContext";
import {ProductContext} from "../../../context/ProductContext";

interface Props {
    product: ProductResponseBody
}

export function CardItem({product}: Props) {
    const {deleteButtonIsVisible, editButtonIsVisible} = useContext(VisibilityCardItemContext);
    const {setToEdit, setToRemove} = useContext(ProductContext);
    return (
        <ProductCardBody>

            <div className={'addons'}>
                <CardAddon id={product.id} name={product.name}>
                    {editButtonIsVisible && <EditButton onClick={() => setToEdit(product)}/>}
                    {deleteButtonIsVisible && <DeleteButton onClick={() => setToRemove(product)}/>}
                </CardAddon>
            </div>

            <div className="body">
                <div className={'card-item'}>
                    <h5 className="card-title">{product.name}</h5>
                </div>

                <div className={'card-item'}>
                    {
                        product.purchased ? (
                            <p className={'card-value bought'}><FiCheck title={'Produto comprado'}/></p>
                        ) : (
                            <p className={'card-value non-bought'}><FiX title={'Produto ainda não comprado'}/></p>
                        )
                    }
                </div>

                <div className={'card-item'}>
                    <p className={'card-value'}>R$ {product.stipulatedPrice} </p>
                </div>

                <div className={'card-item'}>
                    {product.purchased ? (
                            <>
                                <div>
                                    <p className={'card-value text-center'}> R$ {product.spentPrice} </p>
                                </div>
                            </>
                        ) :
                        <p><FiX title={'Ainda não comprado'}/></p>
                    }
                </div>
            </div>
        </ProductCardBody>
    )
}

