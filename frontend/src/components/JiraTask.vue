<template>
  <div>
    <v-card>
      <v-card-title>
        Döneme sadece statüsü done tasklar eklenebilir<br /><br />
        <v-spacer></v-spacer>
        <v-text-field v-model="search" :prepend-inner-icon="icons.mdiMagnify" rounded dense outlined label="Arama"
          single-line hide-details></v-text-field>
      </v-card-title>
      <v-data-table show-select v-model="selectedTasks" :headers="headers" :items="jiraTasks" item-key="key"
        :search="search" class="elevation-1" loading="myloadingvariable" loading-text="Yükleniyor... Lütfen bekleyiniz"
        group-by="status">
        <template #item.key="{ value }">
          <a :href="`${siteUrl}/browse/${value}`">
            {{ value }}
          </a>
        </template>
      </v-data-table>
    </v-card>
    <v-btn :disabled="!isAddBtnActive" block color="primary" class="mt-6" @click="createDbTask()"> Döneme Ekle
    </v-btn>
    <v-alert shaped prominent type="error" v-model="showAlert" dismissible> Sadece statüsü 'Done' olan tasklar
      eklenebilir</v-alert>
    <v-alert shaped prominent type="error" v-model="noPeriodAlert" dismissible> Lütfen bir dönem seçiniz </v-alert>
    <v-alert type="success" v-model="success" dismissible> Task/Tasklar başarılı bir şekilde eklenmiştir </v-alert>
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
      isAddBtnActive: true,
      success: false,
      noPeriodAlert: false,
      showAlert: false,
      myloadingvariable: true,
      icons: {
        mdiMagnify,
      },
      search: '',
      selectedTasks: [],
      taskModelList: [],
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
      siteUrl: "",
      headers: [
        {
          text: 'Task Key',
          align: 'start',
          sortable: true,
          value: 'key',
        },
        { text: 'Status', value: 'status', align: 'right' },
        { text: 'Açıklama', value: 'summary' },
        { text: 'Toplam Saat', value: 'totalWorkHours' },
      ],
    }
  },
  async mounted() {
    this.$root.$refs.JiraTaskComponent = this
    this.siteUrl = this.$route.query.siteUrl;
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
      this.isAddBtnActive = false
      if (Object.keys(this.period).length === 0) {
        console.log(this.noPeriodAlert)
        this.noPeriodAlert = true
      } else {
        for (let item of this.selectedTasks) {
          if (item.status === 'Done') {
            this.success = true
            this.taskModel.key = item.key
            this.taskModel.summary = item.summary
            this.taskModel.description = item.description
            this.taskModel.totalHours = item.totalWorkHours
            this.taskModel.period = this.period
            this.taskModelList.push(this.taskModel);
            this.taskModel = {};
          } else {
            this.showAlert = true
          }
        }
      }
      await DbTaskClient.createTask(this.taskModelList)
      this.taskModelList = [];
      this.selectedTasks = []
      this.loadJiraTasks()
      this.$root.$refs.dbTasksComponent.loadDbTasks(this.period.id)
      this.isAddBtnActive = true
    },
  },
}
</script>