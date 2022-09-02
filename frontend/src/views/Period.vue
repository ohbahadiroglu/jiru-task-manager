<template>
    <div class="period">
        <v-card>
    <v-card-title>Tasks & Periods</v-card-title>
    <v-row class="ma-0 pb-5 px-5">
        <v-col>
        <h1> 
            <v-select 
            label="Select a Period" 
            :items="periods"  
            v-model="period"
            item-text="name"
            return-object
            @click="onClickPeriod()"
             ></v-select>
        </h1>
    </v-col>
    <v-col>
        <h1> </h1>
    </v-col>
    </v-row>
    <div>
                <v-btn 
                color="error"
                @click=remove(period)
                class="mt-6">
                Delete
                </v-btn>
                <v-btn 
                color="primary"
                @click="(selectPeriod(period)), (showInput = !showInput)"
                class="mt-6">
                Edit
                </v-btn>
                <v-btn 
                color="warning"
                @click="olustur"
                class="mt-6">
                Create
                </v-btn>
                <v-text-field
              v-model="period.name"
              outlined
              label="Period Name"
              placeholder="Period Name"
              hide-details
              class="mb-3"
            ></v-text-field>
            <v-text-field
              v-model="period.state"
              outlined
              label="Period State"
              placeholder="Period State"
              hide-details
              class="mb-3"
            ></v-text-field>
            <select v-model="period.hourlyWage">
                <option v-for="hourlyWage in hourlyWages" :value="hourlyWage" :key="hourlyWage.id">{{ hourlyWage.name }}</option>
            </select>
        <v-btn  block
              color="primary"
              class="mt-6" @click="save()" >
              Save
            </v-btn>
        </div>
  </v-card> 
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
         olustur(){
           this.period={};
           this.showInput=!this.showInput;
       } 
    }

}
</script>
