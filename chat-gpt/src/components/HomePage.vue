<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <h1>you'll get the result</h1>
    <!-- <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p> -->
    <div>
    <input type="file" @change="onFileChange" />

    <!-- add text input here-->
    <input type='text' v-model="promptText" placeholder= "Enter your prompt!">
    <button class="custom-button" @click="uploadFile">Upload</button>
    <button class="custom-button" @click="download" :disabled="!OutputFile">Download</button>
    <br />
    <a v-if="downloadLink" :href="downloadLink" :download="OutputFile">Download Converted File</a>
    </div>
    
    
  </div>
</template>

<script>
import ChatService from "../services/ChatService.js";
export default {
  name: 'HomePage',
  props: {
    msg: String
  },
  data() {
    return {
      downloadLink: null,
      OutputFile: null,
      // file: null,
    
      promptText: '',
    };
  },
  methods: {
    onFileChange(event) {
      // Get the selected file from the input event
      this.file = event.target.files[0];
    },


 

uploadFile() {
  ChatService.chatRequest(this.file, this.promptText)
    .then((response) => {
      this.OutputFile = response.data;
      console.log(this.OutputFile);
      alert(`${this.file.name} has been successfully summarized`)
      
    })
  
},
download() {
  //convert array of objects to csv string
  const csvData = this.convertToCSV(this.OutputFile);

  //create a blob from a csv file.
const blob = new Blob([csvData], { type: "text/csv" });

      // Create a temporary link element to initiate the download
      const link = document.createElement("a");
      link.href = URL.createObjectURL(blob);
      link.download = "output.csv"; // You can provide a default file name here
      link.click();
},
convertToCSV(data){
  const csvRows = [];
  for(const row of data){
    const cellValue = row["content"] ? row["content"].toString() : '';
    //Escape double quotes in the cell value by doubling them
    //this will return the text without double quotes!(if you get result with double quotes, you should add '""'(double quotes) after the backtick
    //and before backtick)
    const escapedValue = `${cellValue.replace(/"/g, '""')}`;
    csvRows.push(escapedValue);
  }
  return csvRows.join('\n'); //combine all rows with newline character
}
}
}

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #e4e5e9;
}

h1 {
font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: black;
}

.custom-button {
  background-color: #3498db;
  color: white;
  border: none;
  padding: 10px 20px;
  margin: 5px;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease;
}


</style>
