import styled from "styled-components";

export const HomeContainer = styled.div `
    max-width: 750px;
    background-color: ${props => props.theme.colors.backgroundSecondary};
    .buttons{
        display: flex;
        justify-content: center;
        flex-direction: column;
        align-items: center;
        a{
            margin-bottom: 12px;
            width: 200px;
        }
    }
`
export const BorderWrapper = styled.div`
   max-width: 750px;
   padding: 1rem;
   position: relative;
   background: ${props => props.theme.colors.progressBar};
   padding: 3px;
`