<template>
    <div class="Additional">
        <h3 style="padding-top:30px ;" class="d-flex justify-content-start mb-3">Addtional amount bölümü</h3>
        <button @click="olustur" class="d-flex justify-content-start mb-3 btn btn-primary btn-sm" >Oluştur</button>
        <div class="d-flex justify-content-start mb-3" v-for="additional in additionals" :key="additional.id">
            {{ additional.name }} {{ additional.amount }}
            <button @click=remove(additional) class="btn btn-danger btn-sm">sil</button>
            <button @click="(selectAdditional(additional)),(showInputa=!showInputa)" class="btn btn-success btn-sm">düzenle </button>
        </div>


        <div class="d-flex justify-content-start mb-3" v-if="showInputa">
            <input v-model="additional.name" placeholder="name">
            <input v-model="additional.amount" placeholder="amount">
            <!-- <select v-model="additional.period">
            <option v-for="period in periods" :value="period">{{ period.name }}</option>
            </select> -->
            <button @click="save()" class="btn btn-success">Kaydet</button>
            <b-button pill variant="outline-danger" @click="showInputa=!showInputa">X</b-button>
        </div>
        {{ message }}
    </div>
</template>
<style>

</style>
<script>
import AdditionalAmount from "@/clients/AdditionalAmount";
import Period from "@/clients/Period";
import { faSleigh } from "@fortawesome/free-solid-svg-icons";
export default {
    name: "AdditionalAmountView",
    /* props:{
      periodId:{
          default:1
      }
  }, */
    props: ['period'],
    data() {
        return { additionals: [], additional: {}, periods: [], showInputa: false, message: "", };
    },
    mounted() {
        console.log(this.period)
        this.loadAdditional();

    },
    watch: {
        'period'(newValue) {
            this.loadAdditional(newValue.id);
            console.log("period")
        }
    },
    methods: {
        async loadAdditional(id) {
            const { data } = await AdditionalAmount.getAdditionalPeriod(id);
            this.additionals = data;
            this.additional.period = this.period;
        },
        
        async create() {
            try {
                console.log(this.additional)
                await AdditionalAmount.create(this.additional);
                this.additional = {};
                this.loadAdditional(this.period.id);
                this.showInputa=!this.showInputa;
                this.message = "ekmaliyet oluştuurldu"
            } catch (error) {
                this.message = "ekmaliyet oluşturulmadı"
            }
        },
        async update() {
            try {
                console.log(this.period)
                await AdditionalAmount.update(this.additional);
                this.additional = {};
                this.loadAdditional(this.period.id);
                this.showInputa=!this.showInputa;
            } catch (error) {
                this.message = "additional düzenlenemedi";
            }
        },

        async save() {
            this.additional.period = this.period;
            if (this.additional.id) {
                this.update();
            } else {
                this.create()
            }
        },

        async remove(additional) {
            try {
                await AdditionalAmount.remove(additional.id);
                this.loadAdditional(this.period.id);
            } catch (error) {
                this.message = "Silme işlemi başarısız";
            }

        },
        selectAdditional(additional) {
            this.additional = { ...additional };
        },
        olustur() {
            this.additional = {};
            this.showInputa = !this.showInputa;
        }

    }

}
</script>