<template>
    <div>
        <PeriodComp style="float:left"/>
        <JiraTaskComp style="float:right"/>
    </div>
</template>
<script>
import JiraTaskComp from "../components/JiraTask.vue";
import PeriodComp from "./Period.vue";

export default{
    name:"PeriodTasks",
    components: {PeriodComp,JiraTaskComp},
    data(){
        return {projectKey:"",siteId: null}
    },
    async mounted() {
        this.projectKey = this.$route.query.projectKey;
        this.siteId = this.$route.query.siteId;
        console.log(this.projectKey);
        console.log(this.siteId);

    },

}
</script>