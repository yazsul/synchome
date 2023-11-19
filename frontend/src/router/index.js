import { createWebHistory, createRouter } from "vue-router";
import { setupLayouts } from "virtual:generated-layouts";
import generatedRoutes from "virtual:generated-pages";
// import store from "@/store"
import useUser from "@/use/user";
import { useUserStore } from '@/stores/UserStore.js'

const { token } = useUser();

const routes = setupLayouts(generatedRoutes);

const history = createWebHistory();

const router = createRouter({
  history,
  routes,
  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) {
      return savedPosition;
    } else {
      return { top: 0 };
    }
  },
});

router.beforeEach((to) => {
  const onUserStore = useUserStore();

  // store.loading(true)
  if (!to.matched.length) {
    next("404");
  }
  if(to.meta.auth && !onUserStore.isLoggedIn) return '/'
});

router.afterEach(() => {
  // store.loading(false)
});

export default router;
