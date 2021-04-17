import React from "react";
import {Container, Content} from './styles'
interface Props {
    title: string,
    Icon: React.ComponentType
}

export default function Header({title, Icon}: Props) {
    return (
        <Container className="container mt-5">
            <Content>
              <h3><Icon/> {title}</h3>
            </Content>
        </Container>
    );
}