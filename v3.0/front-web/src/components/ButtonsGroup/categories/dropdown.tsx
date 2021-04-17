import React from "react";
import {FiMoreVertical} from "react-icons/all";
import {Dropdown} from "react-bootstrap";
import {DropdownContent} from './styles'

export function CardDropdown() {
    return (
        <DropdownContent >
            <Dropdown drop='down'>
                <Dropdown.Toggle variant="dropdown" className="button-dropdown button button-primary"
                                 style={{borderBottomLeftRadius: 0, borderTopLeftRadius: 0}}>
                    <FiMoreVertical size={21}/>
                </Dropdown.Toggle>
                <Dropdown.Menu className="drop-menu">
                    <Dropdown.Header className="drop-header">Ações</Dropdown.Header>
                    <Dropdown.Item>Selecionar</Dropdown.Item>
                    <Dropdown.Item>Editar</Dropdown.Item>
                    <Dropdown.Item>Remover</Dropdown.Item>

                    <Dropdown.Header className="drop-header">Filtros</Dropdown.Header>
                    <Dropdown.Item>Filtrar</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </DropdownContent>
    )
}