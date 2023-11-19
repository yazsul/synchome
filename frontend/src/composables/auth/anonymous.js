import { useMutation } from "@vue/apollo-composable";
// import useNotify from "@/use/notify";
// import useErrorParser from "@/use/errorParser";
// const type = import.meta.env.VITE_TYPE;

// const { notify } = useNotify();

// const { parse } = useErrorParser();

export default function (query) {
  console.log("object", query);
  const { mutate, onDone, loading, onError } = useMutation(query, () => ({
    fetchPolicy: "network-only",
  }));
  // onError((err) => notify(parse(err)));
  onError((err) => console.log("error while signup", err));

  return {
    mutate,
    loading,
    onDone,
  };
}
