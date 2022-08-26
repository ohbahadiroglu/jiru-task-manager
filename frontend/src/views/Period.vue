<template>
    <div  class="period">
        <div class="d-flex justify-content-start mb-3">
            <select v-model="period">
                <option v-for="period in periods" :value="period">{{ period.name }}</option>
            </select>
        </div>
        
        <div  v-for="period in periods" :key="period.id">
            {{ period.name }} {{ period.state }} {{ period.hourlyWage.name }}
            <button @click=remove(period) class="btn btn-danger btn-sm">sil</button>
            <button @click=selectPeriod(period) class="btn btn-success btn-sm">düzenle </button>
        </div>

        <div >
            <input v-model="period.name" placeholder="name">
            <input v-model="period.state" placeholder="state">
            <select v-model="period.hourlyWage">
                <option v-for="hourlyWage in hourlyWages" :value="hourlyWage">{{ hourlyWage.name }}</option>
            </select>
            <button @click="save()" class="btn btn-success">Kaydet</button>
        </div>
        {{ message }}
        <AddtionalAmount />
    </div>
</template>

<script>
import Period from "@/clients/Period";
import HourlyWage from "@/clients/HourlyWage";
import AddtionalAmount from "./AddtionalAmount.vue";
export default {
    name: "PeriodView",
    components: { AddtionalAmount },
    data() {
        return {
            periods: [],
            period: {},
            hourlyWages: [],
            message: ""
        };
    },
    mounted() {
        this.loadPeriods();
        this.loadHourlyWage();
    },
    methods: {
        async loadPeriods() {
            const { data } = await Period.get();
            this.periods = data;
        },
        async loadHourlyWage() {
            const { data } = await HourlyWage.get();
            this.hourlyWages = data;
        },
        async create() {
            try {
                await Period.create(this.period);
                this.period = {};
                this.loadPeriods();
                this.message = "period oluşturuldu";
            }
            catch (error) {
                this.message = "period oluşturulamadı";
            }
        },
        async update() {
            try {
                await Period.update(this.period);
                this.period = {};
                this.hourlyWage = {};
                this.loadPeriods();
                this.message = "period güncellendi";
            }
            catch (error) {
                this.message = "periood güncellenemedi";
            }
        },
        async save() {
            if (this.period.id) {
                this.update();
            }
            else {
                this.create();
            }
        },
        selectPeriod(period) {
            this.period = { ...period };
        },
        async remove(period) {
            try {
                await Period.remove(period.id);
                this.loadPeriods();
                this.message = "period silindi";
            }
            catch (error) {
                this.message = "period silinemedi hata var";
            }
        }
    }

}
</script>
