import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";
import Search from "../components/Search";

export default function Categories(){
    return (
        <div>
            <Header title={'Listas'} Icon={FiList}/>
            <Search/>
        </div>
    );
}