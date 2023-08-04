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
    <button @click="download" :disabled="!fileName">Download</button>
    <br />
    <a v-if="downloadLink" :href="downloadLink" :download=fileName>Download Converted File</a>
    </div>
    
    
  </div>
</template>

<script>
import ChatService from "../services/ChatService.js";
import DownloadService from "../services/DownloadService.js";
export default {
  name: 'HomePage',
  props: {
    msg: String
  },
  data() {
    return {
      fileName: null,
      downloadLink: null,
      OutputFile: null
    };
  },
  methods: {
    onFileChange(event) {
      // Get the selected file from the input event
      this.file = event.target.files[0];
    },
    uploadFile() {
    //print the success message
      ChatService.chatRequest(this.file).then((response) => {
        // this.downloadLink = response.data.downloadLink;
        this.OutputFile = response.data;
        console.log(this.OutputFile);
        this.fileName = this.OutputFile.fileName;
        alert(`${this.file.name} has been successfully summarized`)
      })
      .catch((error) => {
        console.error(error);
      })

    },
    download(){
      DownloadService.downloadFiles(this.fileName).then((response) => {
       
        const blob = new Blob([response.data], { type: "application/octet-stream" });
        const link = document.createElement('a');
        link.href =URL.createObjectURL(blob);
        link.download = this.fileName;
        link.click();
      })
      .catch((error) => {
        console.error('Error downloading file:', error);
      });
    }
  },
  
};
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
