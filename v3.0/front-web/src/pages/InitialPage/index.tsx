import {Page} from "./page";
import Logo from "../../components/Logo";

export function InitialPage() {
    const param = window.location.pathname.replace('/' , '');
    const pageName = !param ? 'login' : param;
    const transition = !param ? 'appearFromRight' : 'appearFromLeft';
    return (
        <div className={`container ${transition} mt-5`}>
            <Logo />
            <Page name={pageName}/>
        </div>
    );
}