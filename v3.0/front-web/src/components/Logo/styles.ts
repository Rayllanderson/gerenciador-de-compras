import styled from "styled-components";

export const Container = styled.div`

    margin: 6rem auto 0;
    background: ${props => props.theme.colors.backgroundSecondary};
    color: ${props => props.theme.colors.primary};
    border-radius: 10px;
    width: 13rem;
    padding: 2rem;
    box-shadow: 0px 7px 10px 1px rgba(0,0,0,0.05);
    
    svg{
     fill:  ${props => props.theme.colors.textSecondary};
    }

 `