import {Container} from "./styles";
import {PrimaryButton} from '../Buttons/styles';
import {FiPlus} from "react-icons/all";
import React from "react";
import {ActionDropdown} from "../Dropdown";

interface Props {
    addAction: () => void,
    filterAction: () => void
}
export function ButtonGroup ({addAction, filterAction}: Props){
    return (
        <Container className="container">
            <div className={"group-buttons"}>
                <PrimaryButton className={"btn"} style={{borderBottomRightRadius: 0, borderTopRightRadius: 0}}
                onClick={addAction}>
                    <FiPlus size={21}/>
                </PrimaryButton>

                <ActionDropdown filterAction={filterAction}/>
                </div>
        </Container>
    )
}