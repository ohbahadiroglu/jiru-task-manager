<template>
    <div>
        <PeriodComp @onClickPeriod="myfunc" style="float:left" />
        <JiraTaskComp :period="this.period" style="float:right" />
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