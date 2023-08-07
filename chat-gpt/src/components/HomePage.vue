<template>
  <div class="hello">
    <h1>{{ msg }}</h1>
    <!-- <p>
      For a guide and recipes on how to configure / customize this project,<br>
      check out the
      <a href="https://cli.vuejs.org" target="_blank" rel="noopener">vue-cli documentation</a>.
    </p> -->
    <div>
    <input type="file" @change="onFileChange" />
    <button @click="uploadFile">Upload</button>
    <button @click="download" :disabled="!OutputFile">Download</button>
    <br />
    <a v-if="downloadLink" :href="downloadLink" :download="OutputFile">Download Converted File</a>
    </div>
    
    
  </div>
</template>

<script>
import ChatService from "../services/ChatService.js";
// import DownloadService from "../services/DownloadService.js";
export default {
  name: 'HomePage',
  props: {
    msg: String
  },
  data() {
    return {
      downloadLink: null,
      OutputFile: null
    };
  },
  methods: {
    onFileChange(event) {
      // Get the selected file from the input event
      this.file = event.target.files[0];
    },
    // uploadFile() {
    // //print the success message
    //   ChatService.chatRequest(this.file).then((response) => {
    //     // this.downloadLink = response.data.downloadLink;
    //     this.OutputFile = response.data
    //     console.log(this.OutputFile);
    //     // console.log(this.OutputFile.fileName);
    //     // this.fileName = this.OutputFile.fileName;
    //     // console.log(this.fileName)
    //     alert(`${this.file.name} has been successfully summarized`)
    //   })
    //   .catch((error) => {
    //     console.error(error);
    //   })

    // },
    // download(){
    //   DownloadService.downloadFiles(this.OutputFile).then((response) => {
    //    // download method accept file before calling this method, you should convert data type to file 
    //     const blob = new Blob([response.data], { type: "application/octet-stream" });
    //     const link = document.createElement('a');
    //     link.href =URL.createObjectURL(blob);
    //     link.download = this.OutputFile;
    //     // console.log(this.OutputFile);
    //     link.click();
    //   })
    //   .catch((error) => {
    //     console.error('Error downloading file:', error);
    //   });
    // }

uploadFile() {
  ChatService.chatRequest(this.file)
    .then((response) => {
      this.OutputFile = response.data;
      console.log(this.OutputFile);
      alert(`${this.file.name} has been successfully summarized`)
      // Convert the response data to a Blob
      // const blob = new Blob([JSON.stringify(this.OutputFile)], { type: "application/json" });

      // // Create a temporary link element to initiate the download
      // const link = document.createElement("a");
      // link.href = URL.createObjectURL(blob);
      // link.download = "output.csv"; // You can provide a default file name here
      // link.click();
    })
    // .catch((error) => {
    //   console.error(error);
    // });
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
    const escapedValue = `"${cellValue.replace(/"/g, '""')}"`;
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
  color: #42b983;
}
</style>
