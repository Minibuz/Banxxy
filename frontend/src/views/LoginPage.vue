<template>
  <div class="container-fluid">
    <div class="row no-gutter">

      <div class="col-md-6 d-none d-md-flex bg-image"></div>

      <div class="col-md-6">
        <div class="login d-flex align-items-center py-5">


          <div class="container">
            <div class="row">
              <div class="col-lg-10 col-xl-7 mx-auto">
                <h3 class="display-4">Banxxy</h3>
                <p class="text-muted mb-4">Reactive Bank application for your safe transaction.</p>
                <form>
                  <div class="mb-3">
                    <input id="inputEmail" type="email" placeholder="Email address" required="" autofocus="" class="form-control rounded-pill border-0 shadow-sm px-4" />
                  </div>
                  <div class="mb-3">
                    <input id="inputPassword" type="password" placeholder="Password" required="" class="form-control rounded-pill border-0 shadow-sm px-4 text-primary" />
                  </div>

                  <div class="d-grid gap-2 mt-2">
                    <button type="submit" class="btn btn-primary btn-block text-uppercase mb-2 rounded-pill shadow-sm" @click="handleLogin">Sign in</button>
                  </div>

                </form>
              </div>
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>
</template>

<script>
export default {
  name: 'LoginPage',
  props: {
    msg: String
  },
  data() {
    return {
      form: {mail: null, password: null},
      errors: {}
    }
  },
  methods: {
    increment() {
      this.$store.commit('increment')
      console.log(this.$store.state.count)
    },

    //this is a API Call example
    async handleLogin(){
      //when need to give token to the backEnd
      const config = {
        //choose the method you need
        method: 'POST',
        //don't change the headers
        headers: {
          'Authorization': `Bearer ${this.$store.state.token}`
        },
        //only if you use POST, PUT, PATCH ...
        // this is an example
        body: JSON.stringify({
          userId: 1,
          token:  'randomToken',
        })
      }
      console.log(config)
      try {
        //to test if the fetch is working i use pokemon API
        const response = await fetch('https://pokeapi.co/api/v2/pokemon');
        const { results: data } = await response.json()
        console.log(data)
      }catch (error){
        console.log(error);
      }

    }
  }
}
</script>

<style>
.login,
.image {
  min-height: 100vh;
}
.bg-image {
  background-image: url('@/assets/login.jpg');
  background-size: cover;
  background-position: center center;
}
</style>