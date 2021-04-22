import {ProductCardBody} from "../styles";
import {CardAddon} from "../CardAddon";
import {FiCheck, FiX} from "react-icons/all";

interface Props {
    id: string,
    bought?: boolean;
}

export function CardItem({id, bought}: Props) {
    return (
        <ProductCardBody>

            <div className={'addons'}>
                <CardAddon id={id}/>
            </div>

            <div className="body">
                <div className={'card-item'}>
                    <h5 className="card-title">Placa de video</h5>
                </div>

                <div className={'card-item'}>
                    {
                        bought ? (
                            <p className={'card-value bought'}><FiCheck title={'Produto comprado'}/></p>
                        ) : (
                            <p className={'card-value non-bought'}><FiX title={'Produto ainda não comprado'}/></p>
                        )
                    }
                </div>

                <div className={'card-item'}>
                    <p className={'card-value'}>R$ 1250.00 </p>
                </div>

                <div className={'card-item'}>
                    {bought ? (
                            <>
                                <div>
                                    <p className={'card-value text-center'}> R$ 500.00 </p>
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

