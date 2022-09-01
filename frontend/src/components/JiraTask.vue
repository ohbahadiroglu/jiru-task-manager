<template>
    <div>
        <div>
            <div v-for="(jiraTask, i) in jiraTasks" :key="i" v-if="jiraTask.status == 'Done'">
                <input type="checkbox" v-model="selectedTasks" :value="jiraTask">
                {{ jiraTask.key }}
            </div>
            <button @click="createDbTask()">Periyoda Ekle</button>
        </div>
    </div>
</template>
<script>
import JiraTaskClient from "../clients/JiraTask"
import DbTaskClient from "@/clients/DbTasks";

export default {
    name: "JiraTaskComponent",
    props: ['period'],
    data() {
        return {
            selectedTasks: [],
            taskModel: {
                summary: "",
                totalHours: null,
                key: "",
                period: {}
            },
            jiraTasks: [],
            jiraTask: {},
            jiraRequestModel: {},
            message: ""
        };
    },
    async mounted() {
        this.$root.$refs.JiraTaskComponent = this;
        this.loadJiraTasks();
    },
    methods: {
        async loadJiraTasks() {
            this.jiraRequestModel.projectKey = this.$route.query.projectKey
            this.jiraRequestModel.siteId = this.$route.query.siteId
            const { data } = await JiraTaskClient.getAllJiraTask(this.jiraRequestModel);
            this.jiraTasks = data;
        },

        async createDbTask() {
            for (let item of this.selectedTasks) {
                this.taskModel.key = item.key;
                this.taskModel.summary = item.summary;
                this.taskModel.totalHours = item.totalHours;
                this.taskModel.period = this.period;
                await DbTaskClient.createTask(this.taskModel);
            }
            this.selectedTasks = [];
            this.loadJiraTasks();
            this.$root.$refs.dbTasksComponent.loadDbTasks(this.period.id);
        }
    }
}
</script>