export function getAuthHeader() {
    const userToken = localStorage.getItem('@GerenciadorDeCompras:token');
    if (userToken) {
        return {'Authorization': 'Bearer ' + userToken};
    } else {
        return {};
    }
}