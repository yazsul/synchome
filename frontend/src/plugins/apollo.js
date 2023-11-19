import { ApolloClient, ApolloLink, createHttpLink, InMemoryCache,from } from '@apollo/client/core'
import { onError } from '@apollo/client/link/error'
import useUser from "@/use/user";
import router from '@/router';
const { token, unset, isLoggedIn } =
    useUser();

// HTTP CONNECTION TO THE API

const default_http_link = createHttpLink({
    // You should use an absolute URL here
    // uri: 'https://graph.primary.hahu.jobs/v1/graphql',
})

const default_error_link = onError(({ graphQLErrors, networkError }) => {
    if (graphQLErrors && graphQLErrors[0].extensions.code === "invalid-jwt") {
        unset();
        router.replace("/");
        return;
    }
})


const default_auth_link = new ApolloLink((operation, forward) => {
    const { headers } = operation.getContext();

    const h = {
        ...headers
    }

    if(isLoggedIn.value){
        h.authorization = `Bearer ${token()}`
    }

    operation.setContext({
        headers: h
    })

    return forward(operation);

})

// Cache implementation
const cache = new InMemoryCache();

const apolloClient = new ApolloClient({
    link: from([default_auth_link, default_error_link, default_http_link]),
    cache,
})

export { apolloClient }
