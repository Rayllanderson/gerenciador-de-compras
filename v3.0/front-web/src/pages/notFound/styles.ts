import styled from "styled-components";

export const Content = styled.div`
   margin-left: auto;
   margin-right: auto;
   span, strong{
     color: ${props => props.theme.colors.text};
   }
   span {
     margin-left: 10px;
     font-size: 40px;
   }
   strong {
     display:block;
     font-size: 32px;
     margin-left: 16px
   }
   .spin {
       fill: ${props => props.theme.colors.text};
       -webkit-animation: icon-spin 6s infinite linear;
       animation: icon-spin 6s infinite linear;
   }
   @-webkit-keyframes icon-spin {
       0% {
           -webkit-transform: rotate(0deg);
           transform: rotate(0deg);
       }
       100% {
           -webkit-transform: rotate(359deg);
           transform: rotate(359deg);
       }
   }
   @keyframes icon-spin {
       0% {
           -webkit-transform: rotate(0deg);
           transform: rotate(0deg);
       }
       100% {
           -webkit-transform: rotate(359deg);
           transform: rotate(359deg);
       }
   }
 `