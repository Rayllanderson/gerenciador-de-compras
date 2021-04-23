import {Container} from "./styles";
import {SvgIcon} from "./svgIcon";

export default function Logo() {
    return (
        <Container className='logoContent'>
            <div className="text-center">
                <SvgIcon/>
            </div>
        </Container>
    );
}