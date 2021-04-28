import {Container} from "./styles";
import {PrimaryButton} from '../Buttons/styles';
import {FiPlus} from "react-icons/all";
import React, {useContext} from "react";
import {ActionDropdown} from "../Dropdown/actionDropdown";
import {ModalContext} from "../../context/ModalContext";

export function ButtonGroup (){
    const {openAddModal} = useContext(ModalContext);
    return (
        <Container className="container">
            <div className={"group-buttons"}>
                <PrimaryButton className={"btn"} style={{borderBottomRightRadius: 0, borderTopRightRadius: 0}}
                onClick={openAddModal}>
                    <FiPlus size={21}/>
                </PrimaryButton>

                <ActionDropdown/>
                </div>
        </Container>
    )
}