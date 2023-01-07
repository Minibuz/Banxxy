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