<template>
    <div>
        <h2> Import </h2>
        <router-link to="/">Home</router-link>
        <logic/>
    </div>
</template>

<script>
    import Logic from "../components/Logic";

    export default {
        name: "Import",
        components: {
            Logic
        },
        mounted() {
            // Logic.method.setUp()
        }
    }
</script>

<style scoped>

</style>