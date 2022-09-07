<template>
  <div>
    <v-card>
      <v-card-title>
        Only Tasks with DONE status can be added to period<br /><br />
        <v-spacer></v-spacer>
        <v-text-field
          v-model="search"
          :prepend-inner-icon="icons.mdiMagnify"
          rounded
          dense
          outlined
          label="Search (Enter a number)"
          single-line
          hide-details
        ></v-text-field>
      </v-card-title>
      <v-data-table
        show-select
        v-model="selectedTasks"
        :headers="headers"
        :items="jiraTasks"
        item-key="key"
        :search="search"
        class="elevation-1"
        loading="myloadingvariable"
        loading-text="Loading... Please wait"
        group-by="status"
      >
      </v-data-table>
    </v-card>
    <v-btn block color="primary" class="mt-6" @click="createDbTask()"> Add to period </v-btn>
  </div>
</template>
<script>
import JiraTaskClient from '../clients/JiraTask'
import DbTaskClient from '@/clients/DbTasks'
import { mdiMagnify } from '@mdi/js'

export default {
  name: 'JiraTaskComponent',
  props: ['period'],
  data() {
    return {
      myloadingvariable: true,
      icons: {
        mdiMagnify,
      },
      search: '',
      selectedTasks: [],
      taskModel: {
        key: '',
        summary: '',
        description: '',
        totalHours: null,
        period: {},
      },
      jiraTasks: [],
      jiraTask: {},
      jiraRequestModel: {},
      message: '',
      headers: [
        {
          text: 'Task Key',
          align: 'start',
          sortable: true,
          value: 'key',
        },
        { text: 'Status', value: 'status', align: 'right' },
        { text: 'Summary', value: 'summary' },
        { text: 'Total Hours', value: 'totalHours' },
      ],
    }
  },
  async mounted() {
    this.$root.$refs.JiraTaskComponent = this
    this.loadJiraTasks()
  },
  methods: {
    async loadJiraTasks() {
      this.jiraRequestModel.projectKey = this.$route.query.projectKey
      this.jiraRequestModel.siteId = this.$route.query.siteId
      const { data } = await JiraTaskClient.getAllJiraTask(this.jiraRequestModel)
      this.jiraTasks = data
      this.myloadingvariable = false
    },

    async createDbTask() {
      console.log(this.selectedTasks)
      for (let item of this.selectedTasks) {
        this.taskModel.key = item.key
        this.taskModel.summary = item.summary
        this.taskModel.description = item.description
        this.taskModel.totalHours = item.totalWorkHours
        this.taskModel.period = this.period
        await DbTaskClient.createTask(this.taskModel)
      }
      this.selectedTasks = []
      this.loadJiraTasks()
      this.$root.$refs.dbTasksComponent.loadDbTasks(this.period.id)
    },
  },
}
</script>