import axios  from "axios";
export default{
    get(){
        return axios.get("/additional")
    },
    getAdditionalPeriod(periodId){
        return axios.get(`/additional/period-additional/${periodId}`);
    },
    create(additional){
        return axios.post("/additional", additional)
    },
    update(additional){
        console.log(additional);
        return axios.put("/additional",additional)
    },
    remove(additionalId){
        return axios.delete(`/additional/${additionalId}`)
    }
}