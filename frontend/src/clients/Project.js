import axios from "axios"

export default{
    get(siteId){
        return axios.get(`/project/${siteId}`);
    }
}