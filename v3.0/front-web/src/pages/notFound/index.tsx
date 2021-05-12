import {NotFoundCardContainer} from '../../components/Card/styles';
import {GiRollingEnergy} from "react-icons/all";
import {Content} from './styles';

export default function NotFound() {
    return (
        <NotFoundCardContainer className={'container mt-5 p-5'}>
            <Content>
                <GiRollingEnergy color="white" size={78} className="spin"/>
                <span>404</span>
                <strong>Página não encontrada</strong>
            </Content>
        </NotFoundCardContainer>
    )
}