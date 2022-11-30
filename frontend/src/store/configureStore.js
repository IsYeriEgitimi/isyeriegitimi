import { applyMiddleware, legacy_createStore, compose } from 'redux';
import thunk from 'redux-thunk';
import SecureLS from 'secure-ls';
import authReducer from './reducers/AuthReducer';
import { setAuthorizationHeader } from './../api/ApiCalls';


const secureLS = new SecureLS();

const getStateFromLocalStorage = () => {
    let stateInLocalStorage = {
        isLoggedIn: false,
        username: undefined,
        displayName: undefined,
        role: undefined
    }

    const getStateInLocalStorage = secureLS.get('auth'); // localStorage.getItem('auth'); yerine Secure kullanacağız.

    if (getStateInLocalStorage) {
        stateInLocalStorage = getStateInLocalStorage;
    }
    return stateInLocalStorage;
}

const updateStateInStorage = newState => {
    secureLS.set('auth', newState);
}

const configureStore = () => {
    const composeEnhancers = window.__REDUX_DEVTOOLS_EXTENSION_COMPOSE__ || compose;
    const initialState = getStateFromLocalStorage();
    //setAuthorizationHeader(initialState);

    const store = legacy_createStore(authReducer, initialState, composeEnhancers(applyMiddleware(thunk)));
    
    store.subscribe(() => {
        updateStateInStorage(store.getState());
        setAuthorizationHeader(store.getState());
    });

    return store;
}

export default configureStore;