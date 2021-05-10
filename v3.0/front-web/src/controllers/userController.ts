import api from "../services/api";
import {getAuthHeader} from "../services/auth";
import {UserPutBody} from "../interfaces/userInterface";

export default class UserController {

    updateData(user:UserPutBody){
        return api.put('/users/update', user, {headers: getAuthHeader()});
    }

    updatePassword(password: string){
        return api.put('/users/update/password', {password: password}, {headers: getAuthHeader()});
    }

    uploadPhoto(file: any){
        return api.post('/image',  file, {headers: getAuthHeader()});
    }

    fetchUser(){
        return api.get('/users/details', {headers: getAuthHeader()});
    }

    findMiniature() {
        return api.get('/image/miniature', {headers: getAuthHeader()});
    }
}