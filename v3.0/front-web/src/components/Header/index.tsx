import React from "react";
import {Container, Content} from './style'
interface Props {
    title: string,
    Icon: React.ComponentType
}

export default function Header({title, Icon}: Props) {
    return (
        <Container className="container mt-5">
            <Content>
              <h2><Icon/> {title}</h2>
            </Content>
        </Container>
    );
}