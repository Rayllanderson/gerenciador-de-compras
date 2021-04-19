import {CategoryCard} from "./categoryCard";
import {useState} from "react";


export default function CategoryList() {
    const [selectedItems, setSelectedItems] = useState<any[]>([]);

    // rascunhos...
    const addSelectedItem = (value:any) => {
        setSelectedItems((item => [...item, value]))
    }

    const removeSelectedItem = (value:any) => {
        setSelectedItems(item => item.filter(item => item !== value));
    }

    return (
        <div className={"container"}>
            <div style={{animation: 'appearFromBottom 1s'}}>

                <div className="row row-cols-1 row-cols-md-3 g-4" style={{maxWidth: 750, margin: '0 auto'}}>

                <CategoryCard id={'3'} selectedItems={selectedItems} addSelectItem={addSelectedItem} removeSelectItem={removeSelectedItem}/>
                <CategoryCard id={'2'} selectedItems={selectedItems} addSelectItem={addSelectedItem} removeSelectItem={removeSelectedItem}/>
                <CategoryCard id={'1'} selectedItems={selectedItems} addSelectItem={addSelectedItem} removeSelectItem={removeSelectedItem}/>


                </div>
            </div>
        </div>
    )
}