import {CardCategory} from "./cardCategory";


export default function CategoryList() {
    return (
        <div className={"container"}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>

                <CardCategory/>
                <CardCategory/>
                <CardCategory/>


                </div>
            </div>
        </div>
    )
}