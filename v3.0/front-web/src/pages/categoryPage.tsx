import React from "react";
import Header from "../components/Header";
import {FiList} from "react-icons/all";
import Search from "../components/Search";
import {ButtonGroup} from "../components/ButtonsGroup";
import CategoryList from "../components/Card/category/CategoryList";
import {CategoryModal} from "../components/Modal/category/CategoryModal";
import {DeleteModal} from "../components/Modal/DeleteModal";
import {MyPagination} from "../components/Paginations/Pagination";
import CategoryController from "../controllers/categoryController";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CyanSecondaryButton, RedButton} from '../components/Buttons/styles'

export default function CategoryPage(){
    return (
        <div style={{minHeight: '100vh'}}>
            <Header title={'Listas'} Icon={FiList}/>
            <Search placeholder={'Procurar uma lista...'}/>
            <ButtonGroup/>

            <CategoryList/>

            <MyPagination controller={new CategoryController()}/>

            <SelectItemsButtons >
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Duplicar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </SelectItemsButtons>

            <CategoryModal/>
            <DeleteModal text={'Você tem certeza que deseja excluir a categoria x?'}/>
        </div>
    );
}