<template>
  <div class="Additional" style="padding: 5px">

    <v-card>
      <v-card-title>Ek Maliyetler </v-card-title>
      <div>
        <v-simple-table v-if="additionals.length > 0">
          <template v-slot:default height="250">
            <thead>
              <tr>
                <th class="text-uppercase">Ek Maliyet Adı</th>
                <th class="text-center text-uppercase">Tutarı</th>
                <th class="text-center text-uppercase"></th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="additional in additionals" :key="additional.id">
                <td>
                  {{ additional.name }}
                </td>
                <td class="text-center">
                  {{ additional.amount }}
                </td>
                <td class="text-center">
                  <v-btn small color="error" class="mt-6" @click="remove(additional)"> Sil </v-btn>
                  <v-btn small color="primary" class="mt-6"
                    @click="selectAdditional(additional), $bvModal.show('modal-additional')">
                    Güncelle
                  </v-btn>
                </td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <div v-else class="alert alert-primary border-0">Ekmaliyet<strong> bulunmamaktadır</strong></div>
      </div>

      <!--Additional modal bölümü-->
      <v-btn block color="primary" class="mt-6" @click="$bvModal.show('modal-additional')">EKLE</v-btn>

      <b-modal id="modal-additional" title="Ek Maliyet">
        <v-text-field v-model="additional.name" outlined label="Ek maliyet adı" placeholder="Name 1" hide-details
          class="mb-3"></v-text-field>
        <v-text-field v-model="additional.amount" outlined label="Tutarı" placeholder="100₺" hide-details class="mb-3">
        </v-text-field>

        {{ message }}
        <template #modal-footer>
          <div class="float-right">
            <b-button variant="success" size="sm" @click="save()"> KAYDET </b-button>
            <b-button variant="danger" size="sm" @click="$bvModal.hide('modal-additional'), (message = '')">
              KAPAT
            </b-button>
          </div>
        </template>
      </b-modal>
    </v-card>
  </div>
</template>




<script>
import AdditionalAmount from '@/clients/AdditionalAmount'
export default {
  name: 'AdditionalAmountView',
  props: ['period'],
  data() {
    return { additionals: [], additional: {}, periods: [], showInputa: false, message: '' }
  },
  mounted() {
    this.$root.$refs.additionalComponent = this
  },
  watch: {
    period(newValue) {
      this.loadAdditional(newValue.id)
    },
  },
  methods: {
    async loadAdditional(id) {
      if (id != null) {
        const { data } = await AdditionalAmount.getAdditionalPeriod(id)
        this.additionals = data
        this.additional.period = this.period
        this.$emit('additionalsToParent', this.additionals)
        this.$root.$refs.PeriodComponent.setPeriodCost()
      } else {
        this.additionals = []
      }
    },

    async create() {
      try {
        await AdditionalAmount.create(this.additional)
        this.additional = {}
        this.loadAdditional(this.period.id)
        this.showInputa = !this.showInputa
        this.message = 'ekmaliyet oluştuurldu'
      } catch (error) {
        this.message = 'ekmaliyet oluşturulmadı'
      }
    },
    async update() {
      try {
        await AdditionalAmount.update(this.additional)
        this.additional = {}
        this.loadAdditional(this.period.id)
        this.showInputa = !this.showInputa
      } catch (error) {
        this.message = 'additional düzenlenemedi'
      }
    },

    async save() {
      this.additional.period = this.period
      if (this.additional.id) {
        this.update()
      } else {
        this.create()
      }
    },

    async remove(additional) {
      try {
        await AdditionalAmount.remove(additional.id)
        this.loadAdditional(this.period.id)
      } catch (error) {
        this.message = 'Silme işlemi başarısız'
      }
    },
    selectAdditional(additional) {
      this.additional = { ...additional }
    },
    olustur() {
      this.additional = {}
      this.showInputa = !this.showInputa
    },
  },
}
</script>