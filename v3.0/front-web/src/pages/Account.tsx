import {AccountCardContainer} from "../components/Card/styles";
import ProfileImage from "../components/ProfileImage";

export function Account(){
    return(
        <AccountCardContainer className={'container mt-5'}>
            <div className={'pt-5 pb-5'}>
                <ProfileImage imageSrc={'5'}/>
            </div>
        </AccountCardContainer>
    )
}