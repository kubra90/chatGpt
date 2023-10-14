<template>
  <div>
   <img alt="Vue logo" src="./assets/logo1.jpeg"> 
  <HomePage msg="Upload your research file and enter your prompt"/>
 </div>
</template>

<script>
import HomePage from './components/HomePage.vue'

export default {
  name: 'App',
  components: {
    HomePage
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
  background-color: #e4e5e9;
  margin-top: 60px;
}
</style>
