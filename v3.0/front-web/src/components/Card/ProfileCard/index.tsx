import {FiUploadCloud} from "react-icons/all";
import {FiXCircle} from "react-icons/fi";
import {ProfileCardContainer} from '../styles';
import {Label} from './styles';
import {useContext} from "react";
import {ProfileImageContext} from "../../../contexts/ProfileImageContex";

interface Props{
}

export default function ProfileCard(){
    const {cardOpacity} = useContext(ProfileImageContext)
    return(
        <ProfileCardContainer className={'card container'} style={{opacity: cardOpacity}}>
            <div className={'mb-1'}>
                <Label htmlFor={"file"} color={'#00b4d8'}>
                  <FiUploadCloud/> Upload
                </Label>
                <input type={'file'} hidden id={'file'} accept={'image/jpeg, image/png'}/>
            </div>

            <div>
                <Label color={'#e83f5b'}>
                    <FiXCircle/> Remover
                </Label>
            </div>
        </ProfileCardContainer>
    )
}