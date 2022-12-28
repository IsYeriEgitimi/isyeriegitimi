import axios from 'axios';

export const getAllCompany = (page = 0) => {
    return axios.get(`http://192.168.1.100:8080/api/1.0/company/getAll`);
}
export const getCommentsCompany = (companyId, page = 0) => {
    return axios.get(`http://192.168.1.100:8080/api/1.0/comment/company/${companyId}`);
}

export const postComment = (body) => {
    return axios.post(`http://192.168.1.100:8080/api/1.0/comment/add`, body);
}

export const getAllStudent = (page = 0) => {
    return axios.get(`http://192.168.1.100:8080/api/1.0/student/getAll`);
}
export const getStudentByDate = (start, end, page = 0) => {
    return axios.get(`http://192.168.1.100:8080/api/1.0/student/findByDate/${start}-${end}`);
}


export const loginRequest = (credentials) => {
    return axios.post('http://192.168.1.100:8080/api/1.0/auth/login', credentials);
}

export const logoutRequest = () => {
    return axios.post('http://192.168.1.100:8080/api/1.0/auth/logout');
}

export const setAuthorizationHeader = ({ token, isLoggedIn }) => {
    if (isLoggedIn) {
        const authorizationValue = `Bearer ${token}`
        return axios.defaults.headers['Authorization'] = authorizationValue;
    } else {
        delete axios.defaults.headers['Authorization'];
    }
}