import axios from "axios"

export default{
    getAllTasks(){
        return axios.get("/task");
    },
    getTaskById(taskId){
        return axios.get(`/task/${taskId}`)
    },
    getAllTasksOfPeriod(periodId){
        return axios.get(`/task/period-tasks/${periodId}`)
    },
    createTask(taskModel){
        return axios.post("/task",taskModel)
    },
    removeTask(taskId){
        return axios.delete(`/task/${taskId}`)
    }
}