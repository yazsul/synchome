<script setup>
import { ref, computed, watch, onBeforeMount, toRefs } from "vue";
import { CheckIcon } from "@heroicons/vue/24/solid";
import { XMarkIcon } from "@heroicons/vue/24/outline";
import { useDebounceFn } from "@vueuse/core";
import { useField } from "vee-validate";

const emit = defineEmits([
  "update:modelValue",
  "update:selected",
  "search",
  "onSelectionFound",
]);
const props = defineProps({
  id: String,
  label: {
    type: String,
  },
  name: {
    type: String,
    required: true,
  },
  multiple: {
    type: Boolean,
    default: false,
  },
  items: {
    type: Array,
    required: true,
    default() {
      return [];
    },
  },
  text: {
    type: String,
    default: "name",
  },
  value: {
    type: String,
    default: "id",
  },
  search: {
    type: String,
  },
  modelValue: {
    type: [String, Array, Object],
    // default: () => [],
  },
  selected: {
    type: String,
  },
  loading: {
    type: Boolean,
    default: false,
  },
  errorMessages: {
    type: String,
  },
  placeHolder: {
    type: String,
  },
  init: {
    type: Object,
    default: {},
  },
  class: {
    type: String,
    default: "",
  },
  clearable: Boolean,
  trailingIcon: Function,
  rules: {
    type: String,
    default: "",
    required: false,
  },
  supporter: {
    type: String,
  },
  hideDetail: Boolean,
  searchPlaceholder: String,
  disabled: Boolean,
  placeholderStyle: String,
});

const {
  errorMessage,
  value: inputValue,
  meta,
} = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

const vv = ref(props.selected);
const selected = ref({});
const _placeholder = ref(props.placeHolder);
const show = ref(false);
const input = ref(null);
const search = ref(undefined);
const { items, disabled } = toRefs(props);

onBeforeMount(() => {
  if (props.multiple && inputValue.value) {
    for (let v in inputValue.value) {
      selected.value[inputValue.value[v]] = true;
    }
  }
});

const DataItems = computed(() => {
  console.log("kkkk", props.init);
  if (Object.keys(props.init).length) {
    const itemExists = items.value.some((item) => item.id === props.init.id);
    if (!itemExists) {
      items.value.unshift({ id: props.init.id, name: props.init.name });
    }
  }
  return items.value;
});

const placeholder = computed(() => {
  if (!props.multiple && items.value.length) {
    let v = DataItems.value.find((e) => e[props.value] === props.selected);

    if (v) {
      selected.value = v[props.value];
      _placeholder.value = v[props.text];
      inputValue.value = v[props.text];

      emit("onSelectionFound", v);
      return _placeholder.value;
    } else if (props.placeHolder) {
      _placeholder.value = props.placeHolder;
    } else _placeholder.value = "Select";
  }

  return _placeholder.value;
});

const select = (item) => {
  vv.value = item.id;
  inputValue.value = item[props.value];
  emit("update:selected", item[props.value]);
  emit("update:modelValue", item);
  selected.value = item[props.value];
  _placeholder.value = item[props.text];
  show.value = false;
};

const outside = useDebounceFn(() => {
  show.value = false;
}, 300);

const open = () => {
  show.value = true;

  input.value.focus();
};
const queryList = () => {
  emit("search", search.value);
};

const clear = () => {
  search.value = undefined;
  inputValue.value = "";
  selected.value = "";
  show.value = false;
  emit("update:modelValue", undefined);
  emit("update:selected", undefined);
  emit("onSelectionFound", undefined);
  emit("search", "");
};
</script>

<template>
  <div class="relative">
    <slot name="label"></slot>
    <div class="mt-1 relative rounded-md shadow-sm font-body">
      <button
        type="button"
        :id="id"
        :disabled="disabled"
        @click="open"
        @focus="open"
        class="bg-white text-base relative w-full border rounded-md shadow-sm pl-3 pr-10 py-2 text-left cursor-default focus:outline-none focus:ring-1 dark:bg-hahu-gray dark:text-white"
        :class="[
          errorMessage
            ? 'focus:ring-red-500 focus:border-red-500 hover:border-red-500 border-red-500'
            : 'focus:ring-primary  focus:border-primary hover:border-primary border-primary-2 border-1 ',
          props.class ? props.class : '',
          disabled ? ' bg-gray-100 !cursor-not-allowed' : '',
          placeholder && !inputValue ? 'text-secondary-4' : '',
        ]"
      >
        {{ (inputValue && inputValue.name) || placeholder || $t("select") }}
      </button>
      <div
        v-if="trailingIcon"
        class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none"
      >
        <component class="h-5 w-5 text-gray-400" :is="trailingIcon"></component>
      </div>
      <div
        v-if="clearable"
        @click="clear"
        class="absolute inset-y-0 right-0 pr-3 flex items-center"
      >
        <XMarkIcon class="h-5 w-5 text-gray-500 cursor-pointer"></XMarkIcon>
      </div>
    </div>

    <ul
      v-show="show"
      :class="supporter"
      class="absolute z-50 w-full bg-white border-r-2 border-l-2 border-b-2 border-primary-lite dark:bg-hahu-gray dark:border-primary-2 scrollbar scrollbar-thin scrollbar-track-rounded-full scrollbar-track-primary shadow-lg max-h-56 rounded-br-xl rounded-b-xl text-base overflow-auto"
    >
      <li class="flex justify-center items-center px-2">
        <input
          ref="input"
          @input="queryList"
          v-model="search"
          @blur="outside"
          autocomplete="off"
          type="text"
          :name="props.name"
          id="email"
          class="my-1 shadow-sm focus:ring-primary focus:border-primary block sm:text-sm w-full border-gray-300 rounded-md font-body dark:bg-hahu-gray dark:text-white"
          :placeholder="props.searchPlaceholder || $t('search')"
        />
      </li>
      <li class="h-1">
        <h-progress
          v-if="loading"
          class="rounded-xl w-full"
          color1="bg-primary-lite"
          color2="bg-primary"
          color3="bg-primary-dark"
          height="h-1"
        ></h-progress>
      </li>
      <li
        v-for="item in DataItems"
        :key="item.id"
        @click="select(item)"
        class="border-b select-none relative py-3 px-3 hover:bg-blue-50 dark:hover:bg-secondary-2 text-secondary cursor-pointer"
      >
        <div class="flex DataItems-center justify-between">
          <span
            class="text-secondary dark:text-white font-normal block truncate font-body"
          >
            {{ item.name }}
          </span>
          <span
            class="font-body text-sm text-gray-400 dark:text-white font-medium pr-3"
          >
            {{ item.identifier ? item.identifier : "" }}
          </span>
          <CheckIcon
            v-if="placeholder == item.name"
            class="w-6 h-4 text-primary font-bold text-lg"
          />
        </div>
      </li>
      <div
        v-if="!loading && DataItems.length === 0"
        class="text-black test-lg flex justify-center items-center pb-2"
      >
        <slot name="option" :item="search" />
      </div>
    </ul>

    <p
      :visible="errorMessage"
      v-if="!props.hideDetail"
      class="mt-1 text-sm text-red-600 font-body"
      id="email-error"
    >
      {{ errorMessage }} &nbsp;
    </p>
  </div>
</template>
