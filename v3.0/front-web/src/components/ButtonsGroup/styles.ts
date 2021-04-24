import styled from "styled-components";
import {rgba} from "polished";

export const Container = styled.div `
    max-width: 750px;
    margin-top: 1.5rem;
    
    .group-buttons {
        display: flex;
        justify-content: flex-end; 
    }
    
    .button-dropdown {
        &:after {
        display: none;
      }
    }
`

export const SelectButtonsContainer = styled.div `
 
    background: ${props => props && rgba(props.theme.colors.backgroundSecondary, 0.2)}!important;
    padding: 12px;
    margin: 0 auto;
    border-radius: 12px;
    max-width: 750px;
    
    border: 1px solid ${props => props.theme.colors.progressBar};
    position: fixed;
    bottom: 0;
    left: 50%;
    transform: translateX(-50%);
    
    .buttons{
        display: flex;
        justify-content: center;
    }
    
    .close-card{
        background: transparent;
        border: none;
        color: ${props => props.theme.colors.primary};
        cursor: pointer;
    }
`