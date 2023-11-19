import { createI18n } from "petite-vue-i18n";

import messages from "@intlify/unplugin-vue-i18n/messages";
export default createI18n({
  legacy: false,
  locale: "en",
  messages,
  fallbackLocale: "en",
  globalInjection: true,
});
