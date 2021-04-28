import styled from "styled-components";
import {rgba} from "polished";

interface SelectButtonsProps {
    show: boolean,
}

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

export const SelectButtonsContainer = styled.div<SelectButtonsProps> `
    
    display: ${props => props.show ? 'block' : 'none'};       
      
    
    background: ${props => rgba(props.theme.colors.backgroundSecondary, 0.9)};
    padding: 4px;
    padding-bottom: 12px;
    margin: 0 auto;
    border-radius: 12px;
    max-width: 750px;
    
    border: 1px solid ${props => props.theme.colors.primary};
    
    position: sticky;
    bottom: 0;
    z-index: 999;
    
    .buttons{
        display: flex;
        justify-content: center;
    }
`