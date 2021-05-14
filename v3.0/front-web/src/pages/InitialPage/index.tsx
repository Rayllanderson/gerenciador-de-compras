import {Page} from "./page";
import Logo from "../../components/Logo";
import {useEffect} from "react";

export function InitialPage() {
    const param = window.location.pathname.replace('/' , '');
    const pageName = !param ? 'login' : param;
    const transition = !param ? 'appearFromRight' : 'appearFromLeft';
    useEffect(() => {
        document.title = 'Gerenciador de compras';
    },[])
    return (
        <div className={`container ${transition} mt-5`}>
            <Logo />
            <Page name={pageName}/>
        </div>
    );
}