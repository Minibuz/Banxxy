<template>
      <v-container>
        <v-row
               align="center"
               justify="center">
          <v-col
              v-for="n in 3"
              :key="n"
              cols="auto">
            <v-card
                class="mx-auto"
                color="#00000"

                max-width="344"
            >
              <v-card-text>
                <v-list-item>
                  <template v-slot:prepend>
                    <v-icon icon="mdi-wallet"></v-icon>
                  </template>
                  <v-list-item-title>Balance</v-list-item-title>
                </v-list-item>
              </v-card-text>

              <v-card-text class="text-h4 px-5">
                €1,455
              </v-card-text>
            </v-card>
          </v-col>
        </v-row>


        <v-table>
          <thead>
          <tr>
            <th class="text-left">
              Name
            </th>
            <th class="text-left">
              Calories
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="item in desserts"
              :key="item.name"
          >
            <td>{{ item.name }}</td>
            <td>{{ item.calories }}</td>
          </tr>
          </tbody>
        </v-table>

      </v-container>

</template>


<script>
import { useTheme } from 'vuetify'
export default {
  setup () {
    const theme = useTheme()
    return {
      theme,
      toggleTheme: () => theme.global.name.value = theme.global.current.value.dark ? 'myCustomLightTheme' : 'dark'
    }
  },
  data () {
    return {
      drawer: true,
      items: [
        { title: 'Home', icon: 'mdi-home-city' },
        { title: 'My Account', icon: 'mdi-account' },
        { title: 'Users', icon: 'mdi-account-group-outline' },
      ],

      user : {firstname: 'Remi', lastname: 'Forax'},
      rail: true,
      desserts: [
        {
          name: 'Frozen Yogurt',
          calories: 159,
        },
        {
          name: 'Ice cream sandwich',
          calories: 237,
        },
        {
          name: 'Eclair',
          calories: 262,
        },
        {
          name: 'Cupcake',
          calories: 305,
        },
        {
          name: 'Gingerbread',
          calories: 356,
        },
        {
          name: 'Jelly bean',
          calories: 375,
        },
        {
          name: 'Lollipop',
          calories: 392,
        },
        {
          name: 'Honeycomb',
          calories: 408,
        },
        {
          name: 'Donut',
          calories: 452,
        },
        {
          name: 'KitKat',
          calories: 518,
        },
      ],
    }
  },
  methods: {
     userName(){
        return this.$data.user.firstname +" "+this.$data.user.lastname;
     },
  }
}
</script>

<style scoped>

</style>