<script setup>
import { ref, watch, toRefs, onMounted } from "vue";
import { useField } from "vee-validate";
import { XMarkIcon, CheckIcon } from "@heroicons/vue/24/outline";
import { useDebounceFn } from "@vueuse/core";

const props = defineProps({
  id: String,
  trailingIcon: Function,
  items: {
    type: Array,
    default: () => [],
  },
  placeholder: String,
  label: String,
  clearable: Boolean,
  returnObject: Boolean,
  modelValue: {
    type: [String, Number, Object, Boolean],
    default: undefined,
  },
  name: {
    type: String,
    required: true,
  },
  type: {
    type: String,
    default: "text",
    required: false,
  },
  labelClass: {
    type: String,
    default: "",
    required: false,
  },
  rules: {
    type: String,
    default: "",
    required: false,
  },
  class: {
    type: String,
    default: "",
    required: false,
  },
  disabled: {
    type: Boolean,
    default: false,
  },
});

const emit = defineEmits(["update:modelValue"]);
const selected = ref("");
const { items } = toRefs(props) || [];
const show = ref(false);
const placeHolder = ref(props.placeholder || 'Select');
const input = ref(null);
const {
  errorMessage,
  value: inputValue,
  meta,
} = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

const outside = useDebounceFn(() => {
  show.value = false;
}, 300);

const set = (item) => {
  inputValue.value = item["name"];
  placeHolder.value = item["name"];
  console.log("the placeholder is", item["name"]);
  show.value = false;
  if (props.returnObject) {
    emit("update:modelValue", item);
  } else {
    emit("update:modelValue", item.id);
    selected.value = item.id;
  }
};
const clear = () => {
  // placeholder.value = undefined;
  inputValue.value = undefined;
  show.value = false;
  input.value.focus();
  emit("update:modelValue", undefined);
};

watch(
  () => props.modelValue,
  (newVal) => {
    let selectedItem = props.items.find((item) => item.id == newVal);
    if (selectedItem) inputValue.value = selectedItem.name;
    selected.value = newVal;

    console.log("this is placholder", selectedItem);
    
    // placeholder.value = items.value.reduce((acc, itm) => {
    //   // if (itm.value == newVal) {
    //   //   acc = itm.name;
    //   // }
    //   console.log("item ", itm);
    // }, undefined);

    // console.log("what ", placeholder.value);
  }
);

onMounted(() => {
  let selectedItem = props.items.find((item) => item.id == props.modelValue);
  if (selectedItem) inputValue.value = selectedItem.name;
  selected.value = props.modelValue;
});
</script>
<template>
  <div class="relative">
    <slot name="label"></slot>
    <div class="relative rounded-md shadow-sm font-body">
      <input
        ref="input"
        v-model="inputValue"
        @click="show = true"
        @focus="show = true"
        @blur="outside"
        autocomplete="off"
        :type="type"
        :placeholder="placeHolder"
        :name="props.name"
        :id="id"
        class="block w-full pr-10 focus:outline-none text-base rounded-md"
        :class="[
          errorMessage
            ? 'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500 '
            : 'focus:ring-primary  focus:border-primary hover:border-primary border-primary-2 border-1 ',
          disabled ? ' bg-gray-100 cursor-not-allowed' : '',
        ]"
        :disabled="disabled"
        aria-invalid="true"
        aria-describedby="email-error"
      />
      <!-- icon -->
      <div
        v-if="trailingIcon && !disabled"
        class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none"
      >
        <component class="h-5 w-5 text-gray-400" :is="trailingIcon"></component>
      </div>
      <div
        v-if="clearable && !disabled"
        @click="clear"
        class="absolute inset-y-0 right-0 pr-3 flex items-center"
      >
        <XMarkIcon class="h-5 w-5 text-gray-400 cursor-pointer"></XMarkIcon>
      </div>
    </div>
    <ul
      v-show="show"
      class="absolute z-50 overflow-scroll w-full bg-white border shadow max-h-56 rounded-md text-base"
    >
      <li
        v-for="item in items"
        :key="item.id"
        @click="set(item)"
        :class="{ 'bg-blue-50': selected == item.id }"
        class="border-b select-none relative py-3 px-3 hover:bg-blue-50 text-secondary cursor-pointer overflow-auto"
      >
        <div class="flex items-center justify-between">
          <span
            class="text-secondary font-semibold block truncate capitalize"
            >{{ item.name }}</span
          >
          <CheckIcon v-if="selected == item.id" class="w-4 h-4" />
        </div>
      </li>
    </ul>
    <p v-if="errorMessage"
      :visible="errorMessage"
      class="mt-2 text-sm text-red-600 font-body"
      id="email-error"
    >
      {{ errorMessage }} &nbsp;
    </p>
  </div>
</template>
<style>
.zz {
  z-index: 99;
}

::-webkit-scrollbar {
  width: 4px;
}

/* Track */
::-webkit-scrollbar-track {
  box-shadow: inset 0 0 5px white;
  border-radius: 10px;
}

/* Handle */
::-webkit-scrollbar-thumb {
  background: #009688;
  border-radius: 3px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
  background: #009688;
}
</style>
