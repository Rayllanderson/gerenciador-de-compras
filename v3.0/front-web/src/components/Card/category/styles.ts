import styled from 'styled-components'

export const CardContainer = styled.div `
    h5, p{
         color: ${props => props.theme.colors.text};
    }
    
     .card {
      cursor: pointer;
      background: ${props => props.theme.colors.backgroundSecondary}!important;
      border-radius: 5px;
      border: 0.1rem solid ${props => props.theme.colors.primary}!important;
    }
    
    .progress-bar {
      background: ${props => props.theme.colors.progressBar};
    }
    
    .form-check-input{
        border-radius: 5px;
        color: black;
        &:checked{
         background-color: ${props => props.theme.colors.primary}!important;
        }
    }
    
    .addons{
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
        align-items: center;
    }
`