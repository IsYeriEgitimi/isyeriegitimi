import React, { useEffect, useState } from 'react';
import { ScrollView, StyleSheet, View } from 'react-native';
import { Button, Chip, Text, TextInput } from 'react-native-paper';
import { useApiProgress } from './../components/ApiProgress';
import { useNavigation } from '@react-navigation/native';
import { useDispatch } from 'react-redux';
import { loginHandler } from './../store/actions/AuthActions';

const LoginPage = () => {
    const pendingApiCall = useApiProgress("post", "http://192.168.1.100:8080/api/1.0/auth/login");
    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const [error, setError] = useState();
    const navigation = useNavigation();
    const dispatch = useDispatch();

    useEffect(() => { setError(undefined) }, [username, password]);

    const onClickLogin = async (event) => {
        event.preventDefault();
        const creds = {
            username,
            password
        }
        try {
            await dispatch(loginHandler(creds));
            //this.props.navigation.navigate('Companies');
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
        <ScrollView style={styles.view}>


            <TextInput style={styles.textInput}
                mode="outlined"
                label="Kullanıcı adı"
                onChangeText={(text) => { setUsername(text) }}
            />

            <TextInput style={styles.textInput}
                mode="outlined"
                label="Şifre"
                secureTextEntry={true}
                onChangeText={(text) => { setPassword(text) }}
            />

            {error && <Chip style={styles.chip} icon="information">{error}</Chip>}

            <Button loading={pendingApiCall} style={styles.loginButton} icon="login" mode="contained" onPress={onClickLogin}>
                Giriş yap
            </Button>
        </ScrollView>




    );
};

const styles = StyleSheet.create({

    textInput: {
        marginHorizontal: 30,
        marginTop: 50
    },
    loginButton: {
        marginTop: 50,
        width: 150,
        marginLeft: 'auto',
        marginRight: 'auto'
    },
    view: {
        backgroundColor: 'white',
        height: '100%'
    },
    chip : {
        marginHorizontal: 30,
        marginVertical: 20,
        height : 50,
    }

});

export default LoginPage;