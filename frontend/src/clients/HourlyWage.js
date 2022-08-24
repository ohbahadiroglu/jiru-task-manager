import axios from "axios"

export default{
    get(){
        return axios.get("/wage")
    },
    create(wage){
        return axios.post("/wage",wage)
    },
    update(wage){
        return axios.put("/wage",wage)
    },
    remove(wageId){
        return axios.delete(`/wage/${wageId}`)
    }
}