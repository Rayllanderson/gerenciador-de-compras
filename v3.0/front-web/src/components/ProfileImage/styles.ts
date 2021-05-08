import styled from "styled-components";

export const Container = styled.div`
    display: flex;
    justify-content: center;   
`
export const Avatar = styled.img `
    position: inline-block;
    border-radius: 50%;
    width: 200px;
    transition: .2s;
     &:hover{
        transform: scale(1.03); 
        opacity: 0.8;
        cursor: pointer;
    }
`