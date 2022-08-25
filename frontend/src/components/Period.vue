<template>
    <div class="period">
       
        <div v-for="period in periods" :key="period">

         
         
         <button class="btn btn-danger btn-lg" size="lg"> {{period.name}}   {{period.state}}  {{period.cost}}  {{period.hourlyWage.name}}</button>
           
        </div>

    </div>
</template>

<script>
import Period from '@/clients/Period';
 
export default {
    name: "Period",
    
    data() {
        return { 
            periods: [], period: {}, message: "" 
        };
    },
    async mounted() {
        this.loadPeriods();
    },
    methods: {
        async loadPeriods() {
            const { data } = await Period.get();
            this.periods = data;
        },
        async create() {
            try {
                const { data } = await Period.create(this.period);
                this.period = {};
                this.loadPeriods();
            } catch (error) {
                this.message = "period oluşturulamadı";
            }
        },
        async update() {
            try {
                const { data } = await Period.update(this.period);
                this.period = {};
                this.loadPeriods();
            } catch (error) {
                this.message = "Period düzenlenemedi";
            }
        },
        async save(){
            if (this.period.id){
                this.update(this.period);
            }else{
                this.create(this.period)
            }
        },
        async remove(period) {
            try {
                await Period.remove(period.id);
                this.loadPeriods();
            } catch (error) {
                this.message="Silme işlemi başarısız";
            }
        },
        selectPeriod(period) {
            this.period = { ...period };
        }
    }
}
</script>




