<template>
  <div class="home">
    <h2>
      <fa icon="bug"></fa>
      İssues
    </h2>
    <div v-for="i in issues" :key="i.key">
      {{ i.key }}
    </div>

  </div>
</template>

<script>
import Test from "@/clients/Example";
export default {
  name: "HomeView",
  data() {
    return { issues: [] };
  },
  async mounted() {
    const { data } = await Test.get();
    this.issues = data;
  },
};
</script>
