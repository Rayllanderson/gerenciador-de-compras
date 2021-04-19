import {Container} from './styles';
import {FiChevronsDown} from "react-icons/all";
import {useState} from "react";
import './style.css'
import {Accordion, Card} from "react-bootstrap";

export function ProductHeader() {

    const categoryName = 'Nome da Lista'
    const [visibility, setVisibility] = useState(false);
    const [infoClassName, setInfoClassName] = useState<'more-info' | 'more-info-collapsed'>('more-info-collapsed');

    const [iconDirection, setIconDirection] = useState<'down' | 'up'>('down');

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
                <h4 className="card-title mb-3">{categoryName} </h4>

                <p className="card-text">Orçamento: <span>R$  1546.50</span></p>
                <p className="card-text">Valor Atual: <span>R$  1246.75</span></p>
                <p className="card-text">Valor Estipulado: <span>R$  1946.40</span></p>

                <div className={'card-info mb-3 ' + infoClassName} >
                    <Accordion defaultActiveKey="0">
                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="0" className={"card-content-header"}>
                                Informações Gerais
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="0">
                                <Card.Body>
                                    <p>10 produtos nessa lista</p>
                                    <p>7 produtos comprados</p>
                                    <p>3 produtos não comprados</p>
                                    <p>1250 reais gasto no momento.</p>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="1" className={"card-content-header"}>
                                Valor Economizado
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="1">
                                <Card.Body>
                                    <p>Você economizou 40 reais</p>
                                </Card.Body>
                            </Accordion.Collapse>
                        </Card>

                        <Card className={'card-content'}>
                            <Accordion.Toggle as={Card.Header} eventKey="2" className={"card-content-header"}>
                                Valor disponível para gastar
                            </Accordion.Toggle>
                            <Accordion.Collapse eventKey="2">
                                <Card.Body>
                                    <p>Você ainda tem 150 reais para gastar</p>
                                    <p>Caso compre todos os produtos, ficará com 50 reais</p>
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

