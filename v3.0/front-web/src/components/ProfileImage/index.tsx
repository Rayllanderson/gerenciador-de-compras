import {Avatar, Container} from './styles';

interface Props {
    src?: string
}

export default function ProfileImage({src}: Props) {
    return (
        <Container>
            <Avatar onClick={() => alert('kk')}
                    src="https://1.bp.blogspot.com/-fW1KYH6da7Q/XGC-_68BLmI/AAAAAAAAB5c/1W0L8DUPaUgITmyBZ7_ouTVUMffoTuyLACLcBGAs/s1600/Violet%2BEvergarden_anime.webp"
                    alt={'Profile photo'}
            />
        </Container>
    )
}