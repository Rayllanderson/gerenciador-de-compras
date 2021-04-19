import React from "react";
import BackButtonHeader from "../components/Buttons/BackButton";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";

export default function Products(){
    return (
        <div >
            <BackButtonHeader/>
            <ProductHeader />
            <Search placeholder={'Procurar um produto...'}/>
        </div>
    )
}