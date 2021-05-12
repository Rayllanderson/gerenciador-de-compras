import ContentLoader from "react-content-loader";
import {CategoryCardContainer} from '../../Card/styles';
import {useContext, useEffect, useState} from "react";
import {isMobile} from "../../../utils/configsUtil";
import {ThemeContext} from "styled-components";

const Loader = () => {
    const {title} = useContext(ThemeContext)
    const [width, setWidth] = useState<number>();

    const background = title === 'dark' ? '#414558' : '#e9ecef';
    const foreground = title === 'dark' ? '#595b6e' : '#e3e3e3';
    useEffect(() => {
        isMobile() ? setWidth(300) : setWidth(190);
    }, [])

    return (
        <ContentLoader
            speed={2}
            width={300}
            height={80}
            viewBox="0 0 300 80"
            backgroundColor={background}
            foregroundColor={foreground}
            radius={50}
        >
            <rect x="0" y="8" rx="2" ry="2" width="95" height="16"/>
            <rect x="0" y="30" rx="2" ry="2" width="52" height="10"/>
            <rect x="0" y="59" rx="0" ry="0" width={width} height="17"/>
        </ContentLoader>
    )
}

export function CategoryCardLoader() {
    const [isVisible, setIsVisible] = useState(false);
    useEffect(() => {
        setTimeout(() => setIsVisible(true), 250);
        if (!isVisible) return () => {
        };
    }, [isVisible])

    return (
        isVisible ? (
            <CategoryCardContainer>
                <div className="card h-100">
                    <div className="card-body">
                        <Loader/>
                    </div>
                </div>
            </CategoryCardContainer>
        ) : <></>
    )
}