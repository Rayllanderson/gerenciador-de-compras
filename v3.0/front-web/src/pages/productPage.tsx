import React from "react";
import BackButtonHeader from "../components/Buttons/BackButton";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";
import {ButtonGroup} from "../components/ButtonsGroup";
import ProductList from "../components/Card/product";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CyanSecondaryButton, YellowButton, RedButton} from "../components/Buttons/styles";
import {ProductModal} from "../components/Modal/product/ProductModal";

export default function ProductPage(){
    return (
        <div style={{minHeight: '100vh'}}>
            <BackButtonHeader/>
            <ProductHeader />
            <Search placeholder={'Procurar um produto...'}/>
            <ButtonGroup/>
            <ProductList/>

            <SelectItemsButtons>
                <YellowButton className={'btn me-4'} title={'Duplicar selecionados '}>  Mover  </YellowButton>
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Copiar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </SelectItemsButtons>

            <ProductModal/>

        </div>
    )
}