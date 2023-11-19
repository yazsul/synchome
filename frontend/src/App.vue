<script setup>
// import { useDark, useToggle } from "@vueuse/core";
// const isDark = useDark({
//   onChanged(dark) {
//     // update the dom, call the API or something
//     console.log("dark is", dark);
//     if (dark) {
//       console.log("here", dark);
//       let doc = document.getElementById("main-html");
//       console.log("the element is", doc);
//       document.getElementById("main-html").className = "dark";
//     }
//   },
// });
</script>

<template>
  <router-view class="h-full"></router-view>
  <div class="fixed px-3 py-2 text-sm font-bold text-white bg-gray-900 rounded bottom-4 left-4 z-50">
    <span class="xs:hidden">DEFAULT</span>
    <span class="hidden xs:inline-block sm:hidden">XS</span>
    <span class="hidden sm:inline-block md:hidden">SM</span>
    <span class="hidden md:inline-block lg:hidden">MD</span>
    <span class="hidden lg:inline-block xl:hidden">LG</span>
    <span class="hidden xl:inline-block 2xl:hidden">XL</span>
    <span class="hidden 2xl:inline-block 3xl:hidden">2XL</span>
    <span class="hidden 3xl:inline-block">3XL</span>
  </div>
</template>
<style></style>
