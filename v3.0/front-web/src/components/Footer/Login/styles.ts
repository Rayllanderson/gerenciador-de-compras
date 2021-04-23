import styled from "styled-components";

export const Container = styled.div`

    margin: 0.5rem auto;
    width: 20rem;

     a {
        text-decoration: none;
        transition: 0.3s;
         font-size: 1.1rem;
        margin-top: 2.4rem;
        color: ${props => props.theme.colors.text};
    }

     a:hover {
        color: ${props => props.theme.colors.primary}!important;
    }

`