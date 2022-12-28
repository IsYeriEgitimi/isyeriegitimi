import { applyMiddleware, legacy_createStore } from 'redux';
import authReducer from './reducers/AuthReducer';
import thunk from 'redux-thunk';



export default function configureStore() {

    const store = legacy_createStore(authReducer, applyMiddleware(thunk));
    return store;
}
