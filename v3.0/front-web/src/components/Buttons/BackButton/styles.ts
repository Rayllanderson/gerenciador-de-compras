import styled from "styled-components";
import {darken} from "polished";

export const Header = styled.div`
   max-width:750px;
   
  a{
    font-size: 18px;
    display: flex;
    align-items: center;
    text-decoration: none;
    color: ${props => props.theme.colors.text};
    transition: color 0.2s;
    &:hover{
      color: ${props => props && darken(0.1, props.theme.colors.primary)};
    }
    svg {
      margin-right: 4px;
    }
  }
`