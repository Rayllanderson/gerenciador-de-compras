import styled from "styled-components";
import {chartColors} from "../../utils/colorsUtil";

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

export const SubtitleContainer = styled.div `
    text-align: left;
    padding: 5px;
    line-height: 25px;
    small {
        display: block;
        opacity: 0.9;
    }
    .green {
        color: ${chartColors.green};
    }
    
     .yellow {
        color: ${chartColors.yellow};
    }
    
     .blue {
        color: ${chartColors.blue};
    }
    
    .pink {
        color: ${chartColors.pink};
    }
    
    .purple {
        color: ${chartColors.purple};
    }
    
`