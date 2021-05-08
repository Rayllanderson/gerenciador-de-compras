import {RedButton} from "../../Buttons/styles";
import {FiUploadCloud} from "react-icons/all";
import {FiXCircle} from "react-icons/fi";
import {ProfileCardContainer} from '../styles';
import {Label} from './styles';

interface Props{
    opacity: number
}

export default function ProfileCard({opacity}: Props){
    return(
        <ProfileCardContainer className={'card container mt-2'} style={{opacity: opacity}}>
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