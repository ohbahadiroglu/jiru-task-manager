import axios from "axios"

export default {
    getAllTasks() {
        return axios.get("/task");
    },
    getTaskById(taskId) {
        return axios.get(`/task/${taskId}`)
    },
    getAllTasksOfPeriod(periodId) {
        return axios.get(`/task/period-tasks/${periodId}`)
    },
    createTask(taskModelList) {
        return axios.post("/task", taskModelList)
    },
    removeTask(taskIdList) {
        return axios.post("/task/delete", taskIdList)
    }
}