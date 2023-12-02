<script setup>
import { useDark, useToggle } from "@vueuse/core";
import { useRouter } from "vue-router";

const router = useRouter();
const isDark = useDark();
const toggleDark = useToggle(isDark);
</script>
<template>
  <div class="h-screen border">
    <div class="flex flex-col items-center justify-center h-full">
      <h1 class="text-4xl font-bold">Get Started with SyncH🥰me</h1>
      <p class="text-xl">Simplify Life with a Tap</p>
      <div class="flex flex-col items-center justify-center mt-8">
        <button @click="router.push('/address')" class="px-4 py-2 bg-secondary text-pp-blue hover:bg-slate-200 rounded-md hover:shadow-lg">Start Now</button>
      </div>
    </div>
  </div>
</template>
<style></style>
<route lang="yaml">
meta:
  layout: empty
</route>
