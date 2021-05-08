import {AccountCardContainer} from "../components/Card/styles";
import ProfileImage from "../components/ProfileImage";
import ProfileCard from "../components/Card/ProfileCard";
import {useCallback, useState} from "react";

export function Account(){
    const [opacity, setOpacity] = useState<number>(0);
    const [cardIsOpen, setCardIsOpen] = useState<boolean>(false)

    const showCard = useCallback(() => {
        setOpacity(1);
        setCardIsOpen(true);
    }, [setOpacity, setCardIsOpen, setCardIsOpen]);

    const hideCard = useCallback(() => {
        setOpacity(0);
        setCardIsOpen(false);
    }, [setOpacity, setCardIsOpen]);

    const toggleCardVisibility = useCallback(() => {
        if (cardIsOpen) hideCard();
        else showCard();
    }, [showCard, hideCard, cardIsOpen]);
//  criar um context pra essas funções ae... adeus. raios caindo

    return(
        <AccountCardContainer className={'container mt-5'}>
            <div className={'pt-5 pb-5'}>
                <ProfileImage src={'5'} click={toggleCardVisibility}/>
                <ProfileCard opacity={opacity} />
            </div>
        </AccountCardContainer>
    )
}