import styled, {css} from "styled-components";

interface ContainerProps {
    isFocused: boolean,
    isFilled: boolean,
}

export const CheckboxContainer = styled.div`
    
.form-check-input{
      border-color:  ${props => props.theme.colors.primary}!important;
      border-radius: 5px;
      color: black;
      &:checked{
         background-color: ${props => props.theme.colors.primary}!important;
      }
`

export const InputWithIconContainer = styled.div<ContainerProps>`
      box-shadow: 0px 7px 10px 1px rgba(0,0,0,0.05);
      border-radius: 5px;
      
      span {
        background-color:${props => props.theme.colors.backgroundSecondary}!important;
        border: 2px solid ${props => props.theme.colors.backgroundSecondary};
        border-right: 0 solid;
        transition: 0.2s;
        color: #6c757d;
      }
      
      input {
         border: 2px solid ${props => props.theme.colors.backgroundSecondary}!important;
         border-left: 0 solid!important;
         box-shadow: none!important;
      }
     
     ${props => props.isFocused && css`
            color: ${props1 => props1.theme.colors.primary}!important;
            border-color: ${props1 => props1.theme.colors.primary}!important;
            
            input:focus{
                box-shadow: none!important;
            }
            span{
                color: ${props1 => props1.theme.colors.primary}!important;
                border-color: ${props1 => props1.theme.colors.primary}!important;
            }
     `}
        
     ${props => props.isFilled && css`
        color: ${props1 => props1.theme.colors.primary}!important;
         span{
                color: ${props1 => props1.theme.colors.primary}!important;
            }
     `}   
        
      .input-group-text{
        font-size: 1.2rem;
      }
`

export const SearchInput = styled.input `
    border: 2px solid ${props => props.theme.colors.backgroundSecondary}!important; 
    &:focus{
      border-color: ${props => props.theme.colors.primary}!important;
    }
`

export const InputPasswordContainer = styled.div `
    span{
        background: ${props => props.theme.colors.backgroundSecondary};
        color: ${props => props.theme.colors.primary}!important;
        border: none;
        cursor: pointer;
        transition: .2s;
    }
`