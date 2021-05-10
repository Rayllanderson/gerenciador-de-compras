import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {StatisticData} from "../interfaces/statisticInterface";
import {ProductContext} from "./ProductContext";
import ProductController from "../controllers/productController";
import {getError} from "../utils/handleApiErros";
import {ToastContext} from "./ToastContext";

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
    const {addToast} = useContext(ToastContext);

    const fetchStatisticsFromCurrentCategory = useCallback(async () => {
        if (currentCategoryId) {
            const api = new ProductController(currentCategoryId);
            await api.fetchStatistics().then((response) => {
                setStatisticsFromCurrentCategory(response.data);
            }).catch((err) => addToast({
                type: "error",
                title: 'Error',
                description: getError(err)
            }))
        } else return;
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentCategoryId, updateStatistic, addToast])

    return (
        <StatisticContext.Provider value={{fetchStatisticsFromCurrentCategory, statisticsFromCurrentCategory}}>
            {children}
        </StatisticContext.Provider>
    )
}

