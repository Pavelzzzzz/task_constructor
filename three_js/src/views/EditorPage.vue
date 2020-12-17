<template>
    <div>
<!--        <h2> Editor </h2>-->
<!--        <router-link to="/">Home</router-link>-->
    </div>
</template>

<script>
    import { Editor } from '../js/Editor.js';
    import { Viewport } from '../js/Viewport.js';
    import { Sidebar } from "../js/Sidebar";
    import { Menubar } from '../js/Menubar.js';
    import { Toolbar } from '../js/Toolbar.js';

    import * as THREE from 'three';

    export default {
        name: "EditorPage",
        created() {
            Number.prototype.format = function () {

                return this.toString().replace( /(\d)(?=(\d{3})+(?!\d))/g, "$1," );

            };
        },
        mounted() {
            let editor = new Editor();
            let viewport = new Viewport( editor );
            this.$el.appendChild( viewport.dom );

            let sidebar = new Sidebar( editor );
            this.$el.appendChild( sidebar.dom );

            let toolbar = new Toolbar( editor );
            this.$el.appendChild( toolbar.dom );

            let menubar = new Menubar( editor );
            this.$el.appendChild( menubar.dom );

            editor.storage.init( function () {

                editor.storage.get( function ( state ) {

                    if ( isLoadingFromHash ) return;

                    if ( state !== undefined ) {

                        editor.fromJSON( state );

                    }

                    var selected = editor.config.getKey( 'selected' );

                    if ( selected !== undefined ) {

                        editor.selectByUuid( selected );

                    }

                } );

                //

                let timeout;

                function saveState() {

                    if ( editor.config.getKey( 'autosave' ) === false ) {

                        return;

                    }

                    clearTimeout( timeout );

                    timeout = setTimeout( function () {

                        editor.signals.savingStarted.dispatch();

                        timeout = setTimeout( function () {

                            editor.storage.set( editor.toJSON() );

                            editor.signals.savingFinished.dispatch();

                        }, 100 );

                    }, 1000 );

                }

                let signals = editor.signals;

                signals.geometryChanged.add( saveState );
                signals.objectAdded.add( saveState );
                signals.objectChanged.add( saveState );
                signals.objectRemoved.add( saveState );
                signals.materialChanged.add( saveState );
                signals.sceneBackgroundChanged.add( saveState );
                signals.sceneFogChanged.add( saveState );
                signals.sceneGraphChanged.add( saveState );
                signals.scriptChanged.add( saveState );
                signals.historyChanged.add( saveState );

            } );

            //

            document.addEventListener( 'dragover', function ( event ) {

                event.preventDefault();
                event.dataTransfer.dropEffect = 'copy';

            }, false );

            document.addEventListener( 'drop', function ( event ) {

                event.preventDefault();

                if ( event.dataTransfer.types[ 0 ] === 'text/plain' ) return; // Outliner drop

                if ( event.dataTransfer.items ) {

                    // DataTransferItemList supports folders

                    editor.loader.loadItemList( event.dataTransfer.items );

                } else {

                    editor.loader.loadFiles( event.dataTransfer.files );

                }

            }, false );

            function onWindowResize() {

                editor.signals.windowResize.dispatch();

            }

            window.addEventListener( 'resize', onWindowResize, false );

            onWindowResize();

            //

            var isLoadingFromHash = false;
            var hash = window.location.hash;

            if ( hash.substr( 1, 5 ) === 'file=' ) {

                var file = hash.substr( 6 );

                if ( confirm( 'Any unsaved data will be lost. Are you sure?' ) ) {

                    let loader = new THREE.FileLoader();
                    loader.crossOrigin = '';
                    loader.load( file, function ( text ) {

                        editor.clear();
                        editor.fromJSON( JSON.parse( text ) );

                    } );

                    isLoadingFromHash = true;

                }

            }

            // ServiceWorker

            // if ( 'serviceWorker' in navigator ) {
            //
            //     try {
            //
            //         navigator.serviceWorker.register( 'sw.js' );
            //
            //     } catch ( error ) {
            //         console.error("Error : ", error);
            //
            //     }
            //
            // }
        }
    }
</script>

<style>

</style>