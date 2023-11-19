<script setup>
import { EyeIcon, ArrowPathIcon, EllipsisHorizontalIcon } from "@heroicons/vue/24/outline";
import signUpMutation from "@/composables/auth/anonymous.js"
import signUpQuery from "@/queries/auth/signup.gql"
import google from "@/assets/icons/socials/google.svg?url";
import telegram from "@/assets/icons/socials/telegram.svg?url";
import linkedin from "@/assets/icons/socials/linkedin_color.svg?url";
import facebook from "@/assets/icons/socials/facebook.svg?url";
import { ref } from "vue";

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

const item = ref({});
const showVerification = ref(false);
const userEmail = ref()

const { mutate, loading, onDone } = signUpMutation(signUpQuery);

const submit = () => {
  console.log("item to be send is", item.value);
  mutate({
    params: item.value
  })
}

onDone(({ data }) => {
  console.log("the success data is ", data);
  showVerification.value = true;
  userEmail.value = item.value.email;
})


</script>


<template>
  <div class="relative">
    <dots
      class="sm:w-[70px] w-[80px] h-[120px] md:w-[150px] sm:h-[200px] z-0 md:-top-10 2xl:w-[303px] xl:w-[160px] 2xl:h-[350px] -left-20 sm:left-0 flex -top-10 sm:-top-20 xl:-top-3 2xl:-top-20">
    </dots>
    <dots
      class=" right-1 lg:right-0 w-[80px] h-[120px] sm:w-[70px] sm:h-[200px] md:bottom-[-10vh] bottom-[-10vh] 2xl:top-[73vh] xl:top-[86vh] z-0 md:w-[150px] 2xl:w-[260px] xl:w-[160px] 2xl:h-[300px]">
    </dots>
    <div class="sm:container mx-auto px-5 lg:max-w-[75%]" v-if="!showVerification">
      <section
        class="flex flex-col sm:justify-center lg:justify-between items-center lg:items-start sm:flex-row -mt-6 md:-mt-5">
        <div
          class="w-full lg:flex justify-center lg:mt-32 lg:pl-12 xl:pl-0 lg:justify-start md:w-3/5 lg:w-1/2 md:text-right hidden">
          <img src="@/assets/images/signup_illustration.png" alt="Landing Hero"
            class=" w-[250px] md:w-[480px] xl:w-[529px]" />
        </div>
        <div
          class="w-full bg-white z-10 dark:bg-secondary-dark-2 lg:dark:bg-transparent lg:bg-transparent rounded-xl shadow-lg lg:shadow-none lg:rounded-none 2xl:w-2/5 xl:w-[70%] sm:w-3/4 md:w-3/4 lg:w-1/2 flex items-center  flex-col lg:pt-12 py-6 md:py-10 gap-y-5 px-8 md:px-0">

          <div class="flex justify-center text-lg text-secondary dark:text-secondary-4 font-semibold lg:hidden">
            <p>Create Your Account</p>
          </div>
          <div class="lg:mt-8 mt-5 md:w-[60%] 2xl:w-[75%] xl:w-[60%] w-full lg:space-y-3 2xl:space-y-8 xl:space-y-4 ">
            <div class="lg:flex justify-center lg:justify-start hidden">
              <hahu-logo></hahu-logo>
            </div>
            <div>
              <form @submit.prevent="submit" class="space-y-3 lg:space-y-2 xl:space-y-3 2xl:space-y-5">
                <div class="flex space-x-5">
                  <h-text-field name="first name" v-model="item.given_name" placeholder="Enter your first Name"
                    placeholderStyle="placeholder-secondary-4">
                    <template v-slot:label>
                      <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                        First Name
                      </div>
                    </template>
                  </h-text-field>
                  <h-text-field name="middle name" v-model="item.middle_name" placeholder="Enter your middle name"
                    placeholderStyle="placeholder-secondary-4">
                    <template v-slot:label>
                      <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                        Middle Name
                      </div>
                    </template>
                  </h-text-field>
                </div>
                <h-text-field name="last name" v-model="item.family_name" placeholder="Enter your last name"
                  placeholderStyle="placeholder-secondary-4">
                  <template v-slot:label>
                    <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                      Last Name
                    </div>
                  </template>
                </h-text-field>
                <h-text-field name="Email" v-model="item.email" placeholder="Enter your email"
                  placeholderStyle="placeholder-secondary-4">
                  <template v-slot:label>
                    <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                      Email
                    </div>
                  </template>
                </h-text-field>

                <!-- <h-text-field name="phone" :leadingIcon="() => '+251'" iconLeadingClass="pl-20" :iconBackground="true"
                  placeholder="911000000" placeholderStyle="placeholder-gray-500 placeholder-opacity-50">
                  <template v-slot:label>
                    <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                      Phone Number
                    </div>
                  </template>
                </h-text-field> -->

                <h-text-field type="password" v-model="item.password" :name="$t('password')"
                  placeholder="Enter your password" placeholderStyle="placeholder-secondary-4"
                  :trailingIcon="() => EyeIcon()" class="border-primary-3 dark:bg-hahu-gray">
                  <template v-slot:label>
                    <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                      {{ $t("password") }}
                    </div>
                  </template>
                </h-text-field>

                <h-text-field type="password" v-model="item.confirm_password" :name="$t('confirm_password')"
                  placeholder="confirm your password" placeholderStyle="placeholder-secondary-4"
                  :trailingIcon="() => EyeIcon()" class="border-primary-3 dark:bg-hahu-gray">
                  <template v-slot:label>
                    <div class="2xl:text-lg font-normal 2xl:leading-[35px] text-secondary dark:text-primary-dark-2">
                      Confirm Password
                    </div>
                  </template>
                </h-text-field>


                <div class="pt-5 lg:pt-3">
                  <button :disabled="loading" class="w-full reactiveButton !py-3 flex justify-center"
                    :class="loading ? 'cursor-not-allowed' : ''" type="submit">
                    <EllipsisHorizontalIcon v-if="loading" class="animate-spin flex items-center h-5 w-5 text-white">
                    </EllipsisHorizontalIcon>
                    <span v-else> Sign up</span>
                  </button>
                </div>
              </form>
              <div class="mt-6 flex space-x-10 justify-center flex-row">
                <div v-for="social in socialIcons" :key="social.icon">
                  <a :href="social.link" class="inline-flex h-6 w-full justify-center reactiveSocialIcon">
                    <img :src="social.icon" class="object-contain" alt="social image" />
                  </a>
                </div>
              </div>
              <div class="mt-8 flex flex-col items-center justify-center font-light text-base font-body gap-y-5">
                <p class="dark:text-primary-dark-2 text-center">
                  Already have an account ?
                  <router-link to="/" class="text-primary">Login here
                  </router-link>
                </p>
                <p class="text-center font-light text-base font-body dark:text-secondary-4">
                  By creating an account means you agree with our
                  <span class="text-primary">Terms of Service</span> and
                  <span class="text-primary">Privacy Policy.</span>
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
    </div>
    <div v-else class="container h-screen flex justify-center mx-auto md:w-[30%] pt-20">
      <div class="flex flex-col items-center gap-y-8">
        <div class="flex flex-col items-center">
          <hahu-logo class=""></hahu-logo>
          <div class="mt-4">
            <hahu-moto />
          </div>
        </div>

        <div>
          <p class="text-3xl font-body font-medium dark:text-white">Check your email</p>
        </div>
        <div class="flex flex-col space-y-8 w-[80%]">
          <div class="flex justify-center">
            <img src="@/assets/images/email-sent.png" class="w-[270px]" aria-hidden="true" alt="invitation logo" />
          </div>
          <div class="">
            <p class="text-center dark:text-white">Please check your email at {{ userEmail }}, verification email has been sent.
            </p>
          </div>
          <div class="flex justify-center">
            <router-link :to="{ name: 'index' }" class="dark:text-white text-sm underline">
              Go back to login screen
            </router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
