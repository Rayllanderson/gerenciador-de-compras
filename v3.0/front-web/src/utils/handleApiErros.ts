export function getError(err: any){
    const serverIsOffline:boolean = !err.response
    if (serverIsOffline) {
        return 'Servidor está dormindo, mas já estamos acordando ele. Tente de novo em alguns segundos.';
    }
    return err.response.data.message;
}

export function getValidationError(err: any){
    const serverIsOffline:boolean = !err.response
    if (serverIsOffline) {
        return 'Servidor está dormindo, mas já estamos acordando ele. Tente de novo em alguns segundos.';
    }
    const field = getFirstError(err.response.data.fields);
    if (field === 'undefined') return ''; //false
    const message = getFirstError(err.response.data.fieldsMessage);
    return field + ': ' + message;
}

function getFirstError(error: string) {
    const comingError:string = error + '';
    return comingError.split(',')[0];
}
