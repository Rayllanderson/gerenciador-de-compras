import api from "../services/api";
import {getAuthHeader} from "../services/auth";

export default class UserController {

    fetchUser(){
        return api.get('/users/details', {headers: getAuthHeader()});
    }

    findMiniature() {
        return api.get('/image/miniature', {headers: getAuthHeader()});
    }
}