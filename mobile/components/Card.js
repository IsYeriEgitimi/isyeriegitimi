import { useNavigation } from '@react-navigation/native';
import React from 'react';
import { StyleSheet } from 'react-native';
import { Button, Card, Title, Paragraph } from 'react-native-paper';

const MyCard = (props) => {
    const { company } = props;
    const navigation = useNavigation();

    const onPressReview = () => {
        navigation.navigate("CompanyReview", { company });
    }

    return (
        <Card style={{ marginHorizontal: 20, marginVertical: 10 }}>
            <Card.Cover source={{ uri: `http://192.168.1.100:8080/images/${company.image}` }}
                resizeMode='center' style={styles.cardCover} />
            <Card.Content>
                <Title style={styles.cardTitle}> {company.companyName} </Title>
                <Paragraph style={styles.cardParagraph}> {company.companyEmail} </Paragraph>
            </Card.Content>
            <Card.Actions>
                <Button onPress={onPressReview}>İncele</Button>
            </Card.Actions>
        </Card>
    );
};

const styles = StyleSheet.create({
    cardTitle: {
        marginTop: 10
    },
    cardParagraph: {
        color: "#6F6F6F"
    },
    cardCover: {
        height: 100,
        backgroundColor: '#FFF'
    }

});
export default MyCard;