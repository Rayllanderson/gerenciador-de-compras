import {Avatar, Container, DefaultAvatar} from './styles';
import {useContext} from "react";
import {ProfileImageContext} from "../../contexts/ProfileImageContex";
import {FiUser} from "react-icons/fi";
import {ThemeContext} from "styled-components";

interface Props {
    src?: string
}

export default function ProfileImage({src}: Props) {

    const {toggleCardVisibility} = useContext(ProfileImageContext);
    const {colors} = useContext(ThemeContext);

    return (
        <Container >
            {!!src ? (
                <Avatar onClick={toggleCardVisibility}
                    src={src}
                    alt={'Profile photo'}
                />
            ) : (
                <>
                    <DefaultAvatar onClick={toggleCardVisibility}>
                        <FiUser size={60} color={colors.primary}/>
                    </DefaultAvatar>
                </>
            )

            }

        </Container>
    )
}