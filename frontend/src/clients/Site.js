import axios from "axios";

export default {
    get() {
      return axios.get("/site");
    },
    create(site){
      return  axios.post("/site",site)
    },
    update(site){
      return  axios.put("/site",site)
    },
    remove(siteId){
      return  axios.delete(`/site/${siteId}`)
    }
  };