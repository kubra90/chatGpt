import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:9000'
})

export default {
    chatRequest(file, promptText) {
      // Create a FormData object and append the selected file to it
      const formData = new FormData();
      formData.append('file', file);
      formData.append('promptText', promptText);
  
      // Send the file using Axios with the correct headers
      return http.post('/chat', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
    },
  };