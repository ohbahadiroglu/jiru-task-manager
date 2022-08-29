import axios from "axios";

export default{
    get(){
        return axios.get("/period");
    },
    getPeriodId(periodId){
        return axios.get(`/period/${periodId}`);
    },
    
    create(period){
        return axios.post("/period",period);
    },
    update (period){
        return axios.put("/period",period);
    },
    remove(periodId){
        return axios.delete(`/period/${periodId}`);
    }
}