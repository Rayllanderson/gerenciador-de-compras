import {ProgressBar} from "react-bootstrap";
import {Link} from 'react-router-dom';
import {CategoryCardContainer} from '../styles'
import {CardAddon} from "../CardAddon";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {useContext} from "react";
import {VisibilityCardItemContext} from "../../../context/CardItemVisibilityContext";
import {DeleteButton, EditButton} from '../../Buttons'
import {CategoryContext} from "../../../context/CategoryContext";
import {GeneralContext} from "../../../context/GeneralContex";

interface Props {
    category: CategoryResponseBody;
}

export function CategoryCard({category}: Props) {
    const {deleteButtonIsVisible, editButtonIsVisible} = useContext(VisibilityCardItemContext);
    const {setToEdit, setToRemove} = useContext(CategoryContext);
    const {clearPreviousData} = useContext(GeneralContext);
    return (
        <CategoryCardContainer>

            <div className="card h-100">
                <div className="card-body">
                    <CardAddon id={category.id}>
                        {editButtonIsVisible && <EditButton onClick={() => setToEdit(category)}/>}
                        {deleteButtonIsVisible && <DeleteButton onClick={() => setToRemove(category)}/>}
                    </CardAddon>
                    <Link to={`/categories/${category.id}/products`} onClick={clearPreviousData}>
                        <h5 className="card-title ">{category.name}</h5>
                        <p className="card-text ">R$ {category.budget}</p>
                    </Link>
                </div>
                <Link to={`/categories/${category.id}/products`} className={'footer'} onClick={clearPreviousData}>
                    <ProgressBar now={50} label={'50%'} title={`${50}% concluída`}/>
                </Link>
            </div>

        </CategoryCardContainer>
    )
}
