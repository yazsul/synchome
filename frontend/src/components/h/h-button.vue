<script setup>
import { EllipsisHorizontalIcon } from "@heroicons/vue/24/outline";
const props = defineProps({
    to: [String, Object],
    href: String,
    loading: [Boolean, Object],
    class: String,
    type: String,
});
</script>
<template>
    <button :type="props.type" v-if="!props.to && !props.href" @click="$emit('click')" :class="props.class" class="
      items-center
      border border-transparent
      font-medium
      rounded-md
    ">
        <div v-if="loading" class="flex justify-center">
            <EllipsisHorizontalIcon class="animate-spin h-5 w-5"></EllipsisHorizontalIcon>
        </div>
        <slot v-else></slot>
    </button>
    <router-link v-else-if="props.to" :to="props.to" v-slot="{ navigate }">
        <button @click="navigate" class="mx-4 items-center border border-transparent font-medium rounded-md"
            :class="props.class">
            <slot></slot>
        </button>
    </router-link>
    <a v-else :href="href" :class="props.class" class="
      items-center
      border border-transparent
     
      font-medium
      rounded-md
    ">
        <slot></slot>
    </a>
</template>
<style>
.btn-xs {
    @apply px-3 py-0.5 text-xs shadow-sm;
}

.btn-sm {
    @apply px-3 py-1 text-sm shadow-sm;
}

.btn-base {
    @apply px-4 py-2 text-sm shadow-sm;
}

.btn-lg {
    @apply px-4 py-2 text-base shadow-md;
}

.btn-xl {
    @apply px-6 py-3 text-base shadow-md;
}

.btn-primary {
    @apply bg-primary hover:bg-primary-4  text-white hover:text-black;
}

.btn-white {
    @apply bg-gray-100 hover:bg-primary-dark hover:text-white focus:text-white focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-primary text-secondary focus:bg-primary;
}
</style>
