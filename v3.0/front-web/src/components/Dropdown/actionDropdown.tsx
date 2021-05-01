import React, {useContext} from "react";
import {FiMoreVertical} from "react-icons/all";
import {Dropdown} from "react-bootstrap";
import {ActionDropdownContent} from './styles'
import {VisibilityCardItemContext} from "../../contexts/CardItemVisibilityContext";

export function ActionDropdown() {

    const {showCheckBox, hideCheckBox, checkBoxIsVisible, showDeleteButton, hideDeleteButton
    , hideEditButton, showEditButton, editButtonIsVisible, deleteButtonIsVisible} = useContext(VisibilityCardItemContext);


    const handleCheckBoxClick = () => {
        checkBoxIsVisible ? hideCheckBox() : showCheckBox();
    }

    const handleEditClick = () => {
        editButtonIsVisible ? hideEditButton() : showEditButton();
    }

    const handleDeleteClick = () => {
        deleteButtonIsVisible ? hideDeleteButton() : showDeleteButton();
    }

    return (
        <ActionDropdownContent >
            <Dropdown drop='down'>
                <Dropdown.Toggle variant="dropdown" className="button-dropdown button button-primary"
                                 style={{borderBottomLeftRadius: 0, borderTopLeftRadius: 0}}>
                    <FiMoreVertical size={21} color={'#ffff'}/>
                </Dropdown.Toggle>
                <Dropdown.Menu className="drop-menu">
                    <Dropdown.Header className="drop-header">Ações</Dropdown.Header>
                    <Dropdown.Item onClick={handleCheckBoxClick}>Selecionar</Dropdown.Item>
                    <Dropdown.Item onClick={handleEditClick}>Editar</Dropdown.Item>
                    <Dropdown.Item onClick={handleDeleteClick}>Remover</Dropdown.Item>

                    <Dropdown.Header className="drop-header">Filtros</Dropdown.Header>
                    <Dropdown.Item>Filtrar</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </ActionDropdownContent>
    )
}