import {Avatar, Container} from './styles';

interface Props {
    src?: string
    click?: () => void; //temporaria, jaja é passada via context
}

export default function ProfileImage({src, click}: Props) {

    return (
        <Container>
            <Avatar onClick={click}
                    src={src}
                    alt={'Profile photo'}
            />
        </Container>
    )
}