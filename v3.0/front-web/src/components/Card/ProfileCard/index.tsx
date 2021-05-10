import {FiUploadCloud} from "react-icons/all";
import {FiXCircle} from "react-icons/fi";
import {ProfileCardContainer} from '../styles';
import {Label} from './styles';
import {useContext} from "react";
import {ProfileImageContext} from "../../../contexts/ProfileImageContex";
import {AccountContext} from "../../../contexts/AccountContext";
import {ActionModalContext} from "../../../contexts/ActionModalContext";

export default function ProfileCard() {
    const {cardDisplay} = useContext(ProfileImageContext);
    const {handleImageChange} = useContext(AccountContext);
    const {removePhotoAction} = useContext(ActionModalContext);
    const hasFile: boolean = !!document.getElementById('profileImage');
    return (
        <ProfileCardContainer className={'card container'} style={{display: cardDisplay}}>
            <div className={'mb-1'}>
                <Label htmlFor={"file"} color={'#00b4d8'}>
                    <FiUploadCloud/> Upload
                </Label>
                <input type={'file'} hidden id={'file'} accept={'image/jpeg, image/png'} onChange={handleImageChange}/>
            </div>

            {hasFile &&
            <div>
                <Label color={'#e83f5b'} onClick={removePhotoAction}>
                    <FiXCircle/> Remover
                </Label>
            </div>
            }
        </ProfileCardContainer>
    )
}