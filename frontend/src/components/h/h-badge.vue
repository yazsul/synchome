<script setup>
import { ref } from "vue";
import { XMarkIcon, PlusIcon } from "@heroicons/vue/24/outline";
const emit = defineEmits(["selectedType", "removeSelectedType"]);
const props = defineProps({
  type: {
    type: Object,
    required: true,
  },
});

const passedType = ref({ ...props.type });

const selectType = (type) => {
  type.selected = !type.selected;
  if (type.selected) {
    emit("selectedType", type);
  } else {
    emit("removeSelectedType", type.index);
  }
};
</script>
<template>
  <button
    @click="selectType(passedType)"
    class="inline-flex items-center border border-transparent px-2 py-0.5 rounded-full text-base font-normal text-secondary dark:text-secondary-8"
    :class="[
      passedType.selected
        ? 'bg-primary-4 dark:text-secondary'
        : 'border border-primary',
    ]"
  >
    {{ passedType.name }}
    <button
      type="button"
      class="flex-shrink-0 ml-0.5 h-3 w-3 rounded-full inline-flex items-center justify-center text-primary dark:stroke-secondary-8"
      :class="[passedType.selected ? '' : '']"
    >
      <XMarkIcon v-if="passedType.selected"/>
      <PlusIcon v-else />
    </button>
  </button>
</template>
