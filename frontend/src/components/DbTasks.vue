<template>
  <div style="padding: 5px">
    <v-card>
      <v-card-title>Döneme ait tasklar</v-card-title>
      <v-text-field v-model="search" :prepend-inner-icon="icons.mdiMagnify" rounded dense outlined label="Arama"
        single-line hide-details></v-text-field>
      <v-data-table show-select v-model="selectedTasks" :headers="headers" :items="dbTasks" item-key="key"
        :search="search" class="elevation-1">
        <template #item.key="{ value }">
          <a :href="`${siteUrl}/browse/${value}`">
            {{ value }}
          </a>
        </template>
      </v-data-table>

      <v-btn block color="primary" class="mt-6" @click="removeDbTask()"> Çıkar </v-btn>
    </v-card>
  </div>

</template>

<script>
import dbTasksClient from '../clients/DbTasks'
import { mdiMagnify } from '@mdi/js'

export default {
  name: 'dbTasksComponent',
  props: ['period'],
  data() {
    return {
      myloadingvariable: true,
      icons: {
        mdiMagnify,
      },
      search: '',
      dbTasks: [],
      siteUrl: '',
      selectedTasks: [],
      taskIdList: [],
      dbTask: {},
      message: '',
      siteUrl: "",
      headers: [
        {
          text: 'Task Key',
          align: 'start',
          sortable: true,
          value: 'key',
        },
        { text: 'Açıklama', value: 'summary' },
        { text: 'Toplam Saat', value: 'totalHours' },
      ],
    }
  },
  mounted() {
    this.$root.$refs.dbTasksComponent = this
    this.siteUrl = this.$route.query.siteUrl
  },
  watch: {
    period(newValue) {
      this.loadDbTasks(newValue.id)
    },
  },

  methods: {
    async loadDbTasks(periodId) {
      if (periodId != null) {
        const { data } = await dbTasksClient.getAllTasksOfPeriod(periodId)
        this.dbTasks = data
        this.$emit('dbTasksToParent', this.dbTasks)
        this.$root.$refs.PeriodComponent.setPeriodCost()
        this.myloadingvariable = false
      } else {
        this.dbTasks = []
      }
    },
    async removeDbTask() {
      for (let item of this.selectedTasks) {
        this.taskIdList.push(item.id)
      }
      await dbTasksClient.removeTask(this.taskIdList);
      this.taskIdList = []
      this.selectedTasks = []
      this.loadDbTasks(this.period.id)
      this.$root.$refs.JiraTaskComponent.loadJiraTasks(this.period.id)
    },
  },
}
</script>