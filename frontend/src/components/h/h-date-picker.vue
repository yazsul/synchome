<script setup>
import { ref, watch, toRefs } from "vue";
import { useField } from "vee-validate";
import { useDark, useToggle } from "@vueuse/core";
const props = defineProps({
  modelValue: [String, Date],
  name: {
    type: String,
    default: undefined,
    required: true,
  },
  id: String,
  rules: {
    type: String,
    default: "",
    required: false,
  },
  label: String,
  labelClass: {
    type: String,
    default: () => "",
    required: false,
  },
  placeholder: String,
  disabled: Boolean,
  min: [String, Date],
  max: [String, Date],
  value: {
    type: [String, Date],
    default: () => new Date("01-01-2001"),
    required: false,
  },
  svgIcon: {
    type: String,
    default:
      '<svg xmlns="http://www.w3.org/2000/svg" style="color:#00B4DF;transform:scale(.5)" fill="none" viewBox="0 0 24 24" stroke="currentColor"><path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" /></svg>',
    required: false,
  },
  rules: {
    type: String,
    default: "",
    required: false,
  },
  class: String,
});

const emit = defineEmits(["update:modelValue"]);
const isDark = useDark();
const passedValue = ref(props.modelValue);


const {
  errorMessage,
  value: inputValue,
  meta,
} = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

if (!passedValue.value) {
  
  inputValue.value = undefined;
}
const minDate = ref(props.min);
const maxDate = ref(props.max);

watch(
  () => passedValue,
  (value) => {
    
  }
);

watch(
  () => props.modelValue,
  (newVal) => {
    
    inputValue.value = newVal;
  }
);

const input = (e) => {
  emit("update:modelValue", e.target.value);
};
</script>

<template>
  <div>
    <label :class="labelClass" :for="id" class="block"
      >{{ label }}
      <span v-show="rules && label" class="text-red-500">*</span></label
    >
    <div class="relative rounded-md shadow-sm">
      <input
        ref="inp"
        v-model="inputValue"
        :disabled="disabled"
        type="text"
        onfocus="(this.type ='date')"
        onblur="(this.type='text')"
        @input="input($event)"
        @change="input($event)"
        :id="id"
        :min="minDate"
        :max="maxDate"
        :class="{
          'pointer-events-none  bg-gray-100 shadow-none': disabled,
          'focus:ring-primary  focus:border-primary hover:border-primary-2 border-primary-2 border-1':
            !errorMessage,
          'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500':
            errorMessage,
        }"
        class="block w-full text-secondary dark:text-white dark:bg-secondary placeholder-secondary-3 focus:outline-none md:text-base text-sm rounded-md"
        aria-invalid="true"
        aria-describedby="date-error"
        :placeholder="props.placeholder"
      />
    </div>
    <p
      v-if="!props.hideDetail"
      :visible="errorMessage"
      class="mt-2 text-sm text-red-600"
      id="email-error"
    >
      {{ errorMessage }} &nbsp;
    </p>
  </div>
</template>

<style scoped>
[type="date"]::-webkit-inner-spin-button {
  display: none;
}

[type="date"]::-webkit-calendar-picker-indicator {
  position: absolute;
  opacity: 0;
  padding: 5px;
  width: 100%;
  cursor: pointer;
}
</style>
