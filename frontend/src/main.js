import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/index.js";
import { ApolloClients } from "@vue/apollo-composable";
import { createPinia } from 'pinia';
import { apolloClient } from "./plugins/apollo";
import modal from "@/plugins/modal";
import VueDOMPurifyHTML from "vue-dompurify-html";
import i18n from "@/plugins/i18n";
import axios from 'axios';
import VueAxios from 'vue-axios';
import mitt from 'mitt'; // Import mitt

import "./index.css";
import "@/helpers/rules";

// Create pinia instance
const pinia = createPinia();

// Create mitt emitter instance
const emitter = mitt();

// Create Vue app instance
const app = createApp(App)
  .use(router)
  .use(pinia)
  .provide(ApolloClients, {
    default: apolloClient
  })
  .use(modal)
  .use(VueDOMPurifyHTML)
  .use(i18n)
  .use(VueAxios, axios);

// Assign the mitt emitter to the global properties
app.config.globalProperties.$emitter = emitter;

// Mount the Vue application
app.mount("#app");
