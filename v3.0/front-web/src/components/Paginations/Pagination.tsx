import {Pagination} from "react-bootstrap";
import {Pagination as PaginationInterface} from '../../interfaces/paginationData';
import {useContext} from "react";
import {PaginationContext} from "../../context/PaginationContext";

interface Props {
    controller: PaginationInterface;
}

export function MyPagination({controller}: Props) {

    const {pagination, setPage} = useContext(PaginationContext);
    let active = pagination.number;
    let items = [];
    for (let number = 0; number < pagination.totalPages; number++) {
        items.push(
            <Pagination.Item key={number} active={number === active} activeLabel={''}
                             onClick={() => {setPage(controller, number)}}>
                {number + 1}
            </Pagination.Item>,
        );
    }
    return (

        <Pagination>
            {items}
        </Pagination>
    )
}