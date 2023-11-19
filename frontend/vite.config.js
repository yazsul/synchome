import { defineConfig, loadEnv } from "vite";
import vue from "@vitejs/plugin-vue";
import pages from "vite-plugin-pages";
import layout from "vite-plugin-vue-layouts";
import graphql from "@rollup/plugin-graphql";
import components from "unplugin-vue-components/vite";
import svgLoader from 'vite-svg-loader'
import vueI18n from '@intlify/unplugin-vue-i18n/vite';
import path from "path";

// https://vitejs.dev/config/
export default ({ mode }) => {
  const env = { ...loadEnv(mode, process.cwd()) };
  return defineConfig({
    server: {
      port: env.VITE_PORT,
    },
    resolve: {
      alias: {
        "@": path.resolve(__dirname, "./src"),
        "vue-i18n": "petite-vue-i18n/dist/vue-i18n.runtime.esm-bundler.js",
      },
    },
    plugins: [
      vue({
        script: {
          refSugar: true,
        },
      }),
      graphql(),
      components(),
      svgLoader(),
      pages(),
      layout(),
      vueI18n({
        include: path.resolve(__dirname, "./src/locales/**"),
      }),
    ],
  });
};
