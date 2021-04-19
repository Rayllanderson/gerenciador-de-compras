import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";
import Search from "../components/Search";
import {ButtonGroup} from "../components/ButtonsGroup";
import CategoryList from "../components/Card/category";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CategoryModal, DeleteCategoryModal} from "../components/Modal/category";
import {CyanSecondaryButton, RedButton} from "../components/Buttons/styles";

export default function Categories(){
    return (
        <div>
            <Header title={'Listas'} Icon={FiList}/>
            <Search placeholder={'Procurar uma lista...'}/>
            <ButtonGroup/>

            <CategoryList/>

            <CategoryModal/>
            <DeleteCategoryModal/>
            <SelectItemsButtons>
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Duplicar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </SelectItemsButtons>
        </div>
    );
}