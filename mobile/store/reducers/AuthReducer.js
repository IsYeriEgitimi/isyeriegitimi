import * as ACTIONS from "../Constans";
import { setAuthorizationHeader } from "../../api/ApiCalls";

const defaultState = {
    isLoggedIn: false,
    id: undefined,
    username: undefined,
    displayName: undefined,
    role: undefined,
    token: undefined
}


export default async function authReducer(state = defaultState, action) {

    if (action.type === ACTIONS.LOGIN_SUCCESS) {
        setAuthorizationHeader({
            ...action.payload,
            isLoggedIn: true
        });
        return {
            ...action.payload,
            isLoggedIn: true
        };
    } else if (action.type === ACTIONS.LOGOUT_SUCCESS) {
        setAuthorizationHeader({
            defaultState
        });
        return defaultState;
    } else {
        return defaultState;
    }
}
