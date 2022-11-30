import axios from 'axios';

export const getAllCompany = () => {
    return axios.get('/api/1.0/company/getAll');
}

export const loginRequest = (credentials) => {
    return axios.post('/api/1.0/auth', credentials);
}

export const logoutRequest = () => {
    return axios.post('/api/1.0/logout');
}










export const setAuthorizationHeader = ({ token, isLoggedIn }) => {
    if (isLoggedIn) {
        const authorizationValue = `Bearer ${token}`
        return axios.defaults.headers['Authorization'] = authorizationValue;
    } else {
        delete axios.defaults.headers['Authorization'];
    }
}