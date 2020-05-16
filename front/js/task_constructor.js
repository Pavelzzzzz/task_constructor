window.onload = function () {
    Constructor.init();
}

let Constructor = {
    createScene: function () {
        let scene = new THREE.Scene();
        window.scene = scene;

        let axesHelper = new THREE.AxesHelper(500);
        scene.add(axesHelper);

        let light = new THREE.AmbientLight();
        scene.add(light);

        let geometry = new THREE.SphereGeometry(5, 20, 20);
        // let material = new THREE.MeshStandardMaterial({color: 0x00ff00, wireframe: true});
        let material = new THREE.MeshPhongMaterial({color: 0x00ff00, wireframe: true});
        let mesh = new THREE.Mesh(geometry, material);
        this.sphere = mesh;
        scene.add(mesh);

        let cube = new THREE.BoxGeometry();
        let mesh2 = new THREE.Mesh(cube, material);
        scene.add(mesh2);

        return scene;
    },

    animate: function (render, scene, camera) {
        requestAnimationFrame(this.animate.bind(this, render, scene, camera));
        // this.controls.update();
        this.sphere.rotation.x += 0.005;
        this.sphere.rotation.y += 0.005;
        this.sphere.rotation.z += 0.005;
        render.render(scene, camera);
    },

    createRender: function (workarea, width, height) {
        let render = new THREE.WebGLRenderer();
        workarea.appendChild(render.domElement);
        render.setPixelRatio(window.devicePixelRatio)
        render.setSize(width, height);
        return render;
    },

    createPerspectiveCamera: function(aspect){
        let camera = new THREE.PerspectiveCamera(45, aspect, 1, 1000);
        return camera;
    },

    init: function () {
        this.scene = this.createScene();
        let container = document.getElementsByClassName('webgl')[0];
        let aspect = container.offsetWidth / container.offsetHeight;

        let camera_top = this.createPerspectiveCamera(aspect);
        camera_top.position.y = 20;
        camera_top.lookAt(new THREE.Vector3(0,0,0));
        scene.add(camera_top);

        let camera_front = this.createPerspectiveCamera(aspect);
        camera_front.position.z = 20;
        camera_front.lookAt(new THREE.Vector3(0,0,0));
        scene.add(camera_front);

        let camera_right = this.createPerspectiveCamera(aspect);
        camera_right.position.x = 20;
        camera_right.lookAt(new THREE.Vector3(0,0,0));
        scene.add(camera_right);

        let camera_3d = this.createPerspectiveCamera(aspect);
        camera_3d.position.x = 20;
        camera_3d.position.y = 20;
        camera_3d.position.z = 20;
        camera_3d.lookAt(new THREE.Vector3(0,0,0));
        scene.add(camera_3d);

        let workarea_width = container.offsetWidth/2.01;
        let workarea_height = container.offsetHeight/2;
        let render_top = this.createRender(container.querySelectorAll('#workarea_top')[0], workarea_width, workarea_height);
        let render_front = this.createRender(container.querySelectorAll('#workarea_front')[0], workarea_width, workarea_height);
        let render_right = this.createRender(container.querySelectorAll('#workarea_right')[0], workarea_width, workarea_height);
        let render_3d = this.createRender(container.querySelectorAll('#workarea_3d')[0], workarea_width, workarea_height);

        this.animate(render_top, scene, camera_top);
        this.animate(render_front, scene, camera_front);
        this.animate(render_right, scene, camera_right);
        this.animate(render_3d, scene, camera_3d);
    }
}