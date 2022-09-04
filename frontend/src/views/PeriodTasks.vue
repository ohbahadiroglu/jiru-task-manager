<template>
    <div>
        <v-row no-gutters>
            <v-col> <PeriodComp @onClickPeriod="myfunc" style="float:left" /></v-col>
            <v-col><h1>Tasks from Jira </h1><JiraTaskComp :period="this.period" style="float:right"/></v-col>
        </v-row>
    </div>
</template>
<script>
import JiraTaskComp from "../components/JiraTask.vue";
import PeriodComp from "./Period.vue";

export default {
    name: "PeriodTasks",
    components: { PeriodComp, JiraTaskComp },
    data() {
        return { projectKey: "", siteId: null, period: {} }
    },
    async mounted() {
        this.projectKey = this.$route.query.projectKey;
        this.siteId = this.$route.query.siteId;

    },
    methods: {
        myfunc: function (value) {
            this.period = value;
        }
    }

}
</script>