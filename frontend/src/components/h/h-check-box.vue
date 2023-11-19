<script setup>
import { watch, ref } from "vue";

const emit = defineEmits(["update:modelValue"]);

const props = defineProps({
  modelValue: {
    type: Boolean,
    default: false,
  },
  labelClass: {
    type: String,
    default: () => "",
    required: false,
  },
  name: String,
  label: String,
  description: String,
  hideDetails: Boolean,
  class: String,
});

const checkbox = ref(props.modelValue);

watch(
  () => props.modelValue,
  (newVal) => {
    checkbox.value = newVal || false;
  }
);

watch(
  () => checkbox.value,
  (newVal) => {
    emit("update:modelValue", newVal);
  }
);
</script>
<template>
  <div class="text-md" v-if="!props.hideDetails">&nbsp;</div>
  <div class="relative flex items-start">
    <div class="flex items-center h-5">
      <input
        v-model="checkbox"
        :id="name"
        :aria-describedby="name"
        :name="name"
        type="checkbox"
        class="focus:ring-primary text-primary border-primary-3 rounded text-md"
        :class="props.class"
      />
    </div>
    <div class="ml-3 text-md">
      <label :for="name" class="font-medium text-hahugray" :class="labelClass">
        {{ props.label }}
      </label>
      <p v-if="props.description" id="name" class="text-hahugray text-sm">
        {{ props.description }}
      </p>
    </div>
  </div>
</template>
