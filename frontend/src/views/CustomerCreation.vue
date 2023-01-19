<template>
  <v-form
      ref="form"
      v-model="valid"
  >
    <div class="userInfo">
    <v-container>
      <v-row>
        <v-col cols ="8" md="4">
          <v-text-field
            v-model="customer.firstName"
            :rules="nameRules"
            :counter="10"
            label="First name"
            required>
          </v-text-field>
        </v-col>
        <v-col cols ="8" md="4">
          <v-text-field
              v-model="customer.lastName"
              :rules="nameRules"
              :counter="10"
              label="Last name"
              required>
          </v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8" md="8">
          <v-text-field
              v-model="customer.mail"
              :rules="emailRules"
              label="E-mail"
              required
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8" md="8">
          <v-text-field
              v-model="customer.userName"
              :rules="usernameRules"
              label="User name"
              required
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col cols="8" md="8">
          <v-text-field
              :append-icon="showPwd ? 'mdi-eye' : 'mdi-eye-off'"
              v-model="customer.password"
              :rules="passwordRules"
              :type="showPwd ? 'text' : 'password'"
              label="Password"
              required
              @click:append="showPwd = !showPwd"
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-btn
            class="ml-4 mr-4 "
            type="submit"
            @click="handleCreation"
            :disabled="!valid"
        >
          submit
        </v-btn>
        <v-btn @click="clear">
          clear
        </v-btn>
      </v-row>

    </v-container>
    </div>
  </v-form>

</template>

<script>
import CreateCustomer from "@/services/customer.create";

export default {
  name: "UserCreation",
  data: () => ({
    valid:true,
    customer:{
      firstName:'',
      lastName:'',
      mail:'',
      userName:'',
      password:'',
      advisorId:'',
    },

    nameRules:[
        v => !! v || "Name is required",
        v => v.length <= 10 || "Name must be less than 10 characters"
    ],
    emailRules:[
      v => !! v || "Email is required",
      v => /.+@+/.test(v) || "Email must be valid"
    ],
    usernameRules:[
      v => !! v || "Name is required",
    ],
    passwordRules:[
      v => !! v || "Password is required",
      v => v.length >= 8 || "Password must be more than 8 characters"
    ],
    showPwd:false
  }),
  methods:{
     async handleCreation(){
       if(this.$refs.form.validate()){
         const myCustomer = this.$data.customer
         console.log(myCustomer)
         await CreateCustomer.create(myCustomer)
       }

    },
    clear(){
      this.customer.firstName = ''
      this.customer.lastName = ''
      this.customer.mail = ''
      this.customer.userName = ''
      this.customer.password = ''
      this.customer.advisorId = ''
    }
  //  TODO handle invalid of submit
  }
}


</script>

<style scoped>
.userInfo{
  width: 50%;
}

</style>