<template>
    <div>
        <v-simple-table>
    <template v-slot:default height="250">
      <thead>
        <tr>
          <th class="text-uppercase">
           Task Key
          </th>
          <th class="text-center text-uppercase">
            Summary
          </th>
          <th class="text-center text-uppercase">
            Total Hours
          </th>
        </tr>
      </thead>
      <tbody>
        <tr
        v-for="(dbTask, i) in dbTasks" :key="i"
        >
          <td>
            <input type="checkbox" v-model="selectedTasks" :value="dbTask">
            {{ dbTask.key }}
          </td>
          <td class="text-center">
            {{ dbTask.summary }}
          </td>
          <td class="text-center">
            {{dbTask.totalHours }}
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
  <v-btn  block
              color="primary"
              class="mt-6" @click="removeDbTask()" >
              cikar
             </v-btn>
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