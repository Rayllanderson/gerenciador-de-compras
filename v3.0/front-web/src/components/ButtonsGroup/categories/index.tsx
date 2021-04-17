import {Container} from "./styles";
import {PrimaryButton} from '../../Buttons/styles';
import {FiMoreVertical, FiPlus, FiSettings} from "react-icons/all";
import {Dropdown} from "react-bootstrap";
import React from "react";
import {CardDropdown} from "./dropdown";

export function ButtonGroup (){
    return (
        <Container className="container">
            <div className={"group-buttons"}>
                <PrimaryButton className={"btn"} style={{borderBottomRightRadius: 0, borderTopRightRadius: 0, color: 'var(--green)'}}>
                    <FiPlus size={21}/>
                </PrimaryButton>

                <CardDropdown/>
                </div>
        </Container>
    )
}