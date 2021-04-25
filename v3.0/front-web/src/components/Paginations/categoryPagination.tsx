import {Pagination} from "react-bootstrap";
import {useContext} from "react";
import {CategoryContext} from "../../context/CategoryContext";

export function CategoryPagination() {

    const {pagination, setPage} = useContext(CategoryContext);

    let active = pagination.number;
    let items = [];
    for (let number = 0; number < pagination.totalPages; number++) {
        items.push(
            <Pagination.Item key={number} active={number === active} activeLabel={''} onClick={() => setPage(number)}>
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