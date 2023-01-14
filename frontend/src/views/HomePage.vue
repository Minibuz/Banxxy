<template>
      <v-container>
        <v-row>
        <v-col cols="7">
          <searchBar></searchBar>

          <v-divider></v-divider>

          <div v-if="selectedRow!=null" class="py-2" >
            <v-row
                align="center"
                justify="center">
              <v-col cols="6">
                <div class="pa-2 text-h4">
                  Utilisateur : {{selectedRow.nom}} {{selectedRow.prenom}}
                </div>
              </v-col>
              <v-col
                  v-for="n in 1"
                  :key="n"
                  cols="4">

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
                    {{selectedRow.balance}} €
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col cols="1">
                <v-icon icon="mdi-close" @click="resetSelectedRows" ></v-icon>
              </v-col>

            </v-row>
          </div>

          <v-divider></v-divider>
          <div class="text-h6"> Utilisateur</div>
          <EasyDataTable
              theme-color="#1d90ff"
              table-class-name="customize-table"
              :headers="headers"
              :items="itemsUserTable"
              :rows-per-page="5"
              @click-row="selectRow">

            <template #item-actions="item">
              <div class="operation-wrapper">
                <v-icon icon="mdi-delete"  @click="deleteItem(item)"></v-icon>
              </div>
            </template>

          </EasyDataTable>
          <v-divider></v-divider>


          <div class="text-h6"> Compte</div>
          <EasyDataTable
              theme-color="#1d90ff"
              table-class-name="customize-table"
              :headers="headersCompte"
              :items="itemsCompteTable"
              :rows-per-page="10"
          >

            <template #item-actions="item">
              <div class="operation-wrapper">
                <v-icon icon="mdi-delete"  @click="deleteItem(item)"></v-icon>
              </div>
            </template>

          </EasyDataTable>
        </v-col>
        <v-col cols="5">
          <div class="text-h6"> Transaction</div>
          <EasyDataTable
              theme-color="#1d90ff"
              table-class-name="customize-table"
              :headers="headersTransaction"
              :items="itemsTransactionTable"
              :rows-per-page="10"
          >
          </EasyDataTable>
        </v-col>
        </v-row>
      </v-container>

</template>


<script>
import { useTheme } from 'vuetify'
import searchBar from '@/components/SearchBar'
import Vue3EasyDataTable from 'vue3-easy-data-table'



