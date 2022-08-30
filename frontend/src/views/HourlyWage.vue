<template>
    <div class="HourlyWage">
        <div v-for="wage in wages" :key="wage.id">
            {{ wage.name }} {{ wage.amount }}
            <button @click="remove(wage)" class="btn btn-danger btn-sm">Sil</button>
            <button @click="selectWage(wage)" class="btn btn-success btn-sm">Düzenle</button>
        </div>

        <div>
            <input v-model="wage.name" placeholder="name">
            <input v-model="wage.amount" placeholder="amount">
            <button @click="save()" class="btn btn-success">Kaydet</button>
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

        async save(){
            if (this.wage.id){
                this.update(this.wage);
            }else{
                this.create(this.wage)
            }
        },

        async remove(wage) {
            try {
                await HourlyWage.remove(wage.id);
                this.loadWages();
            } catch (error) {
                this.message="Silme işlemi başarısız, period ile ilişkili olabilir";
            }

        },
        selectWage(wage) {
            this.wage = { ...wage };
        }

    }
}
</script>