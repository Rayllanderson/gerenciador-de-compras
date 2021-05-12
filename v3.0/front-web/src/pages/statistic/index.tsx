import {StatisticCardContainer} from "../../components/Card/styles";
import {ColumnChart, HorizontalChart, PieChart} from "../../components/Chart";
import {PieChartContainer, BarChartContainer, SelectContainer, SubtitleContainer} from './styles';
import {FiPieChart} from "react-icons/all";
import {useContext, useEffect} from "react";
import {CategoryContext} from "../../contexts/CategoryContext";
import {StatisticContext} from "../../contexts/StatisticContext";

export default function StatisticPage() {

    const {loadCategoriesNonPageable, categories} = useContext(CategoryContext);
    const {handleSelectChange, fetchStatisticsFromAllProducts} = useContext(StatisticContext);

    useEffect(() => {
        loadCategoriesNonPageable();
        fetchStatisticsFromAllProducts();
    }, [loadCategoriesNonPageable, fetchStatisticsFromAllProducts])

    return (
        <div className={'container mt-5'} style={{maxWidth: '750px'}}>
            <StatisticCardContainer className={'card'}>
                <h4 className={'pt-4'}><FiPieChart/> Estatísticas </h4>
                <div className={'pt-3 container'}>
                    <h5>Selecione a lista desejada</h5>
                    <SelectContainer>
                        <select className={'form-select mt-3'} onChange={handleSelectChange} defaultValue={'all'}>
                            <option value={'all'}>Todas as listas</option>
                            {categories.map(category =>
                                <option value={category.id} key={category.id}>{category.name}</option>
                            )}
                        </select>
                    </SelectContainer>
                </div>

                <PieChartContainer className={'pt-5 pb-2 container'}>
                    <h5> Quantidade de Produtos </h5>
                    <PieChart/>
                </PieChartContainer>

                <BarChartContainer className={'pt-5 pb-2 container'}>
                    <h5>Gráfico de produtos comprados</h5>
                    <p>O gráfico abaixo representa apenas os produtos que <strong>já foram comprados </strong></p>
                    <ColumnChart/>
                </BarChartContainer>

                <BarChartContainer className={'pt-5 pb-5 container'}>
                    <h5>Gráfico de todos os produtos</h5>
                    <p>O gráfico abaixo representa todos os produtos da lista selecionada</p>
                    <HorizontalChart/>
                    <SubtitleContainer>
                        <p>Legenda:</p>
                        <small><span className={'green'}>Economizado: </span>
                            Subtração do valor estipulado com o valor pago do produto que foi comprado.
                        </small>
                        <small> <span className={'yellow'}>Falta Gastar: </span>
                            Soma dos valores estipulados dos produtos que ainda não foram comprados.
                        </small>

                        <small><span className={'blue'}>Valor Pago: </span>
                            Soma dos valores pagos dos produtos que já foram comprados.
                        </small>

                        <small><span className={'pink'}>Valor Estipulado: </span>
                            Soma de todos os valores estipulados dos produtos, mesmo os que já foram comprados.
                        </small>

                        <small><span className={'purple'}>Valor Total: </span>
                            Soma dos valores dos produtos comprados e não comprados. Caso o produto já tenha sido
                            comprado, levará em consideração seu valor pago, caso não, levará o valor
                            estipulado.
                        </small>
                    </SubtitleContainer>
                </BarChartContainer>

            </StatisticCardContainer>
        </div>
    )
}