<script setup>
import {reactive} from 'vue'
import {useRouter} from 'vue-router'
import { useDark, useToggle } from "@vueuse/core"
import axios from 'axios'
import useUser from "@/use/user";

const isDark = useDark();
const toggleDark = useToggle(isDark);
const user = useUser();
const router = useRouter();

const userData = reactive({
  username: "",
  passwordHash: "",
});
const userLogin = async () => {
  await axios
    .post("http://localhost:8080/users/login", userData)
    .then((res) => {
      user.set({
        userInfo: {...userData}
      });
      router.push("/address");
    })
    .catch((err) => {
      console.log(err);
    });
};

</script>
<template>
    <div class="login-wrappper bg-white flex">
      <div class="login-left">
        <div class="login-img">
          <img
            src="/cover.png"
            alt="login img"
          >
        </div>
      </div>
      <div class="login-right bg-pp-blue text-slate-300 w-full">
        <form
          @submit.prevent="userLogin"
        >
          <div class="flex justify-center mb-5 font-bold text-xl">
            SyncHome
          </div>
          <h2>{{ $t("login") }}</h2>
          <div class="form-item">
            <div
              class="flex items-center justify-between mb-2"
              :class="{ 'invalid-email': invalidEmail }"
            >
              <label class="text-white">Email</label>
              <span v-show="invalidEmail">
                {{ $t("invalidEmail") }}
              </span>
            </div>
            <input
              v-model="userData.username"
              type="text"
              placeholder="Email"
            >
          </div>
          <div class="form-item">
            <div class="flex items-center justify-between mb-2">
              <label class="password">{{ $t("password") }}</label>
            </div>
            <input
              v-model="userData.passwordHash"
              type="password"
              placeholder="Password"
            >
          </div>
          <button class="border" type="submit">
            {{ $t("login") }}
          </button>
          <div class="flex items-center justify-center mt-2">
            <p class="hint text-slate-300">
              don't have an account?
              <a @click="router.push('/signup')">{{ $t("registration") }}</a>
            </p>
          </div>
        </form>
      </div>
    </div>
  </template>
  
  <style lang="scss" scoped>
  
  #girl_img {
              display: none;
              bottom: 0;
              right: -250px; /* start off the screen */
              opacity: 0;
          }
  
          /* Define the animation */
          @keyframes fadeInRight {
              from {
                  right: -250px;
                  opacity: 0;
              }
              to {
                  right: 0;
                  opacity: 1;
              }
          }
      #girl_img.show {
              animation: fadeInRight 1s ease forwards;
              display: block;
          }
  .invalid-email {
    & + input {
      color: #07211d !important;
      border-color: #07211d !important;
    }
    label,
    span {
      color: #07211d !important;
    }
  }
  .hint {
    a {
      cursor: pointer;
      font-weight: 700;
    }
  }
  .login {
    &-wrappper {
      height: 100vh;
    }
    &-img {
      height: 100%;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        object-position: right;
      }
    }
  
    &-left {
      width: 40%;
    }
  
    &-right {
      position: relative;
      // margin-left: 150px;
      width: 60%;
      display: flex;
      align-items: center;
      justify-content: center;
      flex-direction: column;
      form {
        width: 50%;
  
        h2 {
          font-size: 24px;
          font-weight: 700;
          line-height: 29px;
          text-align: center;
          margin-bottom: 20px;
        }
  
        button {
          width: 100%;
          height: 40px;
          font-size: 16px;
          font-weight: 400;
          line-height: 24px;
          outline: none;
          border-radius: 5px;
        }
      }
    }
  
    &-lang {
      position: absolute;
      bottom: 15%;
      button {
        width: 57px;
        height: 40px;
        border: 1px solid #07211d;
        border-radius: 8px;
        color: #323e48;
        background-color: transparent;
        text-align: center;
        outline: none;
        margin-right: 12px;
        &:hover {
          cursor: pointer;
        }
        &.active {
          background-color: #07211d;
          color: #ffffff;
        }
        &:last-child {
          margin-right: 0;
        }
      }
    }
  }
  .form {
    &-item {
      margin-bottom: 20px;
      &:last-child {
        margin-bottom: 0;
      }
      button[type="button"] {
        cursor: pointer;
        height: auto;
        background-color: transparent;
        width: max-content;
      }
      label {
        font-size: 16px;
        font-weight: 700;
        line-height: 17px;
        text-align: left;
        margin-bottom: 10px;
        display: block;
        &.password {
          margin-bottom: 0;
        }
      }
  
      input {
        border: 0.5px solid #dae0e4;
        border-radius: 5px;
        padding: 10px 20px;
        font-weight: 500;
        font-size: 16px;
        line-height: 17px;
        color: #323e48;
        width: 100%;
        outline: none;
        &::placeholder {
          font-weight: 500;
          font-size: 14px;
          line-height: 17px;
          color: #dae0e4;
        }
      }
    }
  }
  
  @media screen and (max-width: 1537px) {
    .login {
      &-wrappper {
        width: 100%;
      }
    }
  }
  
  @media screen and (max-width: 1115px) {
    .login {
      &-wrappper {
        width: 100%;
        justify-content: center;
      }
      &-left {
        display: none;
      }
      &-right {
        margin-left: 0;
        width: 60%;
      }
    }
  }
  
  @media screen and (max-width: 769px) {
    .login {
      &-wrappper {
        width: 100%;
      }
    }
  }
  
  @media screen and (max-width: 426px) {
    .login {
      &-wrappper {
        width: 100%;
      }
  
      &-right {
        width: 90%;
        padding: 15px;
      }
    }
  }
  </style>
  <route lang="yaml">
meta:
  layout: empty
</route>