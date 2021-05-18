import axios from "axios";

const api = axios.create({
  //mudar baseURL para -> http://localhost:8080/api/v1
  baseURL: process.env.REACT_APP_API_URL,
});
export default api;