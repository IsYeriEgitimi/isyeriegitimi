import React, { useCallback, useEffect, useState } from 'react';
import { View, RefreshControl, StyleSheet, ScrollView } from 'react-native';
import { ActivityIndicator, MD2Colors, SegmentedButtons, Text } from 'react-native-paper';
import StudentCard from '../components/StudentCard';
import { getAllStudent, getStudentByDate } from '../api/ApiCalls';
import StudentYearFilterButton from '../components/StudentYearFilterButton';
import { useApiProgress } from '../components/ApiProgress';

const Student = () => {
    const pendingApiCall = useApiProgress("get", "http://192.168.1.100:8080/api/1.0/student/getAll");
    const [value, setValue] = useState();
    const [refreshing, setRefreshing] = useState(false);
    const onRefresh = useCallback(() => {
        setRefreshing(true);
    }, []);

    const [students, setStudents] = useState(
        {
            content: [],
        }
    );
    useEffect(() => {
        const loadStudents = async (page) => {
            try {
                let response;
                if (value) {
                    response = await getStudentByDate(parseInt(value), parseInt(value)+1);
                } else {
                    response = await getAllStudent();
                }
                setStudents({ content: response.data.data.content });
                setRefreshing(false);
            } catch (error) {
                console.log(error);
                setRefreshing(false);
            }
        }
        loadStudents();
    }, [refreshing]);

    return (
        <>
            <View style={{ backgroundColor: '#FFF' }}>
                <View style={styles.body}>
                    <Text style={styles.headerText}>İşyeri Eğitimlerini Tamamlamış Öğrenciler</Text>


                    <SegmentedButtons
                        style={styles.buttons}
                        value={value}
                        onValueChange={setValue}
                        buttons={[
                            {
                                value: '2020',
                                label: '2020',
                                onPress: () => {onRefresh()} 
                            },
                            {
                                value: '2021',
                                label: '2021',
                                onPress: () => {onRefresh()} 
                            },
                            {
                                value: '2022',
                                label: '2022',
                                onPress: () => {onRefresh()} 
                            },
                        ]}
                    />




                </View>
                <ScrollView style={{  height: 350}}
                    refreshControl={
                        <RefreshControl
                            refreshing={refreshing}
                            onRefresh={onRefresh}
                        />
                    }
                >
                    {pendingApiCall && <ActivityIndicator animating={true} color={MD2Colors.deepPurple400} />}
                    {
                        students.content.map((student) => {
                            return (
                                <StudentCard key={student.id} student={student} />
                            )
                        })
                    }
                    { students.content.length === 0 && <Text style={styles.text}>Öğrenci bulunmamaktadır.</Text>}

                </ScrollView>

            </View>

        </>

    );
};

const styles = StyleSheet.create({
    body: {
        marginBottom: 30,
        alignItems: 'center'
    },
    headerText: {
        color: '#8A8A8A',
        fontSize: 18
    },
    buttons: {
        marginHorizontal: 30,
        marginTop: 30,
    },
    text: {
        textAlign: 'center'
    }

});


export default Student;