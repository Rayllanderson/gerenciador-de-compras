import {PrimaryButton} from "../Buttons/styles";
import {FiSearch} from "react-icons/all";
import {Container} from "./styles";
import {useContext} from "react";
import {PaginationContext} from "../../context/PaginationContext";

interface Props{
    placeholder?: string;
    action: () => void,
}

export default function Search({action, placeholder}: Props){
    const {handleSearchChange, search} = useContext(PaginationContext);
    return (
        <Container className="container">
            <div className='input-group input-group-lg' >
                <input type="search" className="rounded-md py-1 px-3 form-control"
                       value={search} onChange={handleSearchChange}
                       placeholder={placeholder}/>
                <PrimaryButton type="button" className="btn btn-lg" onClick={action}> <FiSearch/> </PrimaryButton>
            </div>
        </Container>
    )
}