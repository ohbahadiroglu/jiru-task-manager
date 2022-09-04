<template>
    <div class="period">
        <v-card>
    <v-card-title>Tasks & Periods</v-card-title>
    <v-row class="ma-0 pb-5 px-5">
        <v-col>
        <h1> 
            <v-select 
            @change="onClickPeriod()"
            v-model="period"
            :hint="`${period.name}, ${period.cost}`"
            label="Select a Period" 
            :items="periods"  
            item-text="name"
            persistent-hint
            return-object
            single-line
             ></v-select>         
        </h1>
       
     </v-col>
     
    <v-col>
        <v-btn 
                color="error"
                @click=remove(period)
                class="mt-6">
                Delete
                </v-btn>
    </v-col>
    </v-row>  
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
            <v-col>
            <h1> 
            <v-select 
            label="Select an Hourly Wage to be added to period" 
            :items="hourlyWages"  
            v-model="period.hourlyWage"
            item-text="name"
            return-object
             ></v-select>
        </h1>
    </v-col>
             <v-btn  block
              color="primary"
              class="mt-6" @click="save()" >
              Save
             </v-btn>
             {{ message }}
    </v-card>
<h1>
    Tasks from Period Database
</h1>
        <DbTasks :period="period"/>
        
        <AdditionalAmount :period="period"/>
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
