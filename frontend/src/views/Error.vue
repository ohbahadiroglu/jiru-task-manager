<template>
  <div id="misc">
    <img
      class="misc-mask"
      height="226"
      :src="require(`@/assets/images/misc/misc-mask-${$vuetify.theme.dark ? 'dark' : 'light'}.png`)"
    />

    <v-img class="misc-tree" src="@/assets/images/misc/tree-4.png"></v-img>

    <div class="page-title text-center px-5">
      <h2 class="text-2xl font-weight-semibold text--primary d-flex align-center justify-center">
        <span class="me-2">Bir hata oluştu </span>
        <v-icon color="warning">
          {{ icons.mdiAlert }}
        </v-icon>
      </h2>
      <p class="text-sm">Lütfen URL,Token ve Emaili tekrar kontrol ediniz</p>
      <v-btn color="primary" to="/" class="mb-4"> Anasayfaya dön</v-btn>

      <div class="misc-character d-flex justify-center">
        <v-img
          max-width="400"
          src="https://media.istockphoto.com/photos/desperation-picture-id108826692?k=20&m=108826692&s=612x612&w=0&h=KWWkvTpcmQNRtnYziBh3y641xuXAYWjkhsOVnRG99jA="
        ></v-img>
      </div>
    </div>
  </div>
</template>

<script>
import { mdiAlert } from '@mdi/js'

export default {
  setup() {
    return {
      icons: {
        mdiAlert,
      },
    }
  },
}
</script>

<style lang="scss">
@import '~@/plugins/vuetify/default-preset/preset/misc.scss';
</style>
