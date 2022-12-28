import React from 'react';
import { StyleSheet, Image, View } from 'react-native';
import AppLogo from '../assets/logo.png';

class TopBar extends React.Component {
  render() {
    return (
      <View style={styles.container}>
        <Image source={AppLogo} style={styles.logo}/>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    alignSelf: 'center',
  },
  logo:{
    marginTop: 35,
    marginBottom: 30,
    width: 66,
    height: 58,
  }
});

export default TopBar;