import {StatisticCardContainer} from "../../components/Card/styles";
import {ColumnChart, HorizontalChart, PieChart} from "../../components/Chart";
import {PieChartContainer, BarChartContainer} from './styles';

export default function StatisticPage() {
    return (
        <div className={'container mt-5 '} style={{maxWidth: '750px'}}>
            <StatisticCardContainer >

                <PieChartContainer className={'pt-5 pb-2 container'} >
                    <h5 className={'pb-2'}> Quantidade de Produtos </h5>
                    <PieChart/>
                </PieChartContainer>

                <BarChartContainer className={'pt-5 pb-2 container'}>
                    <h5>Gráfico dos produtos comprados</h5>
                    <small>O gráfico abaixo representa apenas os produtos que já foram comprados</small>
                    <ColumnChart/>
                </BarChartContainer>

                <BarChartContainer className={'pt-5 pb-2 container'}>
                    <h5>Gráfico de todos os produtos</h5>
                    <small>O gráfico abaixo representa todos os produtos da lista selecionada</small>
                    <HorizontalChart/>
                </BarChartContainer>

            </StatisticCardContainer>
        </div>
    )
}