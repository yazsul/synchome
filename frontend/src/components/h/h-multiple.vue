<script setup>
import { ref, watch } from "vue";
import {
  CheckIcon,
  XMarkIcon,
  ChevronDownIcon,
} from "@heroicons/vue/24/outline";
import { onClickOutside } from "@vueuse/core";
import { useField } from "vee-validate";

// import useClickOutside from '@/composable/clickOutside';

const props = defineProps({
  id: String,
  modelValue: {
    type: [String, Array, Object],
    // default: () => [],
  },
  name: {
    type: String,
    required: true,
  },
  items: {
    type: Array,
    default: [],
  },
  returnBy: {
    type: String,
  },
  showBy: {
    type: String,
  },
  chipsStyle: {
    type: String,
    required: false,
  },
  init: {
    type: Array,
    default: () => [],
  },
  placeholder: {
    type: String,
    default: "Search",
  },
  loading: {
    type: Boolean,
    default: false,
    required: false,
  },
  value: {
    type: String,
    default: "id",
  },
  multiple: {
    type: Boolean,
    default: false,
  },
  trailingIcon: {
    type: Function,
    default: ChevronDownIcon,
  },
  rules: {
    type: String,
    default: "",
    required: false,
  },
  listClass: {
    type: String,
  },
  deletedItem: {
    type: String,
  },
  disabled: Boolean,
});

const chips = ref([]);
const show = ref(false);
const search = ref(undefined);
const selected = ref({});
const set = ref(true);
const list_select = ref(null);

const emit = defineEmits(["update:modelValue", "update:selected", "search"]);

//=================== functions ============================

const {
  errorMessage,
  value: inputValue,
  meta,
} = useField(props.name, props.rules, {
  initialValue: props.modelValue,
});

const select = (val) => {
  selected.value[val[props.value]] = !selected.value[val[props.value]];
  if (!Object.values(selected.value).includes(false)) {
    chips.value.push(val);
    inputValue.value = chips.value.map((chip) => chip.id);
    //
  } else {
    delete selected.value[val.id];
    //
    chips.value = chips.value.filter((e) => e.id != val.id);
    inputValue.value = chips.value.map((chip) => chip.id);
    //
    //
    //   )
  }
  // )

  if (chips.value && chips.value.length) {
    emit(
      "update:modelValue",
      chips.value.map((chip) => chip.id)
    );
  } else {
    emit("update:modelValue", undefined);
    inputValue.value = undefined;
  }
};

// watch(() => chips.value, (val) => {
//   if(!val){
//
//   }
// })

const open = () => {
  show.value = true;
  // input.value.focus();
};

const queryList = () => {
  emit("search", search.value);
};

if (props.init) {
  

  chips.value = [
    ...new Map(props.init.map((item) => [item[props.value], item])).values(),
  ];
  for (const val of chips.value) {
    selected.value[val[props.value]] = !selected.value[val[props.value]];
  }

  if (chips.value && chips.value.length) {
    emit(
      "update:modelValue",
      chips.value.map((chip) => chip.id)
    );
  } else {
    emit("update:modelValue", undefined);
  }
}

const deleteChip = (index) => {
  //
  let id = chips.value[index].id;
  chips.value.splice(index, 1);
  delete selected.value[id];
  if (chips.value?.length < 1) {
    inputValue.value = chips.value;
  }

  if (chips.value && chips.value.length) {
    emit(
      "update:modelValue",
      chips.value.map((chip) => chip.id)
    );
  } else {
    emit("update:modelValue", undefined);
    inputValue.value = undefined;
  }
  // emit(
  //   "update:modelValue",
  //   chips.value.map((chip) => chip.id)
  // );
};
// const backspaceDelete = ({ which }) => {
//   which === 8 && currentInput.value === "" && chips.value.pop();
// };

onClickOutside(list_select, (e) => (show.value = false));
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
        ]"
      >
        <!-- {{ typeof selectedItems }} -->

        <span>{{ placeholder || "Select" }}</span>
      </button>
      <div
        v-if="trailingIcon"
        class="absolute inset-y-0 right-0 pr-3 flex items-center pointer-events-none"
      >
        <component class="h-5 w-5 text-gray-400" :is="trailingIcon"></component>
      </div>
    </div>
    <div
      v-if="chips.length && !show"
      class="flex w-full gap-2 flex-wrap overflow-y-scroll rounded-b-md shadow-sm font-body bg-white border-primary-2 border border-t-0 p-2"
    >
      <span
        v-for="(chip, i) in chips"
        class="px-2 py-1 rounded-full text-hahu-gray bg-primary-5 font-semibold text-xs flex items-center align-center w-max cursor-pointer active:bg-gray-300 transition duration-300 ease"
      >
        <span>{{ chip.name }}</span>
        <XMarkIcon
          @click="deleteChip(i)"
          class="h-4 w-5 text-hahu-black font-black cursor-pointer"
        ></XMarkIcon>
      </span>
    </div>
    <ul
      ref="list_select"
      v-show="show"
      class="absolute z-10 w-full bg-white border border-primary shadow max-h-56 rounded-b-md text-base overflow-auto"
      :class="[listClass || '']"
    >
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
      <li class="px-2 border-b pb-2">
        <input
          class="flex-1 focus:ring-0 focus:outline-none flex min-w-20 rounded-md border-primary border-2 w-full p-1"
          :name="props.name"
          @click="show = true"
          v-model="search"
          @input="queryList"
          @blur="outside"
          id="email"
          placeholder="Search"
          :disabled="false"
        />
      </li>
      <li
        v-for="item in items"
        @click="select(item)"
        class="border-b select-none relative py-3 px-3 hover:bg-blue-50 dark:hover:bg-secondary-2 text-secondary cursor-pointer"
      >
        <div class="flex items-center justify-between">
          <span class="text-secondary dark:text-white font-normal block truncate font-body">
            {{ item[showBy] }}
          </span>
          <CheckIcon
            v-if="multiple && selected[item[value]]"
            class="w-5 h-5 text-gray-600"
          />
        </div>
      </li>
    </ul>
    <p
      :visible="errorMessage"
      class="mt-2 text-sm text-red-600"
      id="email-error"
    >
      {{ errorMessage }} &nbsp;
    </p>
  </div>
</template>
<style scoped>
/* width */
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
