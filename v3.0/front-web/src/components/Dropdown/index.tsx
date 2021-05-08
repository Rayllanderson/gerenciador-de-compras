import {Dropdown} from "react-bootstrap";
import {FiHelpCircle, FiLogOut, FiMoreVertical, FiPieChart, FiSettings} from "react-icons/all";
import {Link} from 'react-router-dom';
import React, {useContext} from "react";
import {DropdownContent, NavBarContent} from './styles';
import {FiUser} from "react-icons/fi";
import {VisibilityCardItemContext} from "../../contexts/CardItemVisibilityContext";
import {LogoutContext} from "../../contexts/LogoutContext";

interface ActionDropdownProps {
    filterAction: () => void
}

export function DropdownNavbar() {
    const {logout} = useContext(LogoutContext);
    return (
        <NavBarContent>
            <Dropdown className="content-items" drop='down'>
                <Dropdown.Toggle variant="dropdown">
                    < FiSettings size={21}/>
                </Dropdown.Toggle>
                <Dropdown.Menu className="drop-menu">
                    <Dropdown.Item> <Link to={'/account'}> <FiUser/> Minha Conta </Link></Dropdown.Item>
                    <Dropdown.Item><FiPieChart/> Estatísticas</Dropdown.Item>
                    <Dropdown.Item><FiHelpCircle/> Ajuda</Dropdown.Item>
                    <Dropdown.Header> </Dropdown.Header>
                    <Dropdown.Item onClick={logout}><FiLogOut/> Logout</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </NavBarContent>
    )
}

export function ActionDropdown({filterAction}: ActionDropdownProps) {

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
        <DropdownContent >
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
                    <Dropdown.Item onClick={filterAction}>Filtrar</Dropdown.Item>
                </Dropdown.Menu>
            </Dropdown>
        </DropdownContent>
    )
}