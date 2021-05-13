import {Container} from './styles';
import {FiChevronsDown} from "react-icons/all";
import {useContext, useEffect, useState} from "react";
import './style.css'
import {Accordion, Card} from "react-bootstrap";
import {StatisticContext} from "../../../contexts/StatisticContext";
import {
    AmountSavedText,
    AvailableToSpendIfBuyAllText,
    AvailableToSpendText,
    NumberOfProductsNotPurchasedText,
    NumberOfProductsPurchasedText,
    NumberOfProductsText
} from "../../Text/Statistic";

interface Props {
    fetchStatisticFunction: () => void;
}

export function ProductHeader({fetchStatisticFunction}: Props) {

    const [visibility, setVisibility] = useState(false);
    const [infoClassName, setInfoClassName] = useState<'more-info' | 'more-info-collapsed'>('more-info-collapsed');

    const [iconDirection, setIconDirection] = useState<'down' | 'up'>('down');

    const {statistics} = useContext(StatisticContext);

    useEffect(() => {
        fetchStatisticFunction();
    }, [fetchStatisticFunction])

    const toggleVisibility = () => {
        if (visibility) {
            setVisibility(false);
            setInfoClassName('more-info-collapsed');
            setIconDirection('down');
        } else {
            setVisibility(true);
            setInfoClassName("more-info");
            setIconDirection('up');
        }
    }
    return (
        <Container className="card mt-5 appearSmoothly">
            <div className="card-body">
                <h4 className="card-title mb-3">{statistics.categoryName} </h4>

                <div className={'mb-3'}>
                    <p className="card-text">Orçamento: <span>R$ {statistics.categoryBudget}</span></p>
                    <p className="card-text">Valor Atual: <span>R$ {statistics.currentAmountTotal}</span></p>
                    <p className="card-text">Valor Estipulado: <span>R$ {statistics.totalStipulated}</span></p>
                </div>

                <div className={'card-info mb-3 ' + infoClassName}>
                    <Accordion defaultActiveKey="0">
                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="0" className={"card-content-header"}>
                                Informações Gerais
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="0">
                                <Card.Body className={'center-left'}>
                                    <NumberOfProductsText value={statistics.numberOfProducts}/>
                                    <NumberOfProductsPurchasedText value={statistics.numberOfProductsPurchased}/>
                                    <NumberOfProductsNotPurchasedText value={statistics.numberOfProductsNotPurchased}/>
                                    <p> R$ {statistics.currentAmountSpent} gasto no momento</p>
                                    <p> Falta gastar R$ {statistics.amountToSpend}</p>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content text-center'}>
                            <Accordion.Toggle as={Card.Header} eventKey="1" className={"card-content-header"}>
                                Valor Economizado
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="1">
                                <Card.Body>
                                    <AmountSavedText value={statistics.amountSaved}/>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="2" className={"card-content-header"}>
                                Valor disponível para gastar
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="2">
                                <Card.Body className={'center-left'}>
                                    {statistics.categoryBudget !== 0 ?
                                        <>
                                            <AvailableToSpendText value={statistics.availableToSpend}/>

                                            {!statistics.completed &&
                                            <AvailableToSpendIfBuyAllText value={statistics.availableToSpendIfBuyAll}/>
                                            }
                                        </>
                                        :
                                        <p>Sua lista não possui orçamento. Adicione um pra ver algo aqui.</p>
                                    }
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>
                    </Accordion>
                </div>

                <div>
                    <FiChevronsDown className={`icon icon-${iconDirection}`} size={23} title={'Ver mais informações'}
                                    onClick={toggleVisibility}/>
                </div>
            </div>
        </Container>
    )
}

