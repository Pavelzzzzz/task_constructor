import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/views/Home'

Vue.use(Router)

export default new Router({
    mode: 'history',
    routes: [{
        path: '/',
        component: Home
    },
        {
            path: '/import',
            component: () => import('./views/Import.vue')
        },
        {
            path: '/editor',
            component: () => import('./views/EditorPage.vue')
        },
        {
            path: '/folder/tree',
            component: () => import('./views/FolderTree.vue')
        }
    ]
})