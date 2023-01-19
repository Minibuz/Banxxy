import {getCurrentInstance, reactive, toRefs, watch } from "vue";

export const useRouter = () => {
    const vm = getCurrentInstance().proxy
    const state = reactive({
        route: vm.$route,
    })

    watch(
        () => vm.$route,
        r => {
            state.route = r
        },
    )

    return {...toRefs(state), router: vm.$router}
}

export const _ = null

export const can = (role) => {
    if(role===null){
        return false;
    }
    const user = JSON.parse(localStorage.getItem('user'));
    const r = user.roles[0]
    //const r = this.$store.state.auth.user.roles[0]
    if(r === null){
        return false;
    }
    switch (r) {
        case "ROLE_ADVISOR" :
            return "advisor" === role
        default :
            return "customer" === role
    }
}