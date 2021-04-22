import React from "react";
import BackButtonHeader from "../components/Buttons/BackButton";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";
import {ButtonGroup} from "../components/ButtonsGroup";
import ProductList from "../components/Card/product";

export default function ProductPage(){
    return (
        <div style={{minHeight: '100vh'}}>
            <BackButtonHeader/>
            <ProductHeader />
            <Search placeholder={'Procurar um produto...'}/>
            <ButtonGroup/>
            <ProductList/>
        </div>
    )
}