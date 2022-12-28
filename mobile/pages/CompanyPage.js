import React, { useCallback, useEffect, useState } from 'react';
import { ScrollView, StyleSheet, View, RefreshControl } from 'react-native';
import { Card, Title, Paragraph, Button, Text, Avatar, Divider, TextInput, ActivityIndicator, MD2Colors } from 'react-native-paper';
import { useRoute } from '@react-navigation/native';
import { Pdf, Call, Email } from 'react-native-openanything';
import { useSelector } from 'react-redux';
import { getCommentsCompany, postComment } from '../api/ApiCalls';
import StudentImage from '../assets/student.png';
import { useApiProgress } from '../components/ApiProgress';

const Company = () => {
    const pendingApiCallAdd = useApiProgress("post", "http://192.168.1.100:8080/api/1.0/comment/add");
    const pendingApiCallGet = useApiProgress("get", "http://192.168.1.100:8080/api/1.0/comment/company/");
    const route = useRoute();
    const [newComment, setNewComment] = useState();
    const { company } = route.params;

    const { isLoggedIn, id, role } = useSelector((store) => {
        return { isLoggedIn: store._z.isLoggedIn, role: store._z.role, id: store._z.id };
    });

    let source = undefined;
    try {
        source = `http://192.168.1.100:8080/protocols/${company.protocol.protocolName}.pdf`;
    } catch (error) { }

    const [comments, setComments] = useState(
        {
            content: [],
        }
    );

    const [refreshing, setRefreshing] = useState(false);
    const onRefresh = useCallback(() => {
        setRefreshing(true);
    }, []);

    const onClickCommentButton = async () => {
        const body = {
            comment: newComment,
            company,
            user: {
                id
            }
        }
        try {
            await postComment(body);
            onRefresh();

        } catch (err) {
            console.log(err);
        }

    }

    useEffect(() => {
        const loadComments = async (page) => {
            try {
                const response = await getCommentsCompany(company.id, page);
                setComments({ content: response.data.data.content });
                setNewComment();
                setRefreshing(false);
            } catch (error) {
                console.log(error);
            }
        }
        loadComments();
    }, [refreshing]);
    const LeftContent = () => (
        <Avatar.Image
            source={StudentImage}
            style={{ backgroundColor: '#F7F3F9' }}
            size={45} />);

    return (
        <ScrollView
            refreshControl={
                <RefreshControl
                    refreshing={refreshing}
                    onRefresh={onRefresh}
                />
            }>

            <Card style={styles.card} elevation={0}>
                <Card.Cover source={{ uri: `http://192.168.1.100:8080/images/${company.image}` }}
                    resizeMode='center' style={styles.cardCover} />
                <Card.Content>
                    <Title style={styles.cardTitle}> {company.companyName}</Title>
                    <Paragraph style={styles.cardParagraph}> {company.companyEmail} </Paragraph>
                    <Paragraph style={styles.cardParagraph}> {company.companyPhone} </Paragraph>
                    <Paragraph style={styles.cardParagraph}>
                        {
                            company.address.neighborhood + " " + company.address.road + " " + company.address.no
                            + " " + company.address.province + " / " + company.address.district
                        }
                    </Paragraph>
                </Card.Content>

                <Card.Actions style={styles.cardActions}>
                    <Button onPress={() => {
                        Call(company.companyPhone)
                    }}>Ara</Button>

                    <Button onPress={() => {
                        Email(company.companyEmail)
                    }}>Mail yolla</Button>

                    {source !== undefined &&

                        <Button
                            onPress={() => {
                                Pdf(source)
                            }}>
                            Protokolü görüntüle
                        </Button>

                    }

                </Card.Actions>
                <Divider />

                {isLoggedIn && role.level == 1 &&
                    <>
                        <TextInput
                            style={styles.commentText}
                            multiline
                            mode="outlined"
                            label="Yorumun"
                            onChangeText={(text) => { setNewComment(text); }}
                        />
                        {
                            newComment &&
                            <Button
                                loading={pendingApiCallAdd}
                                style={styles.button}
                                onPress={onClickCommentButton}>
                                Yorum yap
                            </Button>
                        }

                    </>
                }
                {pendingApiCallGet && <ActivityIndicator animating={true} color={MD2Colors.deepPurple400} /> }
                
                {

                    comments.content.map((comment) => {
                        return (
                            <Card key={comment.id} style={styles.commentCard}>
                                <Card.Title
                                    title={comment.user.displayName}
                                    subtitle={comment.comment}
                                    left={LeftContent}
                                />
                            </Card>
                        )
                    })

                }


            </Card>

        </ScrollView>
    );
};
const styles = StyleSheet.create({
    card: {
        borderRadius: 0,
        backgroundColor: '#FFF'
    },
    cardTitle: {
        marginTop: 10
    },
    cardParagraph: {
        marginTop: 10,
        color: "#6F6F6F"
    },
    cardCover: {
        height: 100,
        backgroundColor: '#FFF'
    },
    cardActions: {
        marginTop: 20
    },
    commentCard: {
        marginHorizontal: 20,
        marginVertical: 10,
        backgroundColor: '#FCFBFD'
    },
    commentText: {
        margin: 20
    },
    button: {
        marginLeft: 'auto',
        marginRight: 20,
        justifyContent: 'flex-end',
        borderWidth: 1,
        borderColor: '#6E58A7',
        width: 100
    }

});
export default Company;



