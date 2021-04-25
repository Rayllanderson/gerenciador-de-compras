import styled from "styled-components";

export const Container = styled.div `
    max-width: 750px;
    display: flex;
    justify-content: center;
    
    .page-link{
        background: ${props => props.theme.colors.backgroundSecondary}!important;
        border-color: ${props => props.theme.colors.primary}!important;
        color: ${props => props.theme.colors.text}!important;
    }
    
    .active > span{
         background: ${props => props.theme.colors.primary}!important;
         color: var(--white)!important;
    }
`