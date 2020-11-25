<template>
    <div>
        <p>(You can double click on an item to turn it into a folder.)</p>
        <ul id="demo">
            <tree-item
                    class="item"
                    v-for="(root, index) in roots"
                    :key="index"
                    :folder="root">
            </tree-item>
        </ul>
    </div>
</template>

<script>
    import TreeItem from "../components/TreeItem";

    const axios = require('axios').default;

    export default {
        name: "FolderTree",
        components: {TreeItem},
        comments: {
            TreeItem
        },
        data: function() {
            return {
                roots: null
            };
        },
        methods: {
            getRoots: async function () {
                const response = await axios
                    .get(process.env.VUE_APP_HOST_URL + "/folder/parent")
                    .catch(error => console.log(error));
                return response.data;
            }
        },
        created() {
            this.getRoots().then(roots => this.roots = roots);
        }
    }
    
</script>
