<template>
    <div>
        <v-simple-table height="500">
    <template v-slot:default>
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
        v-for="(jiraTask, i) in jiraTasks" :key="i" 
        v-if="jiraTask.status == 'Done'" 
        >
       
          <td>
            <input type="checkbox" v-model="selectedTasks" :value="jiraTask">
            {{ jiraTask.key }}
          </td>
          <td class="text-center">
            {{ jiraTask.summary }}
          </td>
          <td class="text-center">
            {{jiraTask.totalHours }}
          </td>
        </tr>
      </tbody>
    </template>
  </v-simple-table>
            <v-btn  
            block
            color="primary"
            class="mt-6" @click="createDbTask()" >
            Add to period
            </v-btn>
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
                  this.taskModel.description=item.description;
                  this.taskModel.totalHours = item.totalWorkHours;
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