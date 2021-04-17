import styled from "styled-components";

export const Container = styled.div`
    width: 100%;
    max-width: 750px;
    text-align: center;
`

export const Content = styled.div`
    margin: 0 auto;
    background: ${props => props.theme.colors.background};
    color: ${props => props.theme.colors.primary};
    border: 2px solid ${props => props.theme.colors.primary};
    width: 250px;
    height: 60px;
    border-radius: 5px;
    padding: 10px;
    
   h3{
    display: flex;
    align-items: center;
    justify-content: center
    }
    
    
    /*background: linear-gradient(${props => props.theme.colors.background}, ${props => props.theme.colors.background}) 
    padding-box, linear-gradient(var(--gradientDegree),var(--pink),var(--purple)) border-box;*/
`