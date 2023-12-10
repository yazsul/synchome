<script setup>
import { useDark, useToggle } from "@vueuse/core";
import { useRouter } from "vue-router";

const router = useRouter();
const isDark = useDark();
const toggleDark = useToggle(isDark);
</script>
<template>
  <div class="h-screen border text-slate-300">
    <div class="flex flex-col items-center justify-center h-full">
      <h1 class="text-4xl font-bold">Get Started with SyncH🥰me</h1>
      <p class="text-xl">Simplify Life with a Tap</p>
      <div class="flex flex-col items-center justify-center mt-8">
        <button @click="router.push('/login')" class="px-4 py-2 bg-pp-blue border hover:bg-slate-300 hover:text-pp-blue rounded-md hover:shadow-lg">Start Now</button>
      </div>
    </div>
  </div>
</template>
<style></style>
<route lang="yaml">
meta:
  layout: empty
</route>
