import { defineStore } from 'pinia'
import { useStorage } from "@vueuse/core";

export const useUserStore = defineStore("user", {
    state: () => ({
        user: useStorage('session', {})
    }),

    getters: {
        isLoggedIn: (state) => !!state?.user?.access_token,
        token: (state) => {
            console.log("state after reload", state?.user);
          return state?.user?.access_token
        },
        email: (state) => state?.user && state?.user?.email,
        name: (state) =>  (state?.user && state?.user?.name),
        id: (state) => state?.user.sub,
    },

    actions: {
        unset(){
            this.user = {};
            localStorage.removeItem("session");
        },
        set(data){
            console.log("data of user inside ", data);
            this.user = data;
        }
    }



})