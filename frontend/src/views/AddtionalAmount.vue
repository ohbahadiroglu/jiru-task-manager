<template>
    <div  class="Additional">
        <div v-for="additional in additionals" :key="additional.id">
            {{ additional.name }} {{ additional.amount }}
            <button @click=remove(additional) class="btn btn-danger btn-sm">sil</button>
            <button @click=selectAdditional(additional) class="btn btn-success btn-sm">düzenle </button>

        </div>

        <div >
            <input v-model="additional.name" placeholder="name">
            <input v-model="additional.amount" placeholder="amount">
            <select v-model="additional.period">
            <option v-for="period in periods" :value="period">{{ period.name }}</option>
            </select>
            <button @click="save()" class="btn btn-success">Kaydet</button>
        </div>
        {{ message }}
    </div>
</template>

<script>
import AdditionalAmount from "@/clients/AdditionalAmount";
import Period from "@/clients/Period";
export default {
    name: "AdditionalAmountView",
    data() {
        return { additionals: [], additional: {}, periods: [], message: "" };
    },
    mounted() {
        this.loadAdditional();
        this.loadPeriod();
    },
    methods: {
        async loadAdditional() {
            const { data } = await AdditionalAmount.get();
            this.additionals = data;
        },
        async loadPeriod() {
            const { data } = await Period.get();
            this.periods = data;
        },
        async create() {
            try {
                await AdditionalAmount.create(this.additional);
                this.additional = {};
                this.loadAdditional();

                this.message = "ekmaliyet oluştuurldu"
            } catch (error) {
                this.message = "ekmaliyet oluşturulmadı"
            }
        },
        async update() {
            try {
                await AdditionalAmount.update(this.additional);
                this.additional = {};
                this.loadAdditional();
            } catch (error) {
                this.message = "additional düzenlenemedi";
            }
        },

        async save() {
            if (this.additional.id) {
                this.update();
            } else {
                this.create()
            }
        },

        async remove(additional) {
            try {
                await AdditionalAmount.remove(additional.id);
                this.loadAdditional();
            } catch (error) {
                this.message = "Silme işlemi başarısız";
            }

        },
        selectAdditional(additional) {
            this.additional = { ...additional };
        }

    }

}
</script>