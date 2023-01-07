// Create a new store instance.
import {createStore} from "vuex";

const store = createStore({
    state () {
        return {
            count: 0,
            auth: false,
            user: null,
            token: null,
            role: null,
            timeout: null
        }
    },
    mutations: {
        increment (state) {
            state.count++
        },
        setAuth(state, data){
            state.auth = true;
            state.user = data.user;
            state.token = data.token;
            state.role = data.role;
            state.timeout = Date.now();
        },
        resetAuth(state){
            state.auth = false;
            state.user = null;
            state.token = null;
            state.role = null;
            state.timeout = null;
        }
    }
});

export default store;