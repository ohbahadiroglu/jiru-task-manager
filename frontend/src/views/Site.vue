<template>
    <div class="site">
        <v-card-text>
          <p class="text-2xl font-weight-semibold text--primary mb-2">
            Welcome to Phexum Task Manager! 👋🏻
          </p>
          <p class="mb-2">
            Please Add Site Information or use one of the exicting sites
          </p>
        </v-card-text>
  
      <div v-for="site in sites" :key="site.id">
      <router-link :to="{path:'/project',query:{siteId: site.id}}">
        <v-btn class="mt-6"
      outlined
      color="indigo"
      > URL : {{ site.url }} <br> Email :  {{ site.email }}</v-btn> 
        
    </router-link>
        
        <v-btn color="error"
              class="mt-6" @click="remove(site)">
              Delete
            </v-btn>
        
      </div>
      <div>
        <v-text-field
              v-model="site.url"
              :prepend-inner-icon="icons.mdiAccessPoint"
              outlined
              label="URL"
              placeholder="https://bocek.atlassian.net"
              hide-details
              class="mb-3"
            ></v-text-field>
            <v-text-field
              v-model="site.token"
              :prepend-inner-icon="icons.mdiLockOutline"
              outlined
              label="Token"
              placeholder="ASDssad2231"
              hide-details
              class="mb-3"
            ></v-text-field>
            <v-text-field
              v-model="site.email"
              :prepend-inner-icon="icons.mdiEmailOutline"
              outlined
              label="Email"
              placeholder="juju@gmail.com"
              hide-details
              class="mb-3"
            ></v-text-field>
            
    
        <v-btn  block
              color="primary"
              class="mt-6" @click="save()" >
              Save
            </v-btn>
  
      </div>
      {{ message }}
    </div>
  </template>
  
  <script>
  import Site from "@/clients/Site"  
  import { mdiAccountOutline, mdiEmailOutline, mdiCellphone, mdiLockOutline ,mdiAccessPoint} from '@mdi/js'
  import ErrorPage from "../views/Error.vue"
import router from '@/router'


  
  export default {
      name: "SiteView",
      data() {
          return { sites: [], site: {}, message: "" };
      },
      async mounted() {
          this.loadSites();
      },
      setup(){
        return {
      // icons
      icons: {
        mdiAccountOutline,
        mdiEmailOutline,
        mdiCellphone,
        mdiLockOutline,
        mdiAccessPoint,
      },
    }
      },
      
      methods: {
          async loadSites() {
              const { data } = await Site.get();
              this.sites = data;
          },
          async create() {
              try {
                  await Site.create(this.site);
                  this.site = {};
                  this.loadSites();
                  this.message = ("Site kaydetme işlemi başarılı");
              }
              catch (error) {
                  this.message = ("Hata var ");
                 router.push("error-404")
              }
          },
          async update() {
              try {
                  await Site.update(this.site);
                  this.site = {};
                  this.loadSites();
                  this.message = ("Site güncelleme işlemi başarılı");
              }
              catch (error) {
                  this.message = ("Hata var ");
                 
                  
              }
          },
          async remove(site) {
              try {
                  await Site.remove(site.id);
                  this.loadSites();
                  this.message = ("Site silme işlemi başarılı");
              }
              catch (error) {
                  this.message = ("Silinemedi Hata var ");
                
              }
          },
          async save() {
              if (this.site.id) {
                  this.update();
              }
              else {
                  this.create();
              }
          },
          selectSite(site) {
              this.site = { ...site };
          }
      },
      
      
  }
  </script>