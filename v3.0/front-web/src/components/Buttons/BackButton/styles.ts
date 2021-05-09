import styled from "styled-components";
import {darken} from "polished";

export const Header = styled.div`
   max-width:750px;
   
  button{
    background: transparent;
    border: none;
    box-shadow: none!important;
    max-width:100px;
    font-size: 18px;
    display: flex;
    align-items: center;
    text-decoration: none;
    color: ${props => props.theme.colors.primary};
    transition: color 0.2s;
    &:hover{
      color: ${props => props && darken(0.1, props.theme.colors.primary)};
    }
    svg {
      margin-right: 4px;
    }
  }
`