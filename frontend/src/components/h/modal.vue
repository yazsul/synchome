<script setup>
import {
  TransitionRoot,
  Dialog,
  DialogOverlay,
  TransitionChild,
} from "@headlessui/vue";


const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false,
  },
  class: String,
  wrapperClass: String,
  initialFocus: Boolean,
  bgClass: String,
  initialFocus: Boolean,
});
</script>

<template>
  <TransitionRoot as="template" :show="props.modelValue">
    <Dialog
      as="div"
      class="fixed z-40 inset-0 overflow-y-auto"
      :class="props.class"
    >
      <div
        class="flex items-center justify-center min-h-screen px-4 pb-20 text-center sm:block sm:p-0"
      >
        <TransitionChild
          as="template"
          enter="ease-out duration-300"
          enter-from="opacity-0"
          enter-to="opacity-100"
          leave="ease-in duration-200"
          leave-from="opacity-100"
          leave-to="opacity-0"
        >
          <DialogOverlay
            class="fixed inset-0 bg-gray-300 dark:bg-primary-dark/80 bg-opacity-75 transition-opacity"
          />
        </TransitionChild>

        <span
          class="hidden sm:inline-block sm:align-middle sm:h-screen"
          aria-hidden="true"
          >&#8203;</span
        >
        <TransitionChild
          as="template"
          enter="ease-out duration-300"
          enter-from="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
          enter-to="opacity-100 translate-y-0 sm:scale-100"
          leave="ease-in duration-200"
          leave-from="opacity-100 translate-y-0 sm:scale-100"
          leave-to="opacity-0 translate-y-4 sm:translate-y-0 sm:scale-95"
        >
          <div
            class="inline-block bg-transparent rounded-lg text-left overflow-auto transform transition-all my-8 md:align-middle align-top w-11/12"
            :class="bgClass ? bgClass : 'bg-white shadow-xl'"
          >
          <input
              v-if="!props.initialFocus"
              type="text"
              class="opacity-0 h-0"
            />

            <slot></slot>
          </div>
        </TransitionChild>
      </div>
    </Dialog>
  </TransitionRoot>
</template>
