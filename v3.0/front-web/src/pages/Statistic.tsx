import {ColumnChart, HorizontalChart, PieChart} from "../components/Chart";
import {StatisticCardContainer} from "../components/Card/styles";

export function StatisticPage() {
    return (
        <div className={'container mt-5 '} style={{maxWidth: '750px'}}>
            <StatisticCardContainer>
                <div>
                    <PieChart/>
                </div>
                <div>
                    <ColumnChart/>
                </div>
                <div>
                    <HorizontalChart/>
                </div>
            </StatisticCardContainer>
        </div>
    )
}