import * as ACTIONS from "../Constans";

const defaultState = {
    isLoggedIn: false,
    username: undefined,
    displayName: undefined,
    role: undefined
}

const authReducer = (state = { ...defaultState }, action) => {
    
    if (action.type === ACTIONS.LOGIN_SUCCESS) {
        return {
            ...action.payload,
            isLoggedIn: true
        };
    }else if(action.type === ACTIONS.LOGOUT_SUCCESS){
        return defaultState;
    }
    
    return state;
}

export default authReducer;

