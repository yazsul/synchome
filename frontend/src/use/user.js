import { computed } from "vue";
import { useStorage } from "@vueuse/core";

const state = useStorage("session", {});

export default function() {
    
    return {
        isLoggedIn: computed(() => !!state.value.access_token),
        token: () => state.value.access_token,
        email: state.value && state.value.email,
        name: (state.value && state.value.name),
        id: state.value.sub,
        unset: () => {
            state.value = {};
            localStorage.removeItem("session");
        },
        set: (data) => {
            console.log("data of user", data);
            state.value = data
        }
    }
}