<template>
  <v-form>
    <div class="userInfo">
    <v-container>
      <v-row>
        <v-col
            cols ="8"
            md="4">
          <v-text-field
            v-model="firstname"
            :rules="nameRules"
            :counter="10"
            label="First name"
            required>
          </v-text-field>
        </v-col>
        <v-col
            cols ="8"
            md="4">
          <v-text-field
              v-model="lastname"
              :rules="nameRules"
              :counter="10"
              label="Last name"
              required>
          </v-text-field>
        </v-col>

      </v-row>

      <v-row>
        <v-col
            cols="8"
            md="8"
        >
          <v-text-field
              v-model="email"
              :rules="emailRules"
              label="E-mail"
              required
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col
            cols="8"
            md="8"
        >
          <v-text-field
              v-model="username"
              :rules="usernameRules"
              label="User name"
              required
          ></v-text-field>
        </v-col>
      </v-row>

      <v-row>
        <v-col
            cols="8"
            md="8"
        >
          <v-text-field
              v-model="password"
              :rules="passwordRules"
              label="Password"
              required
          ></v-text-field>
        </v-col>
      </v-row>


    </v-container>
    </div>
  </v-form>

</template>

<script>
export default {
  name: "UserCreation",
  data: () => ({
    customer:{
      firstname:'',
      lastname:'',
      email:'',
      username:'',
      password:'',
      advisorId:'',
    },
    valid:false,
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
      v => !! v || "Name is required",
      v => v.length >= 8 || "Password must be more than 8 characters"
    ]
  }),
}


</script>

<style scoped>
.userInfo{
  width: 50%;
}

</style>