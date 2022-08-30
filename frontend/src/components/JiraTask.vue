<template>
    <div>
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
        async mounted() {
            this.loadJiraTasks();
        },
        methods:{
            async loadJiraTasks(){
                this.jiraRequestModel.projectKey=this.$route.query.projectKey
                this.jiraRequestModel.siteId=this.$route.query.siteId
                const {data} =  await JiraTaskClient.getAllJiraTask(this.jiraRequestModel);
                console.log(data);
                this.jiraTasks= data;
            }
        }
    }
</script>