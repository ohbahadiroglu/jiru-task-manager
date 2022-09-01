<template>
    <div class="period">
        <div class="d-flex justify-content-start mb-3">
            <select @click="onClickPeriod()" v-model="period">
                <option v-for="period in periods" :value="period" :key="period.id">{{ period.name }}</option>
            </select>
            <div>
                {{ period.name }} {{ period.state }}
                <button @click=remove(period) class="btn btn-danger btn-sm">sil</button>
                <button @click="(selectPeriod(period)), (showInput = !showInput)"
                    class="btn btn-success btn-sm">düzenle</button>
                <button @click="olustur" class="btn btn-primary btn-sm">Oluştur</button>
            </div>

        </div>

        <div class="d-flex justify-content-start mb-3" v-if="showInput">
            <input v-model="period.name" placeholder="name">
            <input v-model="period.state" placeholder="state">
            <select v-model="period.hourlyWage">
                <option v-for="hourlyWage in hourlyWages" :value="hourlyWage" :key="hourlyWage.id">{{ hourlyWage.name }}
                </option>
            </select>
            <button @click="save()" class="btn btn-success">Kaydet</button>
        </div>
        {{ message }}
        <DbTasks :period="period" />
        <AdditionalAmount :period="period" />
    </div>
</template>

<style>
.period {
    padding-left: 5%;
}
</style>

<script>
import Period from "@/clients/Period";
import HourlyWage from "@/clients/HourlyWage";
import AdditionalAmount from "./AddtionalAmount.vue";
import DbTasks from "@/components/DbTasks.vue";
export default {
    name: "PeriodView",
    components: { AdditionalAmount, DbTasks },
    data() {
        return {
            periods: [],
            period: {},
            hourlyWages: [],
            showInput: false,
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
                this.showInput = !this.showInput;
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
                this.showInput = !this.showInput;
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
        },
        onClickPeriod() {
            this.$emit('onClickPeriod', this.period)
        },
        olustur() {
            this.period = {};
            this.showInput = !this.showInput;
        }
    }

}
</script>
