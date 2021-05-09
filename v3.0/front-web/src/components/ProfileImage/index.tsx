import {Avatar, Container} from './styles';
import {useContext} from "react";
import {ProfileImageContext} from "../../contexts/ProfileImageContex";

interface Props {
    src?: string
}

export default function ProfileImage({src}: Props) {

    const {toggleCardVisibility} = useContext(ProfileImageContext)

    return (
        <Container>
            <Avatar onClick={toggleCardVisibility}
                    src={src}
                    alt={'Profile photo'}
            />
        </Container>
    )
}