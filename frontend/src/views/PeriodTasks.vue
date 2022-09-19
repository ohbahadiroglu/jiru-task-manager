<template>
    <div>
        <v-row no-gutters>
            <v-col>
                <PeriodComp @onClickPeriod="setPeriod" style="float:left; padding: 5px;" />
            </v-col>
            <v-col>
                <JiraTaskComp :period="this.period" style="float:right; padding: 5px;" />
            </v-col>
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
        return { period: {} }
    },
    methods: {
        setPeriod: function (value) {
            this.period = value;
        }
    }

}
</script>