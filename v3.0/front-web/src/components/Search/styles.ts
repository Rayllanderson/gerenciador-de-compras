import styled from "styled-components";

export const Container = styled.div `
    max-width: 750px;
    margin-top: 4rem;
   
    input{
        border: 2px solid ${props => props.theme.colors.backgroundSecondary}!important; 
    }
    div{
        height: 50px;
    }
    
    button {
        width: 6rem;
    }
    
    input:focus{
       border-color: ${props => props.theme.colors.primary}!important;
    }

`
