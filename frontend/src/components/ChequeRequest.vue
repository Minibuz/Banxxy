<template>
  <v-btn
      variant="flat"
      color="primary"
      rounded="lg"
      @click="dialogChequeRequest=true"
      class="mr-2">
      demander un chéquier
  </v-btn>

  <v-dialog
      v-model="dialogChequeRequest"
      max-width="600"
  >
    <v-card>
      <v-toolbar
          color="primary">
        <v-toolbar-title>Demander un chequier</v-toolbar-title>
      </v-toolbar>

      <v-card-text>
        Voulez-vous commander un chéquier ?
      </v-card-text>

      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn color="grey"  @click="dialogChequeRequest = false">Annuler</v-btn>
        <v-btn
            color="primary"
            variant="text"
            @click="handleChequier"
        >
          Commander
        </v-btn>
      </v-card-actions>

    </v-card>

  </v-dialog>
</template>

<script>
import authHeader from "@/services/auth-header";
import {useToast} from "vue-toastification";

export default {
  name: "ChequeRequest",

  setup(){
    const toast = useToast();
    return {
      toast,
    }
  },
  data() {
    return{
      dialogChequeRequest: false,
    }
  },
  methods:{
    async handleChequier() {

      //await CreateCustomer.create(myCustomer)
      const API_URL = '/api/jms';

        await fetch(`${API_URL}`, {
          method: 'GET',
          headers: authHeader(),
        });
        this.toast.success("Demande envoyée");
        this.dialogChequeRequest = false;
        }
  }
}
</script>

<style scoped>

</style>