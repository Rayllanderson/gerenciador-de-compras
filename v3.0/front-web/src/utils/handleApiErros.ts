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
    return err.response.data.fields;
}
