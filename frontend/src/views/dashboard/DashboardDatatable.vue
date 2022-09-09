<template>
  <v-card>
    <v-data-table
      :headers="headers"
      :items="usreList"
      item-key="full_name"
      class="table-rounded"
      hide-default-footer
      disable-sort
    >
      <!-- name -->
      <template #[`item.full_name`]="{ item }">
        <div v-for="site in sites" :key="site.id">
          <router-link :to="{ path: '/project', query: { siteId: site.id } }"
            >{{ site.url }} {{ site.email }}</router-link
          >>

          <button @click="selectSite(site)" class="btn btn-success btn-sm">Düzenle</button>
        </div>
        <div class="d-flex flex-column">
          <span class="d-block font-weight-semibold text--primary text-truncate">{{ item.full_name }}</span>
          <small>{{ item.post }}</small>
        </div>
      </template>

      <!-- status -->
      <template #[`item.status`]="{ item }">
        <v-chip big :color="statusColor[status[item.status]]" class="font-weight-medium">
          <button @click="remove(site)" class="btn btn-danger btn-sm">Sil</button>
        </v-chip>
      </template>
    </v-data-table>
  </v-card>
</template>

<script>
import { mdiSquareEditOutline, mdiDotsVertical } from '@mdi/js'

import data from './datatable-data'
import Site from '@/clients/Site'

export default {
  setup() {
    const statusColor = {
      /* eslint-disable key-spacing */

      Delete: 'error',

      /* eslint-enable key-spacing */
    }

    return {
      headers: [
        { text: 'URL', value: 'full_name' },
        { text: 'Token', value: 'email' },
        { text: 'Email', value: 'start_date' },
        { text: 'Options', value: 'status' },
      ],
      usreList: data,
      status: {},
      statusColor,

      // icons
      icons: {
        mdiSquareEditOutline,
        mdiDotsVertical,
      },
    }
  },
}
</script>
