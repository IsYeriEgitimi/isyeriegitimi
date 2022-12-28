import * as React from 'react';
import { createMaterialBottomTabNavigator } from '@react-navigation/material-bottom-tabs';
import HomePage from './../pages/HomePage';
import Student from '../pages/StudentPage';
import Icon from 'react-native-vector-icons/Ionicons';
import { useSelector } from 'react-redux';
import LoginPage from './../pages/LoginPage';
import AccountPage from './../pages/AccountPage';

const Tab = createMaterialBottomTabNavigator();

const MyNavigation = () => {
  const { isLoggedIn, displayName } =  useSelector((store) => {
    return { isLoggedIn: store._z.isLoggedIn, displayName: store._z.displayName };
  });
  return (
    <Tab.Navigator>
      <Tab.Screen
        name="Companies"
        options={{
          title: 'Şirketler', tabBarIcon: () => (
            <Icon name="business-outline" color={'#6E58A7'} size={22} />
          )
        }}
        component={HomePage}
      />
      <Tab.Screen
        name="Students"
        options={{
          title: 'Öğrenciler', tabBarIcon: () => (
            <Icon name="person-outline" color={'#6E58A7'} size={22} />
          )
        }}
        component={Student}
      />
      {isLoggedIn &&
        <Tab.Screen
          name="Account"
          options={{
            title: (displayName), tabBarIcon: () => (
              <Icon name="person-outline" color={'#6E58A7'} size={22} />
            )
          }}
          component={AccountPage}
        />
      }
      {!isLoggedIn &&
        <Tab.Screen
          name="Login"
          options={{
            title: 'Giriş yap', tabBarIcon: () => (
              <Icon name="person-outline" color={'#6E58A7'} size={22} />
            )
          }}
          component={LoginPage}
        />
      }
    </Tab.Navigator>

  );
};
export default MyNavigation;