import axios from 'axios';

const http = axios.create( {
    baseURL: 'http://localhost:9000'
})

export default {
    chatRequest(file){
        return http.post('/chat', file);
    }
}