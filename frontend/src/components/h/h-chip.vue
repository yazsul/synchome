<script setup>
import { ref } from "vue";
import { XMarkIcon, PlusIcon } from "@heroicons/vue/24/outline";
const emit = defineEmits(["removeSelectedType"]);
const props = defineProps({
    title: {
        type: String,
        required: true,
    },
    subtitle: {
        type: String,
        required: false,
    },
    solid: {
        type: Boolean,
        required: false
    },
    index: {
        type: Number,
        required: true
    },
    titleClass: {
        type: String,
        required: false
    },
    subtitleClass: {
        type: String
    },
    iconClass: {
        type: String
    },
    
});

const removeType = (index) => {
    
    emit("removeSelectedType", index);
};
</script>
<template>
    <div class="inline-flex items-center rounded-full font-normal font-body dark:text-secondary-8"
        :class="[subtitle?.length ? 'flex space-x-4 justify-between items-center px-4 py-1.5' : 'px-2 py-0.5', solid ? 'bg-primary-4' : 'text-secondary border border-primary']">
        <div class="flex flex-col justify-center" :class="[subtitle?.length ? 'pl-3' : '']">
            <p :class="[props.titleClass ? props.titleClass : 'text-base', solid ? 'text-primary ' : '']"> {{ title }}</p>
            <p :class="[props.subtitleClass ? props.subtitleClass : 'text-sm -mt-1', solid ? 'text-secondary': '']" v-if="subtitle">{{ subtitle }}</p>
        </div>
        <div>
            <button @click="removeType(index)" type="button"
                class="flex items-center justify-center text-primary dark:stroke-secondary-8">
                <XMarkIcon :class="[subtitle?.length ? 'w-4 md:w-5' : 'h-4 w-4 ml-2', iconClass ? iconClass : '']" />
            </button>
        </div>
    </div>
</template>
