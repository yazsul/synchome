<script setup>
import { EyeIcon, EllipsisHorizontalIcon } from "@heroicons/vue/24/outline";
import loginMutation from "@/composables/auth/anonymous.js"
import loginQuery from "@/queries/auth/login.gql"
import google from "@/assets/icons/socials/google.svg?url";
import telegram from "@/assets/icons/socials/telegram.svg?url";
import linkedin from "@/assets/icons/socials/linkedin_color.svg?url";
import facebook from "@/assets/icons/socials/facebook.svg?url";
import { ref, onMounted } from "vue";
import { useUserStore } from '@/stores/UserStore.js'
import { useDark, useToggle } from "@vueuse/core";
import useStore from "@/use/user"
import { useRouter } from 'vue-router'
import { useForm } from "vee-validate";

const onUserStore = useUserStore();
const { set, unset, isLoggedIn } = useStore();
const router = useRouter()
const { handleSubmit, handleReset } = useForm();

const isDark = useDark({});
const item = ref({
  email: "cevok22114@paxven.com",
  password: "Biruk#33"
})
const socialIcons = ref([
  {
    icon: google,
    link: "#",
  },
  {
    icon: telegram,
    link: "#",
  },
  {
    icon: linkedin,
    link: "#",
  },
  {
    icon: facebook,
    link: "#",
  },
]);

const { mutate, loading, onDone } = loginMutation(loginQuery);


const submit = handleSubmit(() => {
  mutate({
    email: item.value.email,
    password: item.value.password
  })
});

onDone(({ data }) => {
  let id_token = data.token.me_access_token;
  let access_token = data.token.custom_access_token;
  let claims = access_token.split(".")[1];
  claims = JSON.parse(window.atob(claims));
  claims.access_token = access_token;
  claims.id_token = id_token
  localStorage.setItem("session", JSON.stringify(claims));
  // set(claims);
  onUserStore.set(claims);
  if (onUserStore.isLoggedIn) {
    router.push("/onboarding");
  }
})

onMounted(() => {
  onUserStore.unset();
});
</script>

<template>
  <div class="relative">
    
    <div class="sm:container mx-auto px-5 lg:max-w-[75%]">
     <p>login component</p>
    </div>
  </div>

</template>
<style scoped>
.icon_dark {
  filter: invert(100%) sepia(96%) saturate(0%) hue-rotate(46deg) brightness(105%) contrast(103%);
}
</style>
