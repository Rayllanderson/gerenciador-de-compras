export function hasOnlyNumber(value: string){
    return /^\d+$/.test(value);
}

export function isNotEmpty(value: string){
    return value.trim().length > 0;
}

export function isNotNull(value: string){
    return value != null;
}

export function getNumberWithoutMask(value: string){
    if (value == null) return '0';
    const valueIsWithoutMask = !value.toString().includes(',')
    if (valueIsWithoutMask) return value;
    let parseNumber = value.toString().replace('.', '');
    parseNumber = parseNumber.toString().replace(',', '.');
    return parseNumber;
}