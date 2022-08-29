<template>
    <div class="project">
        <div v-for="project in projects" :key="project.id">
            {{project.name}} {{project.key}} 
        </div>
    </div>
</template>
<script>
    import Project from "@/clients/Project";

    export default{
        name:"ProjectView",
        data(){
            return {projects: [], message: "", siteId: null}
        },
        async mounted(){
            this.siteId=this.$route.query.siteId;
            this.loadProjects(this.siteId);
        }  ,
        methods:{
            async loadProjects(siteId){
                const {data} = await Project.get(siteId);
                this.projects = data;
            }
        }

    }
</script>