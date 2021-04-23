import styled, {css} from "styled-components";

interface ContainerProps {
    isFocused: boolean,
    isFilled: boolean,
}

export const CheckboxContainer = styled.div`

.form-check-input{
      border-radius: 5px;
      color: black;
      &:checked{
         background-color: ${props => props.theme.colors.primary}!important;
      }
`

export const Container = styled.div<ContainerProps>`
     
      span {
        background-color:#ffff!important;;
        border: 2px solid #f8f8f2;
        border-right: 0 solid;
        transition: 0.2s;
        color: #6c757d;
      }
      
      input {
         border: 2px solid #f8f8f2;
         border-left: 0 solid;
      }
     
     ${props => props.isFocused && css`
            color: ${props1 => props1.theme.colors.primary};
            border-color: ${props1 => props1.theme.colors.primary};
            span{
                color: ${props1 => props1.theme.colors.primary};
                border-color: ${props1 => props1.theme.colors.primary};
            }
     `}
        
     ${props => props.isFilled && css`
        color: ${props1 => props1.theme.colors.primary};
         span{
                color: ${props1 => props1.theme.colors.primary};
            }
     `}   
        
      .input-group-text{
        font-size: 1.2rem;
      }
`