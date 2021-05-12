import styled from "styled-components";

export const Container = styled.div `
    width: 100%;
    background: ${props => props.theme.colors.backgroundSecondary};
    color: ${props => props.theme.colors.primary};
    box-shadow: 0px 1px 10px 1px rgba(0,0,0,0.08);   
    
   div{
    align-items: center;
    }
      
`