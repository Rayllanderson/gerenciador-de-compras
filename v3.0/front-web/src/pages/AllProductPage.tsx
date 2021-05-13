import React, {useContext} from "react";
import Search from "../components/Search";
import {ProductHeader} from "../components/Header/Product";
import {ButtonGroup} from "../components/ButtonsGroup";
import ProductList from "../components/Card/product/ProductList";
import {SelectItemsButtons} from "../components/ButtonsGroup/selectItemsButtons";
import {CyanSecondaryButton, RedButton, YellowButton} from "../components/Buttons/styles";
import {ProductModal} from "../components/Modal/product/ProductModal";
import {DeleteModal} from "../components/Modal/DeleteModal";
import {MyPagination} from "../components/Paginations/Pagination";
import {ProductContext} from "../contexts/ProductContext";
import {TransferModal} from "../components/Modal/product/TransferModal";
import {ActionModalContext} from "../contexts/ActionModalContext";
import AllProductController from "../controllers/allProductController";
import {StatisticContext} from "../contexts/StatisticContext";

export default function AllProductPage(){
    const {setToSave, remove, selectedProduct} = useContext(ProductContext);
    const {copyProductsAction, moveProductsAction, removeVariousProductsAction, openFilterProductModalAction} = useContext(ActionModalContext);
    const {fetchStatisticsFromAllProducts} = useContext(StatisticContext);
    return (
        <div style={{minHeight: '100vh'}}>

            <ProductHeader fetchStatisticFunction={fetchStatisticsFromAllProducts}/>

            <Search placeholder={'Procurar um produto...'}/>

            <ButtonGroup addAction={setToSave} filterAction={openFilterProductModalAction}/>

            <ProductList controller={new AllProductController()}/>

            <MyPagination controller={new AllProductController()}/>

            <SelectItemsButtons>
                <YellowButton className={'btn me-4'} title={'Mover produtos selecionados para outra categoria'}
                onClick={moveProductsAction}>  Mover  </YellowButton>
                <CyanSecondaryButton className={'btn '} title={'Copiar produtos selecionados para outra categoria'}
                onClick={copyProductsAction}>Copiar</CyanSecondaryButton>
                <RedButton className={'btn ms-4'} title={'Deletar selecionados'}
                onClick={removeVariousProductsAction}>Deletar </RedButton>
            </SelectItemsButtons>

            <ProductModal/>

            <DeleteModal text={`Você tem certeza que deseja excluir o produto ${selectedProduct.name}?`}
                         action={remove}/>

            <TransferModal/>
        </div>
    )
}