import {ProgressBar} from "react-bootstrap";
import {Link} from 'react-router-dom';
import {CategoryCardContainer} from '../styles'
import {CardAddon} from "../CardAddon";
import {CategoryResponseBody} from "../../../interfaces/categoryInterface";

interface Props {
    category: CategoryResponseBody;
}

export function CategoryCard({category}: Props){


    return(
        <CategoryCardContainer>
            <Link to={`/categories/${category.id}/products`}>
            <div className="col">
                <div className="card h-100">
                    <div className="card-body">
                        <CardAddon id={category.id}/>
                        <h5 className="card-title ">{category.name}</h5>
                        <p className="card-text ">R$ {category.budget}</p>
                        <ProgressBar now={50}  label={'50%'} title={`${50}% concluída`} />
                    </div>
                </div>
            </div>
            </Link>
        </CategoryCardContainer>
    )
}
