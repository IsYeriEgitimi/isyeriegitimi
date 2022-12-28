import React, { useCallback, useEffect, useState } from 'react';
import { StyleSheet, Text, View, RefreshControl } from 'react-native';
import { ScrollView } from 'react-native-gesture-handler';
import { getAllCompany } from '../api/ApiCalls';
import MyCard from '../components/Card';


const HomePage = (navigation) => {

    const [refreshing, setRefreshing] = useState(false);
    const onRefresh = useCallback(() => {
        setRefreshing(true);
    }, []);

    const [companies, setCompanies] = useState(
        {
            content: [],
        }
    );
    useEffect(() => {
        const loadCompanies = async (page) => {
            try {
                const response = await getAllCompany();
                setCompanies({ content: response.data.data.content });
                setRefreshing(false);
            } catch (error) {
                console.log(error);
            }
        }
        loadCompanies();
    }, [refreshing]);

    return (
        <>
            <View style={{ backgroundColor: '#FFF' }}>
                <View style={styles.body}>
                    <Text style={styles.headerText}>Anlaşmalı Şirketler</Text>
                </View>
                <ScrollView style={{ marginBottom: 54 }}
                    refreshControl={
                        <RefreshControl
                            refreshing={refreshing}
                            onRefresh={onRefresh}
                        />
                    }
                >
                    {
                        companies.content.map((company) => {
                            return (
                                <MyCard key={company.id} company={company} />
                            )
                        })
                    }


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

});

export default HomePage;