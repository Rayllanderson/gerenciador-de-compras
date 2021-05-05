import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {StatisticData} from "../interfaces/statisticInterface";
import {ProductContext} from "./ProductContext";
import ProductController from "../controllers/productController";

interface StatisticProviderProps {
    children: ReactNode;
}

interface StatisticContextData {
    statisticsFromCurrentCategory: StatisticData
    fetchStatisticsFromCurrentCategory: () => void
}

export const StatisticContext = createContext<StatisticContextData>({} as StatisticContextData);

export function StatisticProvider({children}: StatisticProviderProps) {

    const [statisticsFromCurrentCategory, setStatisticsFromCurrentCategory] = useState<StatisticData>({} as StatisticData);
    const {currentCategoryId, updateStatistic} = useContext(ProductContext);

    const fetchStatisticsFromCurrentCategory = useCallback(async () => {
        if (currentCategoryId) {
            const api = new ProductController(currentCategoryId);
            await api.fetchStatistics().then((response) => {
                setStatisticsFromCurrentCategory(response.data);
            }).catch((err) => console.log(err));
        } else return;
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentCategoryId, updateStatistic])

    return (
        <StatisticContext.Provider value={{fetchStatisticsFromCurrentCategory, statisticsFromCurrentCategory}}>
            {children}
        </StatisticContext.Provider>
    )
}

