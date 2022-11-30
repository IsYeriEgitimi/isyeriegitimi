import React from 'react';
import { Button } from 'semantic-ui-react';
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { logoutHandler } from '../store/actions/AuthActions';
import { useApiProgress } from './../shared/ApiProgress';

const AdminPanel = () => {
    const pendingApiCall = useApiProgress("post", "/api/1.0/logout");
    const dispatch = useDispatch();
    const navigate = useNavigate();  

    const onClickLogout = async () => { 
        await dispatch(logoutHandler());
        navigate("/admin-login");
    }

    return (
        <div>
            <Button loading={pendingApiCall} inverted color='violet' type='submit' onClick={onClickLogout}>Çıkış yap</Button>
        </div>
    );
};

export default AdminPanel;