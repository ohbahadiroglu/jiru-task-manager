<template>
    <div>
        <input v-model="jiraRequestModel.projectKey">
        <input v-model="jiraRequestModel.siteId">
        <button @click="loadJiraTasks">get jira tasks</button>
        <div v-for="(jiraTask,i) in jiraTasks" :key="i">
            {{jiraTask.summary}}
        </div>
    </div>
</template>
<script>
    import JiraTaskClient from "../clients/JiraTask"

    export default{
        name: "JiraTaskComponent",
        data(){
            return { jiraTasks: [], jiraTask: {},jiraRequestModel:{}, message: "" };
        },
        methods:{
            async loadJiraTasks(){
                console.log(this.jiraRequestModel.projectKey);
                console.log(this.jiraRequestModel.siteId);
                const {data} =  await JiraTaskClient.getAllJiraTask(this.jiraRequestModel);
                console.log(data);
                this.jiraTasks= data;
            }
        }
    }
</script>