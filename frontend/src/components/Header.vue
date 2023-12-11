<script setup>
import { ref } from "vue";
import { Disclosure, DisclosureButton, DisclosurePanel } from "@headlessui/vue";
import { RouterLink } from "vue-router";
import { MoonIcon, SunIcon, XMarkIcon, Bars3Icon } from "@heroicons/vue/24/outline";
import { HomeIcon } from "@heroicons/vue/24/solid";
import { useDark, useToggle } from "@vueuse/core";
import {useRouter} from "vue-router";

const router = useRouter();
const isDark = useDark();
const toggleDark = useToggle(isDark);

const navigation = ref([
  {
    name: "Home",
    href: "/",
    current: true,
  },
  {
    name: "About",
    href: "/#about",
    current: false,
  },
  {
    name: "Contact",
    href: "/#contact",
    current: false,
  },
]);

// const route = useRoute();
// const router = useRouter();
</script>

<template>
  <header class="fixed w-full top-0 z-30 backdrop-filter backdrop-blur-md dark:shadow-black dark:shadow-sm">
    <Disclosure as="div" class="shadow sm:shadow-sm" v-slot="{ open }">
      <div class="px-4 sm:px-6 lg:px-8 2xl:px-16">
        <div class="flex justify-between h-20">
          <div @click="router.push('/')" class="flex items-center gap-2 cursor-pointer">
            <HomeIcon class="h-10 w-10 text-slate-300"></HomeIcon>
            <span class="font-extrabold text-4xl text-slate-300">SyncHome</span> 
          </div>
          
          <div class="hidden sm:ml-6 md:flex sm:items-center sm:space-x-8">
            <div class="hidden sm:ml-6 sm:flex sm:items-center text-center sm:space-x-8">
              <!-- <RouterLink to="/confirm"
                class="cursor-pointer text-secondary dark:text-secondary-8 reactiveText inline-flex items-center px-1 pt-1 text-lg 2xl:text-lg">
                <span>Home</span>
              </RouterLink> -->
              <!-- <RouterLink to="/"
                class="cursor-pointer text-secondary dark:text-secondary-8 reactiveText inline-flex items-center px-1 pt-1 text-lg 2xl:text-lg">
                <span>About</span>
              </RouterLink> -->
              
              <!-- <a href="https://www.hahujobs.io/#contact" target="_blank"
                class="cursor-pointer text-secondary dark:text-secondary-8 reactiveText inline-flex items-center px-1 pt-1 text-lg 2xl:text-lg">
                <span>Contact</span>
              </a> -->

            
              <div class="mt-2">
                <button @click="toggleDark()">
                  <MoonIcon v-if="!isDark" class="w-5 h-5" />
                  <SunIcon v-if="isDark" class="w-5 h-5" />
                </button>
              </div>
              <!-- <div>
                <button class="reactiveButton">Login</button>
              </div> -->
            </div>
          </div>
          <div class="flex space-x-4 items-center md:hidden">
            <!-- <button class="cursor-pointer flex" @click="themeSwitcher">
              <MoonIcon class="w-5 h-5 dark:text-secondary-8 text-gray-500" v-if="colorMode.preference == 'light'">
              </MoonIcon>
              <SunIcon class="w-5 h-5 dark:text-secondary-8 text-black" v-else>
              </SunIcon>
            </button> -->
            <DisclosureButton
              class="inline-flex items-center justify-center p-2 rounded-md text-secondary hover:text-gray-500 hover:bg-gray-100 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-primary">
              <Bars3Icon v-if="!open" class="block h-8 w-8" aria-hidden="true" />
              <XMarkIcon v-else class="block h-8 w-8" aria-hidden="true" />
            </DisclosureButton>
          </div>
        </div>
      </div>

      <DisclosurePanel class="md:hidden">
        <div class="pt-4 pb-3 border-t border-gray-200">
          <div class="mt-3 space-y-1">
            <!-- <NuxtLink @click="openDialog = true" :to="{ name: 'index' }"
              class="block pl-3 pr-4 py-2 border-l-4 border-primary-lite text-base font-medium text-secondary dark:text-secondary-8">
              <DisclosureButton> Login </DisclosureButton>
            </NuxtLink> -->
          </div>
        </div>
      </DisclosurePanel>
    </Disclosure>
  </header>
</template>
