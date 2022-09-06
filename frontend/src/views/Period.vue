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
           
            <v-col>
            <h1> 
                <v-select 
            v-model="period.state"
            label="Select a Fatura State" 
            :items="faturaStateOptions"  
            item-text="state"
            persistent-hint
            return-object
            single-line
             ></v-select> 
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
              Save (Create or Edit)
             </v-btn>
             <v-btn  block
              color="primary"
              class="mt-6" @click="olustur" >
              Oluştur
             </v-btn>
             {{ message }}
    </v-card>

    <!--COMPONENTS-->
<h1>
    Tasks from Period Database
</h1>
        <DbTasks :period="period" @dbTasksToParent="getDbTasks"/>
        
        <AdditionalAmount :period="period" @additionalsToParent="getAdditionals" />
        <!--FATURA MODAL-->
        <div>
            <b-button @click="$bvModal.show('modal-scrollable')">Fatura</b-button>
            <b-modal id="modal-scrollable" title="Fatura">
                <b-row class="mb-1 text-left">
                    <b-col>Task Key:</b-col>
                    <b-col>Tutar:</b-col>
                </b-row>
                <br>
                <div v-for="dbTask in dbTasksFromChild" :key="dbTask.id">
                    
                    <b-row class="mb-1 text-left">
                        <b-col>{{ dbTask.key }}</b-col>
                        <b-col>{{ (period.hourlyWage.amount * dbTask.totalHours).toFixed(2) }}</b-col>
                    </b-row>
                </div>
                <p>-------------------------------------------------------------------</p>
                <b-row class="mb-1 text-left">
                    <b-col>Ek Maaliyet Açıklaması:</b-col>
                    <b-col>Tutar:</b-col>
                </b-row>
                <br>
                <div v-for="additional in additionalsFromChild" :key="additional.id">
                    <b-row class="mb-1 text-left">
                        <b-col>{{ additional.name }}</b-col>
                        <b-col>{{ (additional.amount).toFixed(2) }}</b-col>
                    </b-row>
                </div>
                <p>-------------------------------------------------------------------
                   
                </p>
               
                <template #modal-footer>
                    <div class="w-100">
                        
                        <p style="float: left">Fatura kesme işlemi onaylansın mı?</p>
                        <export-excel :fields="excellFields" :data="excellData">
                            <b-button @click="createExcellData(),updatePeriodState(),$bvModal.hide('modal-scrollable')" variant="primary" style="float: right">Evet</b-button>
                        </export-excel>
                        <b-button @click="$bvModal.hide('modal-scrollable')" variant="danger" style="float: right">Hayır</b-button>
                    </div>

                </template>
                
                
            </b-modal>
        </div>
      
         
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
                faturaStateOptions:["KESILDI","KESILMEDI","ODENDI"],
                periods: [],
                period: {},
                hourlyWages: [],
                dbTasksFromChild: [],
                additionalsFromChild: [],
                excellData: [],
                showInput: false,
                message: "",
                excellFields: {
                'Key': 'key',
                'Açıklama': 'description',
                'Toplam Saat':'totalHours',
                'Tutar': 'cost'
                },
            };
        },
        mounted() {
            this.$root.$refs.PeriodComponent=this;
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
             getDbTasks(listValue) {
                this.dbTasksFromChild =   listValue;
            },
            async getAdditionals(listValue) {
                this.additionalsFromChild = await listValue;
            },
            async setPeriodCost() {
                const { data } = await Period.getById(this.period.id);
                this.period.cost = data.cost;
            },
            createExcellData(){
                this.excellData=[];
                const finalSum = {"cost":(this.period.cost)?.toFixed(2),"key":"toplam tutar"};
                for (let item of this.dbTasksFromChild){
                    let dbTask={'description':item.description,
                                'cost':(item.totalHours*this.period.hourlyWage.amount).toFixed(2),
                                'totalHours':item.totalHours.toFixed(2),
                                'key':item.key
                                };
                    this.excellData.push(dbTask);
                }
                for (let item of this.additionalsFromChild){
                    let additional={'description':item.name,
                                    'cost':(item.amount).toFixed(2),
                                    'totalHours':'-',
                                     'key':'EM'}
                    this.excellData.push(additional);
                }
                this.excellData.push(finalSum)
            },
            async updatePeriodState(){
                this.period.state="KESILDI";
                console.log(this.period)
                await Period.update(this.period);
                this.loadPeriods();
                this.message = "Fatura Kesildi";
            },
            async create() {
                try {
                    await Period.create(this.period);
                    /* this.period = {}; */  //SORRRRRRRRRRRRRR
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
                    /* this.period = {};
                    this.hourlyWage = {}; */   //SORRRRRRRRRRRRRR
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
                this.period =  {...period} ;
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
                this.$root.$refs.dbTasksComponent.loadDbTasks();
                this.$root.$refs.additionalComponent.loadAdditional();
                this.showInput = !this.showInput;
            }
        }
    
    }
    </script>