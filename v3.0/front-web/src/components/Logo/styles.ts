import styled from "styled-components";

export const Container = styled.div`

    margin: 6rem auto 0;
    background: ${props => props.theme.colors.backgroundSecondary};
    color: var(--white);
    border-radius: 10px;
    width: 13rem;
    padding: 2rem;
    
    svg{
     fill:  ${props => props.theme.colors.textSecondary};
    }

 `