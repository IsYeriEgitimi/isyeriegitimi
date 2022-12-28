import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import { Provider } from 'react-redux';
import { legacy_createStore } from 'redux';
import MyNavigation from './components/Navigation';
import TopBar from './components/TopBar';
import Company from './pages/CompanyPage';
import configureStore  from './store/configureStore';

export default function App() {
  const Stack = createNativeStackNavigator();

  return (
    <>
    <Provider store={configureStore()}>
      <TopBar />
      <NavigationContainer>
        <Stack.Navigator screenOptions={{ headerShown: false }}>
          <Stack.Screen name='Home' component={MyNavigation}/>
          <Stack.Screen name='CompanyReview' options={{title: null, headerShown: true}} component={Company}/>
        </Stack.Navigator>
        
      </NavigationContainer>
    </Provider>
    </>
  );
}