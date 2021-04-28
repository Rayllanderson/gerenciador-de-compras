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
    let parseNumber = value.replaceAll('.', '');
    parseNumber = parseNumber.replaceAll(',', '.');
    return parseNumber;
}