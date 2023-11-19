import { createApp } from "vue";
import App from "./App.vue";
import router from "./router/index.js";
import { ApolloClients } from "@vue/apollo-composable";
// import { SetupCalendar } from 'v-calendar';
import {createPinia} from 'pinia'
// import { VueReCaptcha } from "vue-recaptcha-v3";
import { apolloClient } from "./plugins/apollo"
import modal from "@/plugins/modal";
import mitt from "@/plugins/mitt";

import VueDOMPurifyHTML from "vue-dompurify-html";
import i18n from "@/plugins/i18n";
import "./index.css";
import "@/helpers/rules";
// import 'v-calendar/dist/style.css';

const pinia = createPinia();


const app = createApp(App)
  .use(router)
  .use(pinia)
  .use(mitt)
  .provide(ApolloClients, {
    default: apolloClient
  })
  // .use(SetupCalendar, {})
  // .use(VueReCaptcha, { siteKey: import.meta.env.VITE_RECAPATCHA_SITE_KEY })
  .use(modal)
  .use(VueDOMPurifyHTML)
  .use(i18n)
  .mount("#app");