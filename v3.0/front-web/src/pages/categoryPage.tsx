import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";
import Search from "../components/Search";
import {ButtonGroup} from "../components/ButtonsGroup";
import CategoryList from "../components/Card/category";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CategoryModal} from "../components/Modal/category/CategoryModal";
import {CyanSecondaryButton, RedButton} from "../components/Buttons/styles";
import {DeleteModal} from "../components/Modal/DeleteModal";

export default function CategoryPage(){
    return (
        <div>
            <Header title={'Listas'} Icon={FiList}/>
            <Search placeholder={'Procurar uma lista...'}/>
            <ButtonGroup/>

            <CategoryList/>

            <SelectItemsButtons>
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Duplicar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </SelectItemsButtons>

            <CategoryModal/>
            <DeleteModal text={'Você tem certeza que deseja excluir a categoria x?'}/>
        </div>
    );
}