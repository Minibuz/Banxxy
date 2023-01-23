<template>
  <v-btn
      variant="flat"
      color="primary"
      rounded="lg"
      @click="dialogChequeRequest=true"
      class="mr-2">
      demande chéquier
  </v-btn>

  <v-dialog
      v-model="dialogChequeRequest"
      max-width="600"
  >
    <v-card>
      <v-toolbar
          color="primary">
        <v-toolbar-title>Demander chequier</v-toolbar-title>
      </v-toolbar>

      <v-card-text>
        Etre vous demander chequier ?
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="grey"  @click="dialogChequeRequest = false">Annuler</v-btn>
        <v-btn
            color="primary"
            variant="text"
            @click="requestCheque"
        >
          Demander
        </v-btn>
      </v-card-actions>

    </v-card>

  </v-dialog>
</template>

<script>
export default {
  name: "ChequeRequest",
  data() {
    return{
      dialogChequeRequest: false,
    }
  },
  methods:{
    "requestCheque" : {
      //TODO Fetch API
    }
  }
}
</script>

<style scoped>

</style>