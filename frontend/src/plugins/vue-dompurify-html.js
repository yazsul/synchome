import VueDOMPurifyHTML from "vue-dompurify-html";
export default {
  install(app) {
    app.use(VueDOMPurifyHTML);
  },
};