export default {
  setup () {
    const theme = useTheme();


    const headers = [
      { text: "Identifiant", value: "id" },
      { text: "NOM", value: "nom"},
      { text: "PRENOM", value: "prenom"},
      { text: "ACTIONS", value: "actions"},
    ];
    const headersCompte = [
      { text: "N°Compte", value: "id_compte" },
      { text: "Détenteur", value: "user"},
      { text: "solde", value: "balance"},
      { text: "ACTIONS", value: "actions"},
    ];
    const headersTransaction = [
      { text: "N° Transaction", value: "id_transaction" },
      { text: "Date", value: "date"},
      { text: "Compte source", value: "source"},
      { text: "Compte destination", value: "destination"},
      { text: "Montant", value: "destination"},
    ];

    //TODO pour le moment static mais sera remplacer par une requete qui recupere les utilisateurs
    const itemsUser = [
          { id: "client-0001", nom: "client_1", prenom: "vuejs",balance: 30000},
          { id: "client-0002", nom: "client_2", prenom: "vuejs",balance: 125010},
          { id: "client-0003", nom: "client_3", prenom: "vuejs",balance: 2545},
          { id: "client-0004", nom: "client_4", prenom: "vuejs",balance: 100},
        ];

    //TODO pour le moment static mais sera remplacer par une requete qui recupere les comptes
    const itemsCompte = [
      { id_compte: "compte-0001", user: "client_1", id_client: "client-0001", balance: 30000},
      { id_compte: "compte-0002", user: "client_2", id_client: "client-0002", balance: 125010},
      { id_compte: "compte-0003", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0004", user: "client_4", id_client: "client-0004" ,balance: 100},
      { id_compte: "compte-0005", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0006", user: "client_4", id_client: "client-0004" ,balance: 100},
      { id_compte: "compte-0007", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0008", user: "client_4", id_client: "client-0004" ,balance: 100},
    ];

    //TODO pour le moment static mais sera remplacer par une requete qui recupere les transactions
    const itemsTransaction = [
      { id_compte: "compte-0001", user: "client_1", id_client: "client-0001", balance: 30000},
      { id_compte: "compte-0002", user: "client_2", id_client: "client-0002", balance: 125010},
      { id_compte: "compte-0003", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0004", user: "client_4", id_client: "client-0004" ,balance: 100},
      { id_compte: "compte-0005", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0006", user: "client_4", id_client: "client-0004" ,balance: 100},
      { id_compte: "compte-0007", user: "client_3", id_client: "client-0003", balance: 2545},
      { id_compte: "compte-0008", user: "client_4", id_client: "client-0004" ,balance: 100},
    ];

    return {
      theme,headers,headersCompte,headersTransaction,itemsCompte,itemsUser,itemsTransaction,
      toggleTheme: () => theme.global.name.value = theme.global.current.value.dark ? 'myCustomLightTheme' : 'dark'
    }
  },
  components: {
    searchBar,EasyDataTable: Vue3EasyDataTable
  },
  data () {
    return {
      itemsUserTable : this.itemsUser,
      itemsCompteTable : this.itemsCompte,
      itemsTransactionTable : this.itemsTransaction,
      drawer: true,
      selectedRow: null,
      rail: true,
    }
  },
  methods: {

    //permet de choisir un utilisateur
    selectRow(item){
       this.$data.selectedRow = item;
       this.$data.itemsCompteTable = this.itemsCompte.filter((i) => i.id_client === item.id);
       console.table(item);
    },
    //supprime un utilisateur
    //TODO modale (fenetre) pour demander validation de la suppression
    //TODO requete back pour suppression
    deleteItem(val) {
       console.log(val)
      this.itemsUserTable= this.itemsUserTable.filter((item) => item.id !== val.id);
    },

    //TODO selectCompte permet de selection un compte et fait un appel a l'api pour récuperer les transactions du compte

    //reset les choix et enleve les filtres
    resetSelectedRows(){
      this.$data.selectedRow = null;
      this.$data.itemsCompteTable = this.itemsCompte;

    }
  }
}
</script>

<style>


.customize-table {
  --easy-table-border: 1px solid #445269;
  --easy-table-row-border: 1px solid #445269;

  --easy-table-header-font-size: 14px;
  --easy-table-header-height: 50px;
  --easy-table-header-font-color: #c1cad4;
  --easy-table-header-background-color: #2d3a4f;

  --easy-table-header-item-padding: 10px 15px;

  --easy-table-body-even-row-font-color: #fff;
  --easy-table-body-even-row-background-color: #4c5d7a;

  --easy-table-body-row-font-color: #c0c7d2;
  --easy-table-body-row-background-color: #2d3a4f;
  --easy-table-body-row-height: 50px;
  --easy-table-body-row-font-size: 14px;

  --easy-table-body-row-hover-font-color: #2d3a4f;
  --easy-table-body-row-hover-background-color: #eee;

  --easy-table-body-item-padding: 10px 15px;

  --easy-table-footer-background-color: #2d3a4f;
  --easy-table-footer-font-color: #c0c7d2;
  --easy-table-footer-font-size: 14px;
  --easy-table-footer-padding: 0px 10px;
  --easy-table-footer-height: 50px;

  --easy-table-rows-per-page-selector-width: 70px;
  --easy-table-rows-per-page-selector-option-padding: 10px;
  --easy-table-rows-per-page-selector-z-index: 1;


  --easy-table-scrollbar-track-color: #2d3a4f;
  --easy-table-scrollbar-color: #2d3a4f;
  --easy-table-scrollbar-thumb-color: #4c5d7a;;
  --easy-table-scrollbar-corner-color: #2d3a4f;

  --easy-table-loading-mask-background-color: #2d3a4f;
}
</style>