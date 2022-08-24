<template>
  <div class="site">

    <div v-for="i in sites" :key="i">
      {{ i.url }} {{ i.email }}

      <button @click="remove(i)" class="btn btn-danger btn-sm">Sil</button>
      <button @click="selectSite(i)" class="btn btn-success btn-sm">Düzenle</button>
    </div>

    <div>
      <input v-model="site.url" placeholder="site URL" />
      <input v-model="site.token" placeholder="site token" />
      <input v-model="site.email" placeholder="site email" />

      <button @click="save" class="btn btn-primary">Kaydet</button>

    </div>
    {{ message }}

  </div>
</template>

<script>
import Site from "@/clients/Site"


export default {
  name: "SiteView",
  data() {
    return { sites: [], site: {}, message: "" };
  },
  async mounted() {
    this.loadSites();
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

        this.message = ("Site kaydetme işlemi başarılı")
      } catch (error) {
        this.message = ("Hata var ");
      }

    },
    async update() {

      try {
        await Site.update(this.site);
        this.site = {};
        this.loadSites();

        this.message = ("Site güncelleme işlemi başarılı")
      } catch (error) {
        this.message = ("Hata var ");
      }
    },
    async remove(site) {
      try {
        await Site.remove(site.id);
        this.loadSites();

        this.message = ("Site silme işlemi başarılı")
      } catch (error) {
        this.message = ("Silinemedi Hata var ");
      }
    },
    async save() {
      if (this.site.id) {
        this.update();
      } else {
        this.create();
      }
    },
    selectSite(site) {
      this.site =  {...site} ;
    }

  }
}
</script>
