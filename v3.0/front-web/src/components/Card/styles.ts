import styled from "styled-components";

export const AddonsContainer = styled.div`
     display: flex;
     flex-direction: row;
     justify-content: flex-end;
     align-items: center;
    
    .checkbox, button{
        animation: appearFromTop 0.5s;
    }
    
    .form-check-input{
      border-radius: 5px;
      color: black;
      &:checked{
         background-color: ${props => props.theme.colors.primary}!important;
      }
    }
 `

export const CardContainer = styled.div`
    h5, p{
       color: ${props => props.theme.colors.text};
    }
    
    .card {
      cursor: pointer;
      background: ${props => props.theme.colors.backgroundSecondary}!important;
      border-radius: 5px;
     /* border: 0.1rem solid ${props => props.theme.colors.primary}!important; */
    }
`

export const CategoryCardContainer = styled(CardContainer)`
  .progress-bar {
     background: ${props => props.theme.colors.progressBar};
  }
`

export const ProductCard = styled(CardContainer)`
      cursor: unset;
      padding: 10px;
      background: ${props => props.theme.colors.backgroundSecondary}!important;
`

export const ProductCardBody = styled.div`
    
     padding-top: 1.1rem;
     padding-bottom: 1.1rem;

    .body{
      display: flex;
      justify-content: space-between;
      align-items: center;
      width: 100%;
    }
    
    .addons{
      padding: 5px;
    }
    
   .card-item{
     max-width: 187.5px;
     width: 100%;
     display: flex;
     justify-content: center;
   }
   
   .bought{
     color: ${props => props.theme.title === 'dark' ? 'var(--green)' : 'var(--greenLight)'};
   }
   .non-bought{
     color: ${props => props.theme.title === 'dark' ? 'var(--red)' : 'var(--redLight)'};
   }
   
`

export const ProductCardHeader = styled.div`
   max-width: 750px;
   position: sticky;
   top: 0;
   z-index: 99;
   scroll-behavior: smooth;
   padding-left: 10px;
   padding-right: 10px;
   font-size: 1rem;
   display: flex;
   justify-content: space-between;
   margin-top: 1rem;
   margin-bottom: 1rem;
   width: 100%;
   height: 60px;
   border: 1px solid ${props => props.theme.colors.primary};
   border-radius: 5px;
   background: ${props => props.theme.colors.background};
  
   p{
     max-width: 187.5px;
     width: 100%;
     height: 100%;
     display: flex;
     justify-content: center;
     align-items: center;
     color: ${props => props.theme.colors.primary};
   }
`