import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";
import Search from "../components/Search";
import {ButtonGroup} from "../components/ButtonsGroup/categories";
import CategoryList from "../components/Card/category";
import {ProgressBar} from "react-bootstrap";

export default function Categories(){
    return (
        <div>
            <Header title={'Listas'} Icon={FiList}/>
            <Search/>
            <ButtonGroup/>
            <CategoryList/>
        </div>
    );
}