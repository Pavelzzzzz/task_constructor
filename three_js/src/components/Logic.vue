<template>
    <div>
        <div clsss="desktop" ref="desktop"></div>
    </div>
</template>

<script>
    // import * as THREE from '../lib/three.js'
    import * as THREE from 'three'
    // import { GLTFLoader } from '../lib/GLTFLoader.js';
    import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader';

    export default {
        data: function (){
          return {
            scene: this.createSceneWithDefaultAttribute(),
            camera: null,
            render: null

        }},
        methods: {
            setUp: function () {
                let desktop = this.$refs.desktop;
                let aspect = desktop.offsetWidth / desktop.offsetHeight
                this.camera = new THREE.PerspectiveCamera(45, aspect, 1, 1000)
                this.camera.position.set(20, 20, 20)
                this.camera.lookAt(new THREE.Vector3(0, 0, 0))
                this.scene.add(this.camera)

                this.render = new THREE.WebGLRenderer()
                desktop.appendChild(this.render.domElement)
                this.render.setPixelRatio(window.devicePixelRatio)
                this.render.setSize(desktop.offsetWidth, desktop.offsetHeight)

                this.animate()
                console.log('set up finished')
            },
            createSceneWithDefaultAttribute: function (){
                let scene = new THREE.Scene()
                let axesHelper = new THREE.AxesHelper(500)
                scene.add(axesHelper)
                let light = new THREE.AmbientLight()
                scene.add(light)
                scene.background = new THREE.Color();
                return scene
            },
            animate: function(){
                requestAnimationFrame(this.animate)
                this.render.render(this.scene, this.camera)
            },
            import: function (path ) {
                let loader = new GLTFLoader();

                let scene = this.scene

                loader.load(path, function ( gltf ) {

                    scene.add( gltf.scene );

                }, undefined, function ( error ) {

                    console.error( error );

                } );
            }
        },
        mounted: function () {
            // console.log(this.scene)
            this.setUp()
            this.import('Cybertruck.glb')
            // this.import('scene.bin')
        }
    }
</script>



