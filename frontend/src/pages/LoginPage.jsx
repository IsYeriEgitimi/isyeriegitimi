import React from 'react';
import { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Button, Form, Container, Grid, Message } from 'semantic-ui-react';
import { useApiProgress } from './../shared/ApiProgress';
import { loginHandler } from '../store/actions/AuthActions';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
    const pendingApiCall = useApiProgress("post", "/api/1.0/auth/login");
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [error, setError] = useState();
    const dispatch = useDispatch();
    const navigate = useNavigate();



    useEffect(() => { setError(undefined) }, [username, password]);

    const onClickLogin = async (event) => {
        event.preventDefault();
        const creds = {
            username,
            password
        }
        try {
            await dispatch(loginHandler(creds));
            navigate("/admin-panel");
        } catch (apiError) {
            if (apiError.response.data.validationErrors) {
                setError(apiError.response.data.validationErrors);
            }
            if (apiError.response.data.message) {
                setError(apiError.response.data.message);
            }
        }

    }

    return (
        <Container>
            <Grid>
                <Grid.Row centered>
                    <Grid.Column width={6}>
                        <Form>
                            <Form.Field>
                                <label>Kullanıcı adı</label>
                                <input placeholder='Kullanıcı adı' onChange={(event) => { setUsername(event.target.value) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Şifre</label>
                                <input placeholder='Şifre' onChange={(event) => { setPassword(event.target.value) }} />
                            </Form.Field>
                            <Form.Field>
                                <Form.Field>
                                    
                                   {error && <Message size='tiny' color='yellow'>{error}</Message> }
                                </Form.Field>
                            </Form.Field>
                            <div>
                                <Button loading={pendingApiCall} inverted color='violet' type='submit' onClick={onClickLogin}>Giris yap</Button>
                            </div>
                        </Form>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Container>
    );
};

export default LoginPage;