import {createGlobalStyle} from 'styled-components';

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
  }
  
  body {
    zoom: 115%
  }
  
  a {
     text-decoration: none;
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
    --green: #8aff80;
    --greenSecondary: #d0ffcc;
    --greenLight: hsla(115,100%,75%,5%);
    --orange: #ffca80;
    --orangeSecondary: #ffeacc;
    --orangeLight: hsla(35,100%,75%,5%);
    --pink: #ff80bf;
    --pinkSecondary: #ffcce6;
    --pinkLight: hsla(330,100%,75%,5%);
    --purple: #9580ff;
    --purpleSecondary: #d5ccff;
    --purpleLight: hsl(250,100%,75%,5%);
    --red: #ff5555;
    --redSecondary: #ffd5cc;
    --redLight: hsla(10,100%,75%,5%);
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
    --yellow-pink: linear-gradient(var(--gradientDegree),var(--yellow),var(--pink));
    --cyan-green: linear-gradient(var(--gradientDegree),var(--cyan),var(--green));
    --pink-purple: linear-gradient(var(--gradientDegree),var(--pink),var(--purple));
    --inventada: linear-gradient(var(--gradientDegree),var(--pink),var(--purple));
`;