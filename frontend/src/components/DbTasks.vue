<template>
    <div>
        <div v-for="dbTask in dbTasks" :key="dbTask.id">
            {{dbTask.summary}}
        </div>
    </div>
</template>

<script>
import dbTasksClient from "../clients/DbTasks"

export default {
    name: "dbTasksComponent",
    props: ['period'],
    data() {
        return { dbTasks: [], dbTask: {}, message: "" }
    },
    mounted() {
        this.loadDbTasks();
    },
    watch: {
        'period'(newValue) {
            this.loadDbTasks(newValue.id);
        }
    },

    methods: {
        async loadDbTasks(periodId) {
            const { data } = await dbTasksClient.getAllTasksOfPeriod(periodId);
            this.dbTasks = data;
        }
    }
}
</script>