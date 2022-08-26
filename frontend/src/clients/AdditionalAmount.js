import axios  from "axios";
export default{
    get(){
        return axios.get("/additional")
    },
    create(additional){
        return axios.post("/additional", additional)
    },
    update(additional){
        return axios.put("/additional",additional)
    },
    remove(additionalId){
        return axios.delete(`/additional/${additionalId}`)
    }
}