import {useCallback, useEffect, useState} from "react";

interface Props {
    value: number
}

interface ObjectProps {
    text: string,
    color: string
}

export function NumberOfProductsText({value}: Props) {

    const color = '';

    const handleNumberOfProductsText = useCallback((numberOfProducts: number) => {
        return (numberOfProducts === 1 ? ' produto nessa lista' : ' produtos nessa lista');
    }, []);

    return (
        <p><span style={{color: color}}>{value}</span> {handleNumberOfProductsText(value)} </p>
    )
}

export function NumberOfProductsPurchasedText({value}: Props) {

    const color = ''

    const handleNumberOfProductsPurchasedText = useCallback((numberOfProductsPurchased: number) => {
        return (numberOfProductsPurchased === 1 ? ' produto comprado' : ' produtos comprados');
    }, [])

    return (
        <p><span style={{color: color}}>{value}</span> {handleNumberOfProductsPurchasedText(value)} </p>
    )
}

export function NumberOfProductsNotPurchasedText({value}: Props) {
    const color = ''

    const handleNumberOfProductsNotPurchasedText = useCallback((numberOfProductsNotPurchased: number) => {
        return (numberOfProductsNotPurchased === 1 ? ' produto não comprado' : ' produtos não comprados');
    }, [])

    return (
        <p><span style={{color: color}}>{value}</span> {handleNumberOfProductsNotPurchasedText(value)} </p>
    )
}

export function AmountSavedText({value}: Props) {

    const [object, setObject] = useState<ObjectProps>({} as ObjectProps)

    useEffect(() => {
        if (value < 0) {
            setObject({text: `Passou do seu orçamento em R$ ${(-(value))}`, color: 'var(--red)'});
        }

        if (value === 0) {
            setObject({text: 'Nada até o momento. R$ 0', color: ''});
        }
        if (value > 0) {
            setObject({text: 'Você economizou R$ ' + value, color: 'var(--green)'});
        }
    }, [value])

    return (
        <p style={{color: object.color}}>{object.text}</p>
    )
}

export function AvailableToSpendText({value}: Props) {

    const [object, setObject] = useState<ObjectProps>({} as ObjectProps)

    useEffect(() => {
        if (value < 0) {
            setObject({
                text: 'Você passou do seu orçamento em R$' + (-(value)) + ' Você não tem mais nada disponível para gastar.'
                , color: 'var(--red)'
            });
        }
        if (value === 0) {
            setObject({
                text: 'Você atingiu o orçamento para essa lista. Qualquer outra compra, passará de seu orçamento para essa lista.'
                , color: ''
            });
        }
        if (value > 0) {
            setObject({
                text: 'Você tem R$' + value + ' disponível para gastar de acordo com o orçamento para essa lista.'
                , color: 'var(--green)'
            });
        }
    }, [value])

    return (
        <p style={{color: object.color}}>{object.text} </p>
    )
}

export function AvailableToSpendIfBuyAllText({value}: Props) {

    const [object, setObject] = useState<ObjectProps>({} as ObjectProps)

    useEffect(() => {
        let text = 'Caso compre todos os produtos, ';
        if (value < 0) {
            setObject({text: text + 'passará em R$' + (-(value)) + ' do seu orçamento.', color: 'var(--red)'});
        }
        if (value > 0) {
            setObject({text: text + 'ficará com R$' + value, color: 'var(--green)'});
        }
    }, [value])

    return (
        <p style={{color: object.color}}>{object.text} </p>
    )
}