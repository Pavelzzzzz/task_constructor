<template>
    <div>
        <hr>
        <div id="tree-div">
            <p>(You can double click on an item to turn it into a folder.)</p>
            <ul id="demo">
                <tree-item
                        class="item"
                        v-for="root in roots"
                        :key="root.id"
                        v-bind:folder="root">
                </tree-item>
            </ul>
        </div>
    </div>
</template>

<script>
    import TreeItem from "../components/TreeItem";
    import {UICheckbox, UIRow, UISpan, UITabbedPanel, UIText} from "../js/libs/ui";

    const axios = require('axios').default;

    export default {
        name: "FolderTree",
        components: {TreeItem},
        comments: {
            TreeItem
        },
        data: function() {
            return {
                roots: null,
                container: new UITabbedPanel(),
                folderTab: new UISpan()
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

            let parametersRow = new UIRow()
                .setPaddingTop( '10px' )
                .setPaddingLeft( '10px' )
                .setTextAlign('right');

            // show File checkbox
            let showFile = new UICheckbox('show file')
                .onChange(function () {
                    console.log("On change show file " + this.getValue());
                });
            parametersRow.add( new UIText('show file').setWidth( '100px' ));
            parametersRow.add(showFile);

            //show JSON checkbox
            let showJSON = new UICheckbox('show JSON')
                .onChange(function () {
                    console.log("On change show JSON " + this.getValue());
                });
            parametersRow.add( new UIText('show JSON').setWidth( '100px' ));
            parametersRow.add(showJSON);

            this.folderTab.add(parametersRow);
            this.container.addTab('folderTab', 'folder', this.folderTab);
        },
        mounted() {
            this.$el.prepend( this.container.dom );
        }
    }
</script>


<style>
    #tree-div{
        padding-left: 10px;
    }
</style>
