import {Container} from "./styles";
import {PrimaryButton} from '../Buttons/styles';
import {FiPlus} from "react-icons/all";
import React from "react";
import {ActionDropdown} from "../Dropdown/actionDropdown";

export function ButtonGroup (){
    return (
        <Container className="container">
            <div className={"group-buttons"}>
                <PrimaryButton className={"btn"} style={{borderBottomRightRadius: 0, borderTopRightRadius: 0}}>
                    <FiPlus size={21}/>
                </PrimaryButton>

                <ActionDropdown/>
                </div>
        </Container>
    )
}