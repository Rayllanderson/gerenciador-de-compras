import {BallLoader} from "../index";
import {NameLoaderContainer} from './styles';
import {useEffect, useState} from "react";

export function NameLoader() {
    const [isVisible, setIsVisible] = useState(false);
    useEffect(() => {
        setTimeout(() => setIsVisible(true), 250);
        if (!isVisible) return () => {
        };
    }, [isVisible])
    return (
        isVisible ?
            <NameLoaderContainer>
                <BallLoader size={'sm'}/>
                <BallLoader size={'sm'}/>
                <BallLoader size={'sm'}/>
            </NameLoaderContainer>
            : <></>
    )
}