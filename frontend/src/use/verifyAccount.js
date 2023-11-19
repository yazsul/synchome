import { computed, ref, watch } from "vue";
import { useQuery, useMutation, useResult } from "@vue/apollo-composable";
import verifyEmailQuery from "@/queries/auth/verify_email.gql"
import useStore from "@/use/user"

// let data = {
//     verify_account: {
//         accessToken: "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJnaXZlbl9uYW1lIjoiYmlydWsiLCJtaWRkbGVfbmFtZSI6Im1vbGxhIiwiZmFtaWx5X25hbWUiOiJ0YW1lbmUiLCJnZW5kZXIiOiIiLCJwaG9uZV9udW1iZXIiOiIiLCJlbWFpbCI6Im5lamVrYTk0NjdAYml0dm9vLmNvbSIsInBpY3R1cmUiOiIiLCJiaXJ0aGRhdGUiOiIiLCJtZXRhZGF0YSI6eyJyb2xlcyI6WyJwcmltYXJ5OnVzZXIiXSwieC1oYXN1cmEtdXNlci1pZCI6IjNmMjE5NDRhLTE1ZGMtNDEzZi05ZTBhLTdhNmZmMTA0ZDk1NCJ9LCJyb2xlIjpbInVzZXIiXSwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwIiwic3ViIjoiM2YyMTk0NGEtMTVkYy00MTNmLTllMGEtN2E2ZmYxMDRkOTU0IiwiYXVkIjpbIjQ0YjZlZTMxLTI2MGItNDI3Ni1iNTg4LTg5Yjg5MzQ1NjIwYSJdLCJleHAiOjE2NzExODE2NjgsImlhdCI6MTY3MTA5NTI2OH0.WEWDKAGktr29QIiOKDj1m-9kuOPH3eCza_6R0o_sCgnZTFOZlNQqmOQYl4A-sV-94WHstbHItWxy1cqw21Z0D-ccd8NuAuOEtr67PoaCAvIwSSqjDAFmsAmOBVP-2WdACFGkHnpoyw9SFWenfORSIG_L7bCSlftUvbbeMQ8ppfMkvBBorV7qJI6GTfKEpwsYvrz5Lecx8R0RRoJZj5dwLTI62TEChC4pHI_s7Fdzpp8or0jfFRmq0rjT8ow_AIqRBR0642D_D8wYXuisHYMMUPGmUS_ud8A7YvX7kFs39KrNT62MwXKNqcI25au4zgM5ZpjTYRq1LQdhhUOGPspUlQ"
//     }
// }


export default function (token) {
    if (token) {
        const enabled = ref(false);
        const { set } = useStore();

        const { mutate, onDone, loading, onError } = useMutation(verifyEmailQuery, () => ({
            variables: {
                token: token
            }
        }));




        onDone(({ data }) => {
            if (data) {
                cleanResult(data)
            }
        })

        const cleanResult = computed((data) => {
            let claims;
            if (data.verify_account) {
                console.log("verified email", data.verify_account.accessToken);
                const accessToken = data.verify_account.accessToken;
                console.log("token", accessToken);
                claims = token.split('.')[1];
                claims = JSON.parse(window.atob(claims));
                claims.access_token = accessToken;
                localStorage.setItem("session", JSON.stringify(claims));
                set(claims);
                console.log("claims", claims);
            }

            return claims;
        })

        const onResult = (cb) => {
            watch(
                () => cleanResult.value,
                (newVal) => {
                    cb(newVal);
                }
            );
        };

        onError((err) => {
            // error.value = true;
            // notify(parse(err));
            console.log("sending token error", err);
        });
        return {
            mutate,
            loading,
            onResult
        }
    }
}