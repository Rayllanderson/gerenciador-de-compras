import {StatisticCardContainer} from "../../components/Card/styles";
import {ColumnChart, HorizontalChart, PieChart} from "../../components/Chart";
import {PieChartContainer, BarChartContainer, SelectContainer} from './styles';
import {FiPieChart} from "react-icons/all";

export default function StatisticPage() {
    return (
        <div className={'container mt-5 '} style={{maxWidth: '750px'}}>
            <StatisticCardContainer>
                <h4 className={'pt-4'}> <FiPieChart/> Estatísticas </h4>
                <div className={'pt-3 container'}>
                    <h5>Selecione a lista desejada</h5>
                    <SelectContainer>
                        <select className={'form-select mt-3'}>
                            <option>Todas as listas</option>
                            <option>Lista 2</option>
                        </select>
                    </SelectContainer>
                </div>

                <PieChartContainer className={'pt-5 pb-2 container'}>
                    <h5> Quantidade de Produtos </h5>
                    <PieChart/>
                </PieChartContainer>

                <BarChartContainer className={'pt-5 pb-2 container'}>
                    <h5>Gráfico de produtos comprados</h5>
                    <small>O gráfico abaixo representa apenas os produtos que já foram comprados</small>
                    <ColumnChart/>
                </BarChartContainer>

                <BarChartContainer className={'pt-5 pb-5 container'}>
                    <h5>Gráfico de todos os produtos</h5>
                    <small>O gráfico abaixo representa todos os produtos da lista selecionada</small>
                    <HorizontalChart/>
                </BarChartContainer>

            </StatisticCardContainer>
        </div>
    )
}