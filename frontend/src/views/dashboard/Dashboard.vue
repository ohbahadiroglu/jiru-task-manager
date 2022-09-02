<template>
  <v-row>
    <v-col cols="12">
      <Site></Site>
    </v-col>
  </v-row>
</template>

<script>
// eslint-disable-next-line object-curly-newline
import { mdiPoll, mdiLabelVariantOutline, mdiCurrencyUsd, mdiHelpCircleOutline } from '@mdi/js'
// demos
import DashboardDatatable from './DashboardDatatable.vue'
import Site from '../Site.vue'

export default {
  components: {
    
    Site
},
  setup() {
   
    // vertical card options
    
    return {
      
    }
  },
}
</script>
