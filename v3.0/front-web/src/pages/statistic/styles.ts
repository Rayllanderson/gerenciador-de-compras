import styled from "styled-components";

export const PieChartContainer = styled.div `
    max-width: 450px;
`

export const BarChartContainer = styled.div `
    max-width: 750px;
    text-align: center;
`

export const SelectContainer = styled.div `
    max-width: 350px;
    margin: 0 auto;
    select{
        border: 1px solid ${props => props.theme.colors.primary}!important;    
    }
`