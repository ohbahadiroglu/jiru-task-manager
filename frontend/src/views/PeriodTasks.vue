<template>
    <div>
        <v-card-text>
          <p class="text-2xl font-weight-semibold text--primary mb-2">
           Tasks Page !
          </p>
          <p class="mb-2">
            Click on periods to veiw and edit periods then added desired tasks to periods
          </p>
        </v-card-text>
        <PeriodComp style="float:left"/>
        <JiraTaskComp style="float:right"/>
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