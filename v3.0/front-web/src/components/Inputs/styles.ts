import styled from "styled-components";

export const CheckboxContainer = styled.div `

.form-check-input{
      border-radius: 5px;
      color: black;
      &:checked{
         background-color: ${props => props.theme.colors.primary}!important;
      }
`