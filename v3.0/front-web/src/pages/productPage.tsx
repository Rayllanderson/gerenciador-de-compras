import React from "react";
import BackButtonHeader from "../components/Buttons/BackButton";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";
import {ButtonGroup} from "../components/ButtonsGroup";
import ProductList from "../components/Card/product/ProductList";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CyanSecondaryButton, RedButton, YellowButton} from "../components/Buttons/styles";
import {ProductModal} from "../components/Modal/product/ProductModal";
import {DeleteModal} from "../components/Modal/DeleteModal";
import {MyPagination} from "../components/Paginations/Pagination";
import ProductController from "../controllers/productController";
import {useParams} from "react-router-dom";

interface RouteParams {
    id: string
}

export default function ProductPage(){
    const params = useParams<RouteParams>();

    return (
        <div style={{minHeight: '100vh'}}>
            <BackButtonHeader to={'/categories'}/>
            <ProductHeader />
            <Search placeholder={'Procurar um produto...'}/>
            <ButtonGroup/>
            <ProductList categoryId={params.id}/>
            <MyPagination controller={new ProductController(params.id)}/>
            <SelectItemsButtons>
                <YellowButton className={'btn me-4'} title={'Duplicar selecionados '}>  Mover  </YellowButton>
                <CyanSecondaryButton className={'btn '} title={'Duplicar selecionados'}>Copiar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}>Deletar </RedButton>
            </SelectItemsButtons>

            <ProductModal/>
            <DeleteModal text={'Você tem certeza que deseja excluir o produto x?'}/>
        </div>
    )
}