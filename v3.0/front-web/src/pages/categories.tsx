import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";

export default function Categories(){
    return (
        <div>
            <Header title={'Listas'} Icon={FiList}/>
        </div>
    );
}