<template>
    <div>
        <div v-for="(dbTask, i) in dbTasks" :key="i">
            <input type="checkbox" v-model="selectedTasks" :value="dbTask">
            {{ dbTask.key }}
        </div>
        <button @click="removeDbTask()">Cikar</button>
    </div>
</template>

<script>
import dbTasksClient from "../clients/DbTasks"

export default {
    name: "dbTasksComponent",
    props: ['period'],
    data() {
        return { dbTasks: [], selectedTasks: [], dbTask: {}, message: "" }
    },
    mounted() {
        this.$root.$refs.dbTasksComponent = this;
        this.loadDbTasks();
    },
    watch: {
        'period'(newValue) {
            this.loadDbTasks(newValue.id);
        }
    },

    methods: {
        async loadDbTasks(periodId) {
            if (periodId != null) {
                const { data } = await dbTasksClient.getAllTasksOfPeriod(periodId);
                this.dbTasks = data;
            }

        },
        async removeDbTask() {
            for (let item of this.selectedTasks) {
                await dbTasksClient.removeTask(item.id);
            }
            this.selectedTasks = [];
            this.loadDbTasks(this.period.id);
            this.$root.$refs.JiraTaskComponent.loadJiraTasks(this.period.id);
        }
    }
}
</script>