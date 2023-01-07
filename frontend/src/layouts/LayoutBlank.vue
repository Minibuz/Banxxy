<template>
  <v-app :theme="myCustomLightTheme">
    <v-main>
      <slot></slot>
    </v-main>
  </v-app>
</template>

<script>
import { useTheme } from 'vuetify';

export default {
  setup() {
    const theme = useTheme()
    return {
      theme,
      toggleTheme: () => theme.global.name.value = theme.global.current.value.dark ? 'myCustomLightTheme' : 'dark'
    }
  },
}
</script>

<style scoped>

</style>