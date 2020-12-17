<template>
        <li>
            <div
                    :class="{bold: isFolder}"
                    @click="toggle"
                    @dblclick="makeFolder">
                {{ folder.title }}
                <span v-if="isFolder">[{{ isOpen ? '-' : '+' }}]</span>
            </div>
            <ul v-show="isOpen" v-if="isFolder">
                <tree-item
                        class="item"
                        v-for="child in children"
                        :key="child.id"
                        v-bind:folder="child"
                ></tree-item>
                <li class="add" @click="makeFolder">+</li>
            </ul>
        </li>
</template>

<script>
    const axios = require('axios').default;

    export default {
        name: "TreeItem",
        props: {
            folder: {
                type: Object,
                default (){
                    return {}
                }
            }
        },
        data: function() {
            return {
                isOpen: false,
                children: null
            };
        },
        computed: {
            isFolder: function() {
                return this.children && this.children.length;
            }
        },
        methods: {
            toggle: function() {
                if (this.isFolder) {
                    this.isOpen = !this.isOpen;
                }
            },
            makeFolder: function() {
                axios
                    .post(process.env.VUE_APP_HOST_URL + "/folder", {
                        "title": "new folder",
                        "parentId": this.folder.id
                    })
                    .then(response => this.children.push(response.data))
                    .catch(error => console.log(error));
            },
            getChildren: function() {
                axios
                    .get(process.env.VUE_APP_HOST_URL + "/folder/parent", {
                        params: {
                            parentId: this.folder.id
                        }
                    })
                    .then(response => this.children = response.data)
                    .catch(error => console.log(error));
            }
        },
        created() {
            this.getChildren()
        }
    }
</script>

<style scoped>

</style>