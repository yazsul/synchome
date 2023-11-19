import { useMutation } from "@vue/apollo-composable";
// import useNotify from "@/use/notify";
import verifyEmailQuery from "@/queries/auth/verify_email.gql"
import { useRoute, useRouter } from "vue-router";
const router = useRouter()
// import useErrorParser from "@/use/errorParser";
// const type = import.meta.env.VITE_TYPE;

// const { notify } = useNotify();

// const { parse } = useErrorParser();

export default function () {
  const { mutate, onDone, loading, onError } = useMutation(verifyEmailQuery, () => ({
    fetchPolicy: "network-only",
  }));
  // onError((err) => notify(parse(err)));
  onError((err) => {
    router.push({
        name: 'signup'
    })
    console.log("error while verify Email", err)
  });

  return {
    mutate,
    loading,
    onDone,
  };
}
