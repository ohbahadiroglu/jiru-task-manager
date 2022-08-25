import axios from "axios";

export default {
    create(period){
      return  axios.post("/period",period)
    },
    update(period){
      return  axios.put("/period",period)
    },
    get(){
        return axios.get("/period")
    },
    findById(periodId){
        return axios.get(`/period/${periodId}`)

    },
    remove(periodId){
      return  axios.delete(`/period/${periodId}`)
    }
  };