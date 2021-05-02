import styled from "styled-components";
import {darken} from "polished";

export const Container = styled.div`

    margin: 0.5rem auto;
    width: 20rem;

     a {
        text-decoration: none;
        transition: 0.3s;
         font-size: 1.1rem;
        margin-top: 2.4rem;
        color: ${props => props.theme.colors.primary};
    }

     a:hover {
        color: ${props => darken(0.1, props.theme.colors.primary)}!important;
    }

`