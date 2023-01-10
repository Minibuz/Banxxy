<template>
  <v-app :theme="myCustomLightTheme">

    <v-app-bar>
      <v-spacer></v-spacer>

      <v-btn
          :prepend-icon="theme.global.name.value === 'myCustomLightTheme' ? 'mdi-weather-sunny' : 'mdi-weather-night'"
          @click="toggleTheme"
      >Toggle Theme</v-btn>
    </v-app-bar>

    <v-main>
      <slot></slot>
    </v-main>
  </v-app>
</template>


<script>
import { useTheme } from 'vuetify'
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