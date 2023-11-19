<script setup>
import { ref } from 'vue'
import { ChevronDownIcon } from '@heroicons/vue/24/outline';

const isOpen = ref(true);

const toggleAccordion = () => {
  isOpen.value = !isOpen.value;
};
</script>

<template>
  <div class="mt-5 space-y-4">
    <button
      @click="toggleAccordion"
      class="flex items-center justify-between space-x-3 w-full px-3"
      :aria-expanded="isOpen"
    >
      <div class="flex">
        <slot name="title" />
      </div>
      <ChevronDownIcon
        class="w-6 transition-all duration-200 transform text-black dark:text-secondary-8"
        :class="{
          'rotate-0  md:rotate-180': isOpen,
          'rotate-180 md:rotate-0': !isOpen,
        }"
      />
    </button>
    <div class="">
      <div v-show="isOpen" class="w-full transform transition-transform duration-1000 ease-in-out">
        <slot name="content" />
      </div>
    </div>

    <!-- <div class="block md:hidden">
      <div v-show="!isOpen" class="w-full">
        <slot name="content" />
      </div>
    </div> -->
  </div>
</template>
