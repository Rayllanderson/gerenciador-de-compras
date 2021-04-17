import {PrimaryButton} from "../Buttons/styles";
import {FiSearch} from "react-icons/all";
import {Container} from "./styles";

export default function Search(){
    return (
        <Container className="container">
            <div className='input-group input-group-lg' >
                <input type="search" className="rounded-md py-1 px-3 form-control " placeholder="Procurar uma lista..."/>
                <PrimaryButton type="button" className="btn btn-lg"> <FiSearch/> </PrimaryButton>
            </div>
        </Container>
    )
}