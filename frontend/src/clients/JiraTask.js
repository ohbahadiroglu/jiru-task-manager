import axios from "axios"

export default {
    getAllJiraTask(JiraRequestModel){
       return axios.get(`/jiraTask/${JiraRequestModel.siteId}/${JiraRequestModel.projectKey}`);
    }
}