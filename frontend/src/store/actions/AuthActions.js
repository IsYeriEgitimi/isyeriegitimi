import { loginRequest } from "../../api/ApiCalls";
import * as ACTIONS from '../Constans';
import { logoutRequest } from './../../api/ApiCalls';

export const loginSuccessAction = (authState) => {
    return {
        type: ACTIONS.LOGIN_SUCCESS,
        payload: authState
    };
}
export const logoutSuccessAction = () => {
    return {
        type: ACTIONS.LOGOUT_SUCCESS
    };
}
export const loginHandler = credentials => {
    return async function (dispatch) {
        const response = await loginRequest(credentials);
        const authState = {
            ...response.data.user,
            token: response.data.token
        }
        dispatch(loginSuccessAction(authState));
        return response;
    };
}
export const logoutHandler = () => {
    return async function (dispatch) {
        const response = await logoutRequest();
        dispatch(logoutSuccessAction());
        return response;
    };
}