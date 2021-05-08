import {Avatar, Container} from './styles';
import {useCallback, useState} from "react";

interface Props {
    src?: string
    click?: () => void; //temporaria, jaja é passada via context
}

export default function ProfileImage({src, click}: Props) {

    return (
        <Container>
            <Avatar onClick={click}
                    src="https://1.bp.blogspot.com/-fW1KYH6da7Q/XGC-_68BLmI/AAAAAAAAB5c/1W0L8DUPaUgITmyBZ7_ouTVUMffoTuyLACLcBGAs/s1600/Violet%2BEvergarden_anime.webp"
                    alt={'Profile photo'}
            />
        </Container>
    )
}