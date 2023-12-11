<script setup>
import { useRouter } from "vue-router";
import { reactive, computed } from "vue";
import axios from "axios";

const router = useRouter();
const userData = reactive({
  username: "",
  password: "",
  passwordConfirmation: "",
  email: "",
});
const passwordConfirmationObject = reactive({
  atLeastOneNumber: true,
  atLeastLowerCase: true,
  atLeastUpperCase: true,
  min: true,
});

const emailValidation = (e) => {
  const { value } = e.target;
  if (value === "") return;
  // eslint-disable-next-line no-useless-escape
  const email = /^\w+([\+-]?\w+)*(\.\w+)*(\+[\w-]*)?@\w+([\.-]?\w+)*(\.\w{1,3})+$/g;

  if (email.test(value)) {
    e.target.parentElement.classList.remove("invalid");
    userData.email = value;
  } else {
    e.target.parentElement.classList.add("invalid");
  }
};

const passwordValidation = (e) => {
  const { value } = e.target;
  if (value === "") return;
  const lowerCaseLetters = /[a-z]{1,}/g;
  const upperCaseLetters = /[A-Z]{1,}/g;
  const numbers = /[0-9]{1,}/g;
  passwordConfirmationObject.atLeastOneNumber = numbers.test(value);
  passwordConfirmationObject.atLeastUpperCase = upperCaseLetters.test(value);
  passwordConfirmationObject.atLeastLowerCase = lowerCaseLetters.test(value);
  passwordConfirmationObject.min = value.length >= 8;

  if (Object.values(passwordConfirmationObject).every((value) => value == true)) {
    userData.password = value;
  }
};

const checkPasswords = () => {
  return userData.password === userData.passwordConfirmation;
};

const isDisabled = computed(() => {
  if (userData.passwordConfirmation !== "") return checkPasswords();
  return false;
});

const showPasswordWarnings = computed(() => {
  if (Object.values(passwordConfirmationObject).some((value) => value == false))
    return true;
  return false;
});
const resetUserObject = () => {
  const keys = ["email", "password", "username", "passwordConfirmation"];
  keys.forEach((key) => {
    userData[key] = "";
  });
};
const userRegistration = async () => {
  const postData = {
    email: userData.email,
    passwordHash: userData.password,
    username: userData.username,
  };
  const res = await axios.post("http://localhost:8080/users", postData);
  const { status } = res;
  if (status === 201) {
    resetUserObject();
    router.push("/login")
  }
};
</script>
<template>
  <div class="login-wrappper flex">
    <div class="login-left">
      <div class="login-img">
        <img src="/cover.png" alt="login img" />
      </div>
    </div>
    <div class="login-right bg-pp-blue text-slate-300">
      <form @submit.prevent="userRegistration">
        <div class="flex justify-center mb-5 font-bold text-xl">SyncHome</div>
        <h2>{{ $t("registrationForm") }}</h2>
        <div class="form-item">
          <label>User Name</label>
          <input v-model="userData.username" type="text" placeholder="Name" />
        </div>
        <div class="form-item">
          <label>Email</label>
          <input type="text" placeholder="Email" @input="emailValidation" />
        </div>
        <div class="form-item">
          <label>{{ $t("password") }}</label>
          <input type="password" placeholder="Password" @input="passwordValidation" />
        </div>
        <p v-show="showPasswordWarnings" class="invalid-input-text">
          <span :class="{ valid: passwordConfirmationObject.min }">
            "minimum 8 characters"
          </span>
          <span :class="{ valid: passwordConfirmationObject.atLeastOneNumber }">
            "at Least Number"
          </span>
          <span :class="{ valid: passwordConfirmationObject.atLeastLowerCase }">
            "at Leaset Lowercase"
          </span>
          <span :class="{ valid: passwordConfirmationObject.atLeastUpperCase }">
            "at Leaset Uppercase"
          </span>
        </p>
        <div class="form-item">
          <label>{{ $t("passwordConfirm") }}</label>
          <input
            v-model="userData.passwordConfirmation"
            type="password"
            placeholder="Password confirm"
          />
        </div>
        <button
          :class="!isDisabled ? 'bg-slate-500 cursor-none' : ''"
          class="register-button border"
          :disabled="!isDisabled"
          type="submit"
        >
          {{ $t("register") }}
        </button>
        <div class="flex items-center justify-center mt-2">
          <p class="hint">
            {{ $t("alreadyAccount") }}
            <a @click="router.push('/login')">{{ $t("login") }}</a>
          </p>
        </div>
      </form>
    </div>
  </div>
</template>

<style lang="scss" scoped>
.register-button:disabled {
  // background-color: #07211d;
}
.invalid-input-text {
  display: flex;
  flex-direction: column;
  gap: 2px;
  color: white;
  width: 100%;
  padding-left: 15px;
  margin-bottom: 20px;
  span {
    position: relative;
    &.valid {
      color: #06aa16;
    }
    &:before {
      content: "";
      width: 5px;
      height: 5px;
      border-radius: 50%;
      background-color: currentColor;
      position: absolute;
      left: -10px;
      top: 50%;
      transform: translateY(-50%);
    }
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
    width: 100%;
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
    // margin-left: 150px;
    width: 60%;
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    position: relative;
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
        color: #fff;
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
    &.invalid {
      input {
        border: 1px solid #ff0000;
        color: #ff0000;
        &::placeholder {
          color: #ff0000;
        }
      }
    }
    margin-bottom: 20px;
    &:last-child {
      margin-bottom: 0;
    }
    label {
      font-size: 16px;
      font-weight: 700;
      line-height: 17px;
      text-align: left;
      margin-bottom: 10px;
      display: block;
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

@media screen and (max-width: 426px) {
  .login {
    &-right {
      width: 90%;
      padding: 15px;
    }
  }
}
</style>
