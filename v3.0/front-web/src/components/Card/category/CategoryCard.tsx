import {ProgressBar} from "react-bootstrap";
import {Link} from 'react-router-dom';
import {CategoryCardContainer} from '../styles'
import {CardAddon} from "../CardAddon";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";
import {useContext} from "react";
import {VisibilityCardItemContext} from "../../../contexts/CardItemVisibilityContext";
import {DeleteButton, EditButton} from '../../Buttons'
import {CategoryContext} from "../../../contexts/CategoryContext";
import {GeneralContext} from "../../../contexts/GeneralContex";

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
                    <CardAddon id={category.id} name={category.name}>
                        {editButtonIsVisible && <EditButton onClick={() => setToEdit(category)}/>}
                        {deleteButtonIsVisible && <DeleteButton onClick={() => setToRemove(category)}/>}
                    </CardAddon>
                    <Link to={`/categories/${category.id}/products`} onClick={clearPreviousData}>
                        <h5 className="card-title ">{category.name}</h5>
                        <p className="card-text ">R$ {category.budget}</p>
                    </Link>
                </div>
                <Link to={`/categories/${category.id}/products`} className={'footer'} onClick={clearPreviousData}>
                    <ProgressBar now={category.completedPercentage} label={`${category.completedPercentage}%`} max={100}
                                 title={`${category.completedPercentage}% concluída`}/>
                </Link>
            </div>

        </CategoryCardContainer>
    )
}
