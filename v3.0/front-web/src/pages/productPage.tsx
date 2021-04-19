import React from "react";
import BackButtonHeader from "../components/Buttons/BackButton";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";
import {ButtonGroup} from "../components/ButtonsGroup";

export default function ProductPage(){
    return (
        <div >
            <BackButtonHeader/>
            <ProductHeader />
            <Search placeholder={'Procurar um produto...'}/>
            <ButtonGroup/>
        </div>
    )
}