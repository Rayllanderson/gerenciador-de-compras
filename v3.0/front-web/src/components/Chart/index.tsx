import React, {useContext} from "react";
import Chart from 'react-apexcharts';
import {ThemeContext} from "styled-components";
import {StatisticContext} from "../../contexts/StatisticContext";

const chartColors = {
    green: '#26e7a6',
    yellow: '#febc3b',
    blue: '#008ffb',
    pink: '#d830eb',
    purple: '#8b75d7',
    red: '#e83f5b'
}

export function PieChart() {

    const {colors} = useContext(ThemeContext);
    const {statistics} = useContext(StatisticContext);

    const mockData = {
        series: [statistics.numberOfProductsPurchased, statistics.numberOfProductsNotPurchased],
        labels: ['Comprados', 'Não Comprados']
    }

    const options = {
        chart: {
            foreColor: colors.text,
        },
        legend: {
            show: true,
        },
        dataLabels: {
            enabled: true,
        },
        fill: {
            colors: [colors.primary, chartColors.red]
        },
        plotOptions: {
            pie: {
                customScale: 0.9
            }
        },
        colors: [colors.primary, chartColors.red]
    }
    return (
        <Chart
            options={{...options, labels: mockData.labels}}
            series={mockData.series}
            type="pie"
            height="240"
        />
    );
}

export function ColumnChart() {
    const {title, colors: themeColors} = useContext(ThemeContext);
    const {statistics} = useContext(StatisticContext);
    const theme: 'dark' | 'light' = title === 'dark' ? 'dark' : 'light';

    const amountSavedColor = statistics.amountSaved > 0 ? chartColors.green : chartColors.red

    const colors = [chartColors.blue, chartColors.pink, amountSavedColor];

    const mockData = {
        series: [{
            name: 'R$',
            data: [statistics.currentAmountSpent, statistics.stipulatedValueFromBoughtProducts, statistics.amountSaved],
        }],
    }
    const options = {
        chart: {
            foreColor: themeColors.text
        },
        fill: {
            colors: colors,
            opacity: 1,
            type: 'solid',
        },
        grid: {
            show: true,
            borderColor: '#90A4AE',
        },
        plotOptions: {
            bar: {
                columnWidth: '50%',
                distributed: true,
                borderRadius: 4,
            }
        },
        dataLabels: {
            enabled: false
        },
        legend: {
            show: false
        },
        xaxis: {
            categories:
                [
                    ['Valor', 'Gasto'],
                    ['Valor', 'Estipulado'],
                    ['Valor', 'Economizado'],
                ],
            labels: {
                style: {
                    colors: colors,
                    fontSize: '12px'
                }
            }
        },
        tooltip: {
            theme: theme,
            followCursor: true,
        }
    }

    return (
        <Chart
            options={{...options}}
            series={mockData.series}
            type="bar"
            height="350"
        />
    );
}

export function HorizontalChart() {
    const {title, colors: themeColors} = useContext(ThemeContext);
    const {statistics} = useContext(StatisticContext);
    const theme: 'dark' | 'light' = title === 'dark' ? 'dark' : 'light';

    const amountSavedColor = statistics.amountSaved > 0 ? chartColors.green : chartColors.red
    const colors = [amountSavedColor, chartColors.yellow, chartColors.blue, chartColors.pink, chartColors.purple];

    const mockData = {
        series: [{
            name: 'R$',
            data: [statistics.amountSaved, statistics.amountToSpend, statistics.currentAmountSpent,
                statistics.currentAmountStipulated, statistics.currentAmountTotal],
        }],
    }
    const options = {
        chart: {
            foreColor: themeColors.text,
        },
        fill: {
            colors: colors,
            opacity: 1,
            type: 'solid',
        },
        grid: {
            show: true,
            borderColor: '#90A4AE',
        },
        plotOptions: {
            bar: {
                columnWidth: '50%',
                distributed: true,
                borderRadius: 4,
                horizontal: true,
            }
        },
        dataLabels: {
            enabled: false
        },
        legend: {
            show: false
        },
        xaxis: {
            categories:
                [
                    ['Valor', 'Economizado'],
                    ['Falta', 'Gastar'],
                    ['Valor', 'Gasto'],
                    ['Valor', 'Estipulado'],
                    ['Valor', 'Total'],
                ],
            labels: {
                colors: themeColors.text,
                style: {
                    fontSize: '12px'
                }
            },
        },
        tooltip: {
            theme: theme,
            followCursor: true,
        }
    }

    return (
        <Chart
            options={{...options}}
            series={mockData.series}
            type="bar"
            height="350"
        />
    );
}