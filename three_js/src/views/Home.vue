<template>
    <div id="home">
        <img alt="Vue logo" src="../assets/logo.png">
        <h2>Home page</h2>
        <router-link to="/import">Import</router-link> <br>
        <router-link to="/editor">Editor</router-link> <br>
        <router-link to="/folder/tree">Folder tree</router-link>
    </div>
    
</template>

<style scoped>
    #home {
        font-family: Avenir, Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osx-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        /*margin-top: 60px;*/
    }
    * {
        margin: 0;
        padding: 0;
        overflow: hidden;
    }

    html, body, div {
        height: 100%;
        width: 100%;
    }
</style>
