import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:9000'
})

export default {
    downloadFiles(fileName){
        return http.get(`/download/${fileName}`, {
            responseType : 'blob', // Set the response type to 'blob' for downloading files
        })
    }
}

