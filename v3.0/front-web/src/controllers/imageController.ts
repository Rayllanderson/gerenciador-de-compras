import api from "../services/api";
import {getAuthHeader} from "../services/auth";

export default class ImageController {
    findMiniature() {
        return api.get('/image/miniature', {headers: getAuthHeader()});
    }
}