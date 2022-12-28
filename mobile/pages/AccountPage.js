import React from 'react';
import { StyleSheet, View } from 'react-native';
import { Button, Text } from 'react-native-paper';
import { Avatar } from 'react-native-paper';
import { useDispatch, useSelector } from 'react-redux';
import StudentImage from '../assets/student.png';
import { useApiProgress } from '../components/ApiProgress';
import { logoutHandler } from '../store/actions/AuthActions';

const AccountPage = () => {
    const { displayName, role} = useSelector((store) => {
        return { displayName: store._z.displayName, role: store._z.role};
    });

    const pendingApiCall = useApiProgress("post", "http://192.168.1.100:8080/api/1.0/auth/logout");
    const dispatch = useDispatch();
    const { account } = useSelector((store) => {
        return { account: store._z };
    });


    const onClickLogout = async () => { 
        await dispatch(logoutHandler());
    }
    return (
        <>
        <View style={styles.view}>
            <Avatar.Image style={styles.avatar} size={72} source={StudentImage} />
            <Text style={styles.displayName} variant="headlineSmall">{displayName}</Text>
            <Text style={styles.displayName} variant="titleMedium">{role.name}</Text>
            <Button loading={pendingApiCall} onPress={onClickLogout} style= {styles.button}>Çıkış yap</Button>
        </View>
        </>
    );
};

const styles = StyleSheet.create({
    view: {
        height: '100%',
        backgroundColor: 'white',
        alignItems: 'center'
    },
    avatar: {
        marginTop: 40
    },
    displayName :{
        marginTop : 10,
        color: 'gray'
    },
    button: {
        borderColor: '#6E58A7',
        borderWidth: 1,
        marginTop: 30
    }
});

export default AccountPage;