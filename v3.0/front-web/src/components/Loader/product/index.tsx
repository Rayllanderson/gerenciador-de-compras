import ContentLoader from "react-content-loader";
import {useContext, useEffect, useState} from "react";
import {isMobile} from "../../../utils/configsUtil";
import {ThemeContext} from "styled-components";

export function ProductListLoader() {
    const [width, setWidth] = useState<number>(680);
    const [firstXWidth, setFirstXWidth] = useState<number>();
    const [secondXWidth, setSecondXWidth] = useState<number>();
    const [thirdXWidth, setThirdXWidth] = useState<number>();
    const [fourthXWidth, setFourthXWidth] = useState<number>();

    const {title} = useContext(ThemeContext)

    const background = title === 'dark' ? '#414558' : '#e9ecef';
    const foreground = title === 'dark' ? '#595b6e' : '#e3e3e3';

    const [isVisible, setIsVisible] = useState(false);

    useEffect(() => {
        isMobile() ? setWidth(350) : setWidth(680);
        isMobile() ? setFirstXWidth(10) : setFirstXWidth(50);
        isMobile() ? setSecondXWidth(120) : setSecondXWidth(250);
        isMobile() ? setThirdXWidth(200) : setThirdXWidth(415);
        isMobile() ? setFourthXWidth(300) : setFourthXWidth(600);

        setTimeout(() => setIsVisible(true), 250);
        if (!isVisible) return () => {
        };
    }, [setIsVisible, isVisible])

    return (
        isVisible ?
            <ContentLoader
                speed={2}
                width={width}
                height={70}
                viewBox={`0 0 ${width} 70`}
                backgroundColor={background}
                foregroundColor={foreground}
                className={'loader'}
            >
                <rect x={firstXWidth} y="25" rx="2" ry="2" width="90" height="30"/>
                <rect x={secondXWidth} y="25" rx="2" ry="2" width="32" height="30"/>
                <rect x={thirdXWidth} y="25" rx="0" ry="0" width="56" height="30"/>
                <rect x={fourthXWidth} y="25" rx="0" ry="0" width="56" height="30"/>
            </ContentLoader> :
            <></>
    )
}