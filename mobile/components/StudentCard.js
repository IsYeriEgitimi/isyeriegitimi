import React from 'react';
import { StyleSheet, View } from 'react-native';
import { Avatar, Card, Paragraph, Text, Title } from 'react-native-paper';
import StudentImage from '../assets/student.png';

const StudentCard = (props) => {

    const { student }  = props;

    const LeftContent = () => (
        <Avatar.Image
            source={StudentImage}
          style={{ backgroundColor: '#F7F3F9' }}
          size={45} />);

    return (
        <Card style={styles.card}>
           <Card.Title 
           title={student.studentFullName+" - "+ student.studentNo} 
           subtitle={student.studentCompany} 
           left={LeftContent} 
           />
        </Card>
    );
};

const styles = StyleSheet.create({
    card: {
        marginHorizontal: 20,
        marginVertical: 10,
        backgroundColor: '#FCFBFD'
    }
});
export default StudentCard;