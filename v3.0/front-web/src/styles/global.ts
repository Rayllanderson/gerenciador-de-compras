import {createGlobalStyle} from 'styled-components';
import {darken, rgba} from "polished";

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
     
         /*
        background-color: ${props => props.theme.colors.backgroundSecondary}!important;
        color: ${props => props.theme.colors.text}!important;*/
       /* border: 0.5px solid ${props => props.theme.colors.primary}!important; */
        /*   box-shadow: rgba(17, 17, 26, 0.1) 0px 4px 16px, rgba(17, 17, 26, 0.05) 0px 8px 32px!important;*/
        /* box-shadow: ${props => props && rgba(props.theme.colors.primary, 0.15)} 0px 48px 100px 0px!important;*/
        /*border: 0.5px solid ${props => props.theme.colors.primary}!important;*/
        /* ${props => props.theme.title === 'light' ?
        `border: 0.5px solid ${props.theme.colors.primary}!important` :
        `;`}*/
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
  
  .button-primary{
     background: ${props => props.theme.colors.primary}!important;
     color: ${props => props.theme.colors.background}!important;
     &:hover{
         background: ${props => props && darken(0.1, props.theme.colors.primary)}!important;
          color: ${props => props.theme.colors.background}!important;
     }
     
     &:active{
         background: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
     }
     
     &:focus{
          border-color: ${props => props && darken(0.12, props.theme.colors.primary)}!important;
          box-shadow: inset 0 1px 1px rgba(0, 0, 0, 0.025)!important;
     }
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
    --cyanSecondary: #ccfff6;
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
    --purpleLight: hsl(250,100%,75%,5%);
    --red: #e83f5b;
    --redSecondary: #ffd5cc;
    --redLight: #ff5a5f;
    --yellow: #ffff80;
    --yellowSecondary: #ffc;
    --yellowLight: hsla(60,100%,75%,5%);
    --cyan-transparent: rgba(128,255,234,0.1);
    --green-transparent: rgba(138,255,128,0.1);
    --orange-transparent: rgba(255,202,128,0.1);
    --pink-transparent: rgba(255,128,191,0.1);
    --purple-transparent: rgba(149,128,255,0.1);
    --red-transparent: rgba(255,149,128,0.1);
    --yellow-transparent: rgba(255,255,128,0.1);
    --disabled: #6c7393;
    --gradientDegree: 135deg;
    --glowColor: hsla(0,0%,100%,0.25);
    --cyan-100: rgba(128,255,234,0.1);
    --green-100: rgba(138,255,128,0.1);
    --orange-100: rgba(255,202,128,0.1);
    --pink-100: rgba(255,128,191,0.1);
    --purple-100: rgba(149,128,255,0.1);
    --red-100: rgba(255, 87, 87,0.1);
    --yellow-100: rgba(255,255,128,0.1);
    --cyan-200: rgba(128,255,234,0.2);
    --green-200: rgba(138,255,128,0.2);
    --orange-200: rgba(255,202,128,0.2);
    --pink-200: rgba(255,128,191,0.2);
    --purple-200: rgba(149,128,255,0.2);
    --red-200: rgba(255, 87, 87,0.2);
    --yellow-200: rgba(255,255,128,0.2);
    --cyan-300: rgba(128,255,234,0.3);
    --green-300: rgba(138,255,128,0.3);
    --orange-300: rgba(255,202,128,0.3);
    --pink-300: rgba(255,128,191,0.3);
    --purple-300: rgba(149,128,255,0.3);
    --red-300: rgba(255, 87, 87,0.3);
    --yellow-300: rgba(255,255,128,0.3);
    --cyan-400: rgba(128,255,234,0.4);
    --green-400: rgba(138,255,128,0.4);
    --orange-400: rgba(255,202,128,0.4);
    --pink-400: rgba(255,128,191,0.4);
    --purple-400: rgba(149,128,255,0.4);
    --red-400: rgba(255, 87, 87,0.4);
    --yellow-400: rgba(255,255,128,0.4);
    --cyan-500: rgba(128,255,234,0.5);
    --green-500: rgba(138,255,128,0.5);
    --orange-500: rgba(255,202,128,0.5);
    --pink-500: rgba(255,128,191,0.5);
    --purple-500: rgba(149,128,255,0.5);
    --red-500: rgba(255, 87, 87,0.5);
    --yellow-500: rgba(255,255,128,0.5);
    --cyan-600: rgba(128,255,234,0.6);
    --green-600: rgba(138,255,128,0.6);
    --orange-600: rgba(255,202,128,0.6);
    --pink-600: rgba(255,128,191,0.6);
    --purple-600: rgba(149,128,255,0.6);
    --red-600: rgba(255, 87, 87,0.6);
    --yellow-600: rgba(255,255,128,0.6);
    --cyan-700: rgba(128,255,234,0.7);
    --green-700: rgba(138,255,128,0.7);
    --orange-700: rgba(255,202,128,0.7);
    --pink-700: rgba(255,128,191,0.7);
    --purple-700: rgba(149,128,255,0.7);
    --red-700: rgba(255, 87, 87,0.7);
    --yellow-700: rgba(255,255,128,0.7);
    --cyan-800: rgba(128,255,234,0.8);
    --green-800: rgba(138,255,128,0.8);
    --orange-800: rgba(255,202,128,0.8);
    --pink-800: rgba(255,128,191,0.8);
    --purple-800: rgba(149,128,255,0.8);
    --red-800: rgba(255, 87, 87,0.8);
    --yellow-800: rgba(255,255,128,0.8);
    --cyan-900: rgba(128,255,234,0.9);
    --green-900: rgba(138,255,128,0.9);
    --orange-900: rgba(255,202,128,0.9);
    --pink-900: rgba(255,128,191,0.9);
    --purple-900: rgba(149,128,255,0.9);
    --red-900: rgba(255, 87, 87, 0.9);
    --yellow-900: rgba(255,255,128,0.9);
    --accentColor: var(--purple);
    --purple-cyan: linear-gradient(var(--gradientDegree),var(--purple),var(--cyan));
    --purple-red: linear-gradient(var(--gradientDegree),var(--purple),var(--red));
    --yellow-pink: linear-gradient(var(--gradientDegree),var(--yellow),var(--pink));
    --cyan-green: linear-gradient(var(--gradientDegree),var(--cyan),var(--green));
    --cyan-pink: linear-gradient(var(--gradientDegree),var(--cyan),var(--pink));
    --pink-purple: linear-gradient(var(--gradientDegree),var(--pink),var(--purple));
    --red-purple: linear-gradient(var(--gradientDegree),var(--redLight),var(--color-purple));
    --inventada: linear-gradient(var(--gradientDegree),var(--pink),var(--purple));
    
    
    --color-background: #121214;
    --color-green: #04d361;
    --color-red: #e83f5b;
    --color-orange: #fd951f;
    --color-yellow: #f7df1e;
    --color-purple: #8257e6;
    --color-purple-hover: #9466ff;
    --color-secondary: #e1e1e6;
    --color-text: #a8a8b3;
    --color-support: #737380;
    --color-shape: #202024;
    --color-shape-hover: #29292e;
    --color-icons: #41414d;
    --color-borders: #323238;
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