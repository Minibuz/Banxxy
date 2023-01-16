import AuthService from '../services/auth.service';

const user_info = JSON.parse(localStorage.getItem('user'));
const initialState = user_info
    ? { status: { loggedIn: true }, user_info }
    : { status: { loggedIn: false }, user_info: null };

export const auth = {
    namespaced: true,
    state: initialState,
    actions: {
        login({ commit }, user) {
            // return AuthService.login(user).then(
            //     user => {
            //         commit('loginSuccess', user);
            //         return Promise.resolve(user);
            //     },
            //     error => {
            //         commit('loginFailure');
            //         return Promise.reject(error);
            //     }
            localStorage.setItem('user', JSON.stringify({token : "TOKEN-12345"}));
            commit('loginSuccess', user);
            return Promise.resolve(user);
        },
        logout({ commit }) {
            AuthService.logout();
            commit('logout');
        },

    },
    mutations: {
        loginSuccess(state, user) {
            state.status.loggedIn = true;
            state.user = user;
        },
        loginFailure(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        logout(state) {
            state.status.loggedIn = false;
            state.user = null;
        },
        registerSuccess(state) {
            state.status.loggedIn = false;
        },
        registerFailure(state) {
            state.status.loggedIn = false;
        }
    }
};