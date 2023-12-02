<script>
import { useDark, useToggle } from "@vueuse/core";
const isDark = useDark();
const toggleDark = useToggle(isDark);
import useUser from "@/use/user";

export default {
  name: "address",
  display: "Address",
  order: 1,
  components: {},
  data() {
    return {
      streetNumber: "",
      city: "",
      country: "",
      postcode: "",
    };
  },
  computed: {},
  methods: {
    emitMyEvent() {
      const user = useUser();
      user.set({
        address: {
          streetNumber: parseInt(this.streetNumber),
          city: this.city,
          country: this.country,
          postcode: parseInt(this.postcode),
        },
      });
      this.$router.push("/build-house");
    },
  },
  mounted() {},
};
</script>
<template>
  <div class="h-screen">
    <div class="flex flex-col items-center justify-center h-full">
      <h1 class="text-4xl font-bold">Fill out this short form to get started!</h1>
      <div
        class="flex flex-col items-center justify-center mt-8 rounded-lg shadow shadow-white w-1/3 py-4"
      >
        <div class="w-3/4">
          <p class="text-xl text-center text-gray-300 mb-3">Address Info</p>
          <HTextField v-model="streetNumber" name="street" rules="required">
            <template #label>
              <span class="text-gray-300 font-medium">Street No.</span></template
            >
          </HTextField>
          <HTextField v-model="city" name="city" rules="required">
            <template #label>
              <span class="text-gray-300 font-medium">City</span></template
            >
          </HTextField>
          <HTextField v-model="country" name="Country" rules="required">
            <template #label>
              <span class="text-gray-300 font-medium">Country</span></template
            >
          </HTextField>
          <HTextField v-model="postcode" name="post" rules="required">
            <template #label>
              <span class="text-gray-300 font-medium">Post Code</span></template
            >
          </HTextField>
          <div class="text-center w-full">
            <button
              @click="emitMyEvent"
              class="px-4 py-2 bg-secondary text-pp-blue hover:bg-slate-200 rounded-md hover:shadow-lg w-3/4"
            >
              Next
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<route lang="yaml">
meta:
  layout: app
</route>
