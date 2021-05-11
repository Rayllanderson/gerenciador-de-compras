import ContentLoader from "react-content-loader";
import {CategoryCardContainer} from '../../Card/styles';
import {useEffect, useState} from "react";
import {isMobile} from "../../../utils/configsUtil";

const Loader = () => {

    const [width, setWidth] = useState<number>();

    useEffect(() => {
        isMobile() ? setWidth(300) : setWidth(190);
    }, [])

    return (
        <ContentLoader
            speed={2}
            width={300}
            height={80}
            viewBox="0 0 300 80"
            backgroundColor="#414558"
            foregroundColor="#595b6e"
            radius={50}
        >
            <rect x="0" y="8" rx="2" ry="2" width="95" height="16" />
            <rect x="0" y="30" rx="2" ry="2" width="52" height="10" />
            <rect x="0" y="59" rx="0" ry="0" width={width} height="17" />
        </ContentLoader>
    )
}

export function CategoryCardLoader() {
    return (
        <CategoryCardContainer>
            <div className="card h-100">
                <div className="card-body">
                    <Loader/>
                </div>
            </div>
        </CategoryCardContainer>
    )
}