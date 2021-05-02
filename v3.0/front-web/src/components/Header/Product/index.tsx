import {Container} from './styles';
import {FiChevronsDown} from "react-icons/all";
import {useContext, useEffect, useState} from "react";
import './style.css'
import {Accordion, Card} from "react-bootstrap";
import {StatisticContext} from "../../../contexts/StatisticContext";

export function ProductHeader() {

    const [visibility, setVisibility] = useState(false);
    const [infoClassName, setInfoClassName] = useState<'more-info' | 'more-info-collapsed'>('more-info-collapsed');

    const [iconDirection, setIconDirection] = useState<'down' | 'up'>('down');

    const {fetchStatisticsFromCurrentCategory, statisticsFromCurrentCategory: statistics} = useContext(StatisticContext);

    useEffect(() => {
        fetchStatisticsFromCurrentCategory();
    }, [fetchStatisticsFromCurrentCategory])

    const toggleVisibility = () => {
        if (visibility){
            setVisibility(false);
            setInfoClassName('more-info-collapsed');
            setIconDirection('down');
        }else {
            setVisibility(true);
            setInfoClassName("more-info");
            setIconDirection('up');
        }
    }
    return (
        <Container className="card mt-5">
            <div className="card-body">
                <h4 className="card-title mb-3">{statistics.categoryName} </h4>

                <p className="card-text">Orçamento: <span>R$ {statistics.categoryBudget}</span></p>
                <p className="card-text">Valor Atual: <span>R$  {statistics.currentAmountTotal}</span></p>
                <p className="card-text">Valor Estipulado: <span>R$  {statistics.totalStipulated}</span></p>

                <div className={'card-info mb-3 ' + infoClassName} >
                    <Accordion defaultActiveKey="0">
                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="0" className={"card-content-header"}>
                                Informações Gerais
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="0">
                                <Card.Body>
                                    <p>{statistics.completed ? 'Lista concluída' : 'Lista não concluída'}</p>
                                    <p>{statistics.numberOfProducts} produtos nessa lista</p>
                                    <p>{statistics.numberOfProductsPurchased} produtos comprados</p>
                                    <p>{statistics.numberOfProductsNotPurchased} produtos não comprados</p>
                                    <p>{statistics.currentAmountSpent} reais gasto no momento.</p>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="1" className={"card-content-header"}>
                                Valor Economizado
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="1">
                                <Card.Body>
                                    <p>Você economizou {statistics.amountSaved} reais</p>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="2" className={"card-content-header"}>
                                Valor disponível para gastar
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="2">
                                <Card.Body>
                                    <p>Você ainda tem {statistics.availableToSpend} reais para gastar</p>
                                    <p>Caso compre todos os produtos, ficará com {statistics.availableToSpendIfBuyAll} reais</p>
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

