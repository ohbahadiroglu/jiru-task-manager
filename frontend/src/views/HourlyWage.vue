<template>
  <div class="HourlyWage">
    <v-card-text>
      <p class="text-2xl font-weight-semibold text--primary mb-2">
        Adam saatlik ücret tarifesi
      </p>
      <p class="mb-2">
        Lütfen adam saatlik ücret tarifesi bilgilerini doldurunuz.
      </p>
    </v-card-text>
    <v-simple-table>
      <template v-slot:default height="250">
        <thead>
          <tr>
            <th class="text-uppercase">
              ücret tarifesinin adı
            </th>
            <th class="text-center text-uppercase">
              Ücret tarifesinin tutarı
            </th>
            <th class="text-center text-uppercase">
            </th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="wage in wages" :key="wage.id">
            <td>
              {{ wage.name }}
            </td>
            <td class="text-center">
              {{ wage.amount }}
            </td>
            <td>
              <v-btn color="error" class="mt-3" @click="remove(wage)">
                Sil
              </v-btn>
              <v-btn color="primary" class="mt-3" @click="selectWage(wage)">
                Güncelle
              </v-btn>
            </td>
          </tr>
        </tbody>
      </template>
    </v-simple-table>
    <div style="padding: 10px;">
      <v-text-field v-model="wage.name" outlined label="ücret tarifesinin adı" placeholder="tarifenin adı" hide-details
        class="mb-3">
      </v-text-field>
      <v-text-field v-model="wage.amount" outlined label="ücret tarifesinin tutarı" placeholder="100₺" hide-details
        class="mb-3">
      </v-text-field>
      <v-btn block color="primary" class="mt-6" @click="save()">
        Kaydet
      </v-btn>
    </div>
    {{ message }}
  </div>
</template>
<script>
import HourlyWage from "@/clients/HourlyWage";

export default {
  name: "HourlyWageView",
  data() {
    return { wages: [], wage: {}, message: "" };
  },
  async mounted() {
    this.loadWages();
  },
  methods: {
    async loadWages() {
      const { data } = await HourlyWage.get();
      this.wages = data;
    },
    async create() {
      try {
        const { data } = await HourlyWage.create(this.wage);
        this.wage = {};
        this.loadWages();
      } catch (error) {
        this.message = "Wage oluşturulamadı";
      }
    },
    async update() {
      try {
        const { data } = await HourlyWage.update(this.wage);
        this.wage = {};
        this.loadWages();
      } catch (error) {
        this.message = "Wage düzenlenemedi";
      }
    },

    async save() {
      if (this.wage.id) {
        this.update(this.wage);
      } else {
        this.create(this.wage)
      }
    },

    async remove(wage) {
      try {
        await HourlyWage.remove(wage.id);
        this.loadWages();
      } catch (error) {
        this.message = "Silme işlemi başarısız, period ile ilişkili olabilir";
      }

    },
    selectWage(wage) {
      this.wage = { ...wage };
    }

  }
}
</script>