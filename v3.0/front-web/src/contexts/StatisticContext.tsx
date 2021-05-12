import {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {StatisticData} from "../interfaces/statisticInterface";
import {ProductContext} from "./ProductContext";
import ProductController from "../controllers/productController";
import {getError} from "../utils/handleApiErros";
import {ToastContext} from "./ToastContext";
import AllProductController from "../controllers/allProductController";

interface StatisticProviderProps {
    children: ReactNode;
}

interface StatisticContextData {
    statistics: StatisticData,
    fetchStatisticsFromCurrentCategory: () => void,
    fetchStatisticsFromAllProducts: () => void,
    handleSelectChange: (e: any) => void,
}

export const StatisticContext = createContext<StatisticContextData>({} as StatisticContextData);

export function StatisticProvider({children}: StatisticProviderProps) {

    const [statistics, setStatistics] = useState<StatisticData>({} as StatisticData);
    const {currentCategoryId, updateStatistic} = useContext(ProductContext);
    const {addToast} = useContext(ToastContext);

    const fetchStatisticsFromCurrentCategory = useCallback(async () => {
        if (currentCategoryId) {
            const api = new ProductController(currentCategoryId);
            await api.fetchStatistics().then((response) => {
                setStatistics(response.data);
            }).catch((err) => addToast({
                type: "error",
                title: 'Error',
                description: getError(err)
            }))
        } else return;
        // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [currentCategoryId, updateStatistic, addToast])

    const fetchStatisticsFromCategoryId = useCallback(async (id: string) => {
        if (id) {
            const api = new ProductController(id);
            await api.fetchStatistics().then((response) => {
                setStatistics(response.data);
            }).catch((err) => addToast({
                type: "error",
                title: 'Error',
                description: getError(err)
            }))
        } else return;
    }, [addToast])

    const fetchStatisticsFromAllProducts = useCallback(async () => {
        const api = new AllProductController();
        await api.fetchStatistics().then((response) => {
            setStatistics(response.data);
        }).catch((err) => addToast({
            type: "error",
            title: 'Error',
            description: getError(err)
        }))
    }, [addToast])

    const handleSelectChange = useCallback((e:any) => {
        const value = e.target.value;
        if (value){
            if (value === 'all') fetchStatisticsFromAllProducts().then();
            else fetchStatisticsFromCategoryId(value).then();
        }
    }, [fetchStatisticsFromAllProducts, fetchStatisticsFromCategoryId])

    return (
        <StatisticContext.Provider value={{fetchStatisticsFromCurrentCategory,  statistics,
            handleSelectChange, fetchStatisticsFromAllProducts}}>
            {children}
        </StatisticContext.Provider>
    )
}

