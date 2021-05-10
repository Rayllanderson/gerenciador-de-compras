import {FiUploadCloud} from "react-icons/all";
import {FiXCircle} from "react-icons/fi";
import {ProfileCardContainer} from '../styles';
import {Label} from './styles';
import {useContext} from "react";
import {ProfileImageContext} from "../../../contexts/ProfileImageContex";
import {AccountContext} from "../../../contexts/AccountContext";

export default function ProfileCard(){
    const {cardDisplay} = useContext(ProfileImageContext);
    const {handleImageChange} = useContext(AccountContext);
    return(
        <ProfileCardContainer className={'card container'} style={{display: cardDisplay}}>
            <div className={'mb-1'}>
                <Label htmlFor={"file"} color={'#00b4d8'}>
                  <FiUploadCloud/> Upload
                </Label>
                <input type={'file'} hidden id={'file'} accept={'image/jpeg, image/png'} onChange={handleImageChange}/>
            </div>

            <div>
                <Label color={'#e83f5b'}>
                    <FiXCircle/> Remover
                </Label>
            </div>
        </ProfileCardContainer>
    )
}