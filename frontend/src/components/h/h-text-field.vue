<script setup>
import { useField } from "vee-validate";
import { computed, ref, watch } from "vue";
const props = defineProps({
  modelValue: {
    type: [String, Number],
    default: undefined,
  },
  name: {
    type: String,
    default: undefined,
    required: true,
  },
  id: {
    type: String,
    default: undefined,
    required: false,
  },
  type: {
    type: String,
    default: "text",
    required: false,
  },
  placeholder: {
    type: String,
    default: undefined,
    required: false,
  },
  label: {
    type: String,
    default: undefined,
    required: false,
  },
  labelClass: {
    type: String,
    default: () => "",
    required: false,
  },
  hideDetail: {
    type: Boolean,
    default: false,
    required: false,
  },
  trailingIcon: {
    type: Function,
    default: undefined,
    required: false,
  },
  leadingIcon: {
    type: Function,
    default: undefined,
    required: false,
  },
  min: String,
  max: String,
  rules: {
    type: String,
    default: "",
    required: false,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
  class: String,
  iconLeadingClass: String,
  placeholderStyle: String,
  iconBackground: Boolean,
});
const emit = defineEmits(["update:modelValue"]);

const {
  errorMessage,
  value: inputValue,
  meta,
} = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

const type = ref(props.type);
const textType = ref(props.type);

// if (type.value === "number") {
//   emit("update:modelValue", 0);
// }

const set = (event) => {
  emit("update:modelValue", event.target.value);
};

const togglePassword = () => {
  if (type.value === "password") {
    type.value = "text";
    return;
  }
  if (type.value === "text") {
    type.value = "password";
  }
};

watch(
  () => props.modelValue,
  (newVal) => {
    inputValue.value = newVal;
  }
);


</script>
<template>
  <div>
    <slot name="label"></slot>
    <div class="relative rounded-md font-body" :class="props.class">
      <div
        v-if="props.leadingIcon"
        class="absolute inset-y-0 left-0 px-4 flex items-center pointer-events-none"
        :class="
          iconBackground && !errorMessage
            ? 'bg-gray-50 border border-gray-300 rounded-l-lg dark:bg-secondary-6 dark:text-white'
            : 'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500 border rounded-l-lg'
        "
      >
        <component
          class="h-5 w-5 text-gray-400 hover:cursor-pointer"
          :is="props.leadingIcon"
        ></component>
      </div>
      <input
        v-model="inputValue"
        @input="set($event)"
        @change="set($event)"
        :min="min"
        :max="max"
        :type="type"
        :name="props.name"
        step="any"
        :id="id"
        class="block w-full text-secondary dark:bg-gray-100 dark:text-gray-800 focus:outline-none text-sm md:text-base rounded-md font-body"
        :class="[
          errorMessage
            ? 'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500'
            : 'focus:ring-primary  focus:border-primary hover:border-primary border-primary-2 border-1',
          props.iconLeadingClass ? props.iconLeadingClass : '',
          props.trailingIcon ? 'pr-0' : '',
          props.class ? props.class : '',
          props.placeholderStyle
            ? props.placeholderStyle
            : ' placeholder-gray-100',
          disabled ? ' bg-gray-100 cursor-not-allowed' : '',
        ]"
        :placeholder="props.placeholder"
        aria-invalid="true"
        aria-describedby="email-error"
        :disabled="props.disabled"
      />
      <!-- icon -->
      <div
        v-if="props.trailingIcon"
        class="absolute inset-y-0 right-0 pr-3 flex items-center hover:cursor-pointer"
        @click="textType === 'password' ? togglePassword() : undefined"
      >
        <component
          class="h-5 w-5"
          :class="[type === 'password' ? 'text-primary' : 'text-gray-400']"
          :is="props.trailingIcon"
        ></component>
      </div>
    </div>
    <p
      v-if="!props.hideDetail"
      :visible="errorMessage"
      class="mt-2 text-sm text-red-600 font-body"
      id="email-error"
    >
      {{ errorMessage }} &nbsp;
    </p>
  </div>
</template>
