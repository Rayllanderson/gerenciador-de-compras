import {createGlobalStyle} from 'styled-components';
import {darken} from "polished";

export default createGlobalStyle`

  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    outline: 0;
  }
  body {
    background: ${props => props.theme.colors.background};
    color: ${props => props.theme.colors.text};
    -webkit-font-smoothing: antialiased;
    min-height: 100vh;
  }
  body, input, button {
    font-family: 'Fira Code', monospace;
    font-size: 16px;
  }
  h1, h2, h3, h4, h5, h6, strong {
    font-weight: 500;
  }
  button {
    cursor: pointer;
     box-shadow: 0px 7px 10px 1px rgba(0,0,0,0.05)!important;
  }
  
  body {
    zoom: 115%
  }
  
  a {
     text-decoration: none;
  }
  
  input, select{
     border-color: ${props => props.theme.colors.backgroundSecondary}!important;
     box-shadow: 0px 7px 10px 1px rgba(0,0,0,0.05);
     background-color: ${props => props.theme.colors.backgroundSecondary}!important;
     color: ${props => props.theme.colors.text}!important;
  }
  
  input:focus,
  select:focus{
    border-color: ${props => props.theme.colors.primary}!important;
    box-shadow: 0px 7px 10px 1px rgba(0,0,0,0.05)!important;
  }
  
  .card{
     box-shadow: 0px 7px 10px 3px rgba(0,0,0,0.08);
     border: none!important;
  }
  .button {
    color: white;
    transition: 0.3s;
    font-weight: 500;
    &:active{
        transform: scale(0.98);
    }
    
    &: focus{
         transition: 0.3s;
    }
  }
  
  .button-primary, .link-primary{
     background: ${props => props.theme.colors.primary}!important;
     color: ${props => props.theme.colors.background}!important;
     &:hover{
         background: ${props => props && darken(0.1, props.theme.colors.primary)}!important;
         color: white!important;
     }
     
     &:active{
         background: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
     }
     
     &:focus{
          border-color: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
  }
  
  .link {
    color: ${props => props.theme.colors.primary};
    transition: color 0.2s;
    &:hover{
      color: ${props => props && darken(0.1, props.theme.colors.primary)};
    }
  }
  
  .transparent{
     background: transparent!important;
     color: ${props => props.theme.colors.primary}!important;
     border: 1px solid ${props => props.theme.colors.primary};
     &:hover{
         background:  ${props => props.theme.colors.primary}!important;
     }
  }
  
    .loader {
        animation-name: loader-delay;
        animation-delay: 0ms;
        animation-duration: 750ms;
    }

    @keyframes loader-delay {
        from {
            opacity: 0.0;
        }
        to {
            opacity: 1.0;
        }
    }
  
  .hide{
    display: none;
  }
  
  .transition-2{
    transition: all .2s easy;
  }
  
  .transition-3{
    transition: all .2s easy;
  }
  
  .appearFromRight{
    animation: appearFromRight 1s;
   }
   
   .appearFromLeft{
    animation: appearFromLeft 1s;
  }
  
  .appearFromTop{
    animation: appearFromTop 1s;
  }
  
  .appearFromBottom{
    animation: appearFromBottom 1s;
  }  
  
  .appearSmoothly{
    animation: appearSmoothly 1s;
  }
    
  :root {
    --black: #21222c;
    --blackSecondary: #414558;
    --blackTernary: #a7abbe;
    --blackLight: hsla(230,15%,15%,5%);
    --white: #f8f8f2;
    --whiteSecondary: #fff;
    --whiteLight: hsla(60,30%,96%,5%);
    --cyan: #80ffea;
    --cyanSecondary: #00b4d8;
    --cyanLight: hsla(170,100%,75%,5%);
    --green: #04d361;
    --greenLight: #21B534;
    --greenSecondary: #d0ffcc;
    --orange: #ffca80;
    --orangeSecondary: #ffeacc;
    --orangeLight: hsla(35,100%,75%,5%);
    --pink: #ff80bf;
    --pinkSecondary: #ffcce6;
    --pinkLight: hsla(330,100%,75%,5%);
    --purple: #8257e6;
    --purpleSecondary: #d5ccff;
    --red: #e83f5b;
    --redSecondary: #ffd5cc;
    --redLight: #ff5a5f;
    --yellow: #ffff80;
    --yellowSecondary: #ffc;
    --gradientDegree: 135deg;
    --accentColor: var(--purple);
    --purple-red: linear-gradient(var(--gradientDegree),var(--purple),var(--red));
    --cyan-green: linear-gradient(var(--gradientDegree),var(--cyan),var(--green));
    --cyan-pink: linear-gradient(var(--gradientDegree),var(--cyan),var(--pink));
    --red-purple: linear-gradient(var(--gradientDegree),var(--redLight),var(--color-purple));
    
    
    --color-background: #121214;
    --color-orange: #fd951f;
    --color-yellow: #f7df1e;
    --color-purple: #8257e6;
    --color-purple-hover: #9466ff;
    --color-secondary: #e1e1e6;
    --color-shape: #202024;
    --color-icons: #41414d;
    --color-black: #0d0d0f;
    --color-white: #fff
  }
    
  @keyframes appearFromLeft {
     from {
        opacity: 0;
        transform: translateX(-50px);
     }
     to {
        opacity: 1;
        transform: translateX(0);
     }
  }
  
    @keyframes appearSmoothly {
     from {
        opacity: 0;
     }
     to {
        opacity: 1;
     }
  }
    
  @keyframes appearFromRight {
     from {
            opacity: 0;
            transform: translateX(50px);
        }
        to {
            opacity: 1;
            transform: translateX(0);
        }
    }
    
    @keyframes appearFromTop {
        from {
            opacity: 0;
            transform: translateY(-50px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
    @keyframes appearFromBottom {
        from {
            opacity: 0;
            transform: translateY(50px);
        }
        to {
            opacity: 1;
            transform: translateY(0);
        }
    }
    
  @media(max-width: 768px) {
    body {
        zoom: 100%!important;
    }
  }
`;