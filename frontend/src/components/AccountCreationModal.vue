<template>
  <v-btn
      variant="flat"
      color="primary"
      rounded="lg"
      @click="dialogCreatUser=true"
  class="mr-2">
    Créer un compte
  </v-btn>

  <v-dialog
      v-model="dialogCreatUser"
      fullscreen

  >
    <v-card >
      <v-toolbar
          dark
          color="primary"
      >
        <v-btn
            icon
            dark
            @click="dialogCreatUser = false"
        >
          <v-icon>mdi-close</v-icon>
        </v-btn>
        <v-toolbar-title></v-toolbar-title>
        <v-spacer></v-spacer>
      </v-toolbar>

      <AccountCreation :user="user" @close="dialogCreatUser= false"></AccountCreation>

    </v-card>
  </v-dialog>
</template>

<script>
import AccountCreation from "@/components/AccountCreation";
export default {
  name: "AccountCreationModal",
  props:{
    user: Object,
  },
  components: {AccountCreation},
  setup(){
    return{

    }
  },
  data() {
    return{
      dialogCreatUser: false,
    }
  },
}
</script>

<style scoped>

</style>