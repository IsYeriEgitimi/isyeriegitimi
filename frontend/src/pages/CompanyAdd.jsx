import React from 'react';
import { useState, useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Button, Form, Container, Grid, Message, Divider } from 'semantic-ui-react';
import { useApiProgress } from './../shared/ApiProgress';
import { useNavigate } from 'react-router-dom';
import { companyAddRequest } from '../api/ApiCalls';

const CompanyAdd = () => {

    const pendingApiCall = useApiProgress("post", "/api/1.0/company/add");
    const [address, setAddress] = useState();
    const [company, setCompany] = useState();
    const [protocol, setProtocol] = useState();
    const [error, setError] = useState();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    useEffect(() => { setError(undefined) }, [address, company, protocol]);

    const onChangeProtocol = (event) => {
        const file = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
           console.log(fileReader.result);
        }
        fileReader.readAsDataURL(file);

    }

    const onChangeImage = (event) => {
        const file = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.onloadend = () => {
            setCompany( { ...company, image: fileReader.result.split(',')[1]  } );
        }
        fileReader.readAsDataURL(file);

    }

    const onClickLogin = async (event) => {
        event.preventDefault();
        const companyState = {
            ...company,
            address,
            protocol
        }
        try {
            await companyAddRequest(companyState);
            console.log(companyState);
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
        <Container>
            <Grid>
                <Grid.Row centered>
                    <Grid.Column width={6}>
                        <Form>
                            <Divider horizontal> ADRES BİLGİLERİ </Divider>
                            <Form.Field>
                                <label>İl</label>
                                <input name='province' placeholder='İl' onChange={(event) => { setAddress({ ...address, province: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>ilçe</label>
                                <input name='district' placeholder='İlçe' onChange={(event) => { setAddress({ ...address, district: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Mahalle</label>
                                <input name='neighborhood' placeholder='Mahalle' onChange={(event) => { setAddress({ ...address, neighborhood: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Cadde</label>
                                <input name='road' placeholder='Cadde' onChange={(event) => { setAddress({ ...address, road: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>No</label>
                                <input name='no' placeholder='No' onChange={(event) => { setAddress({ ...address, no: event.target.value }) }} />
                            </Form.Field>

                            <Divider horizontal> ŞİRKET BİLGİLERİ </Divider>

                            <Form.Field>
                                <label>Email</label>
                                <input name='companyEmail' placeholder='Email' onChange={(event) => { setCompany({ ...company, companyEmail: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Adı</label>
                                <input name='companyName' placeholder='Adı' onChange={(event) => { setCompany({ ...company, companyName: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Telefon</label>
                                <input name='companyPhone' placeholder='Telefon' onChange={(event) => { setCompany({ ...company, companyPhone: event.target.value }) }} />
                            </Form.Field>
                            <Form.Field>
                                <label>Logo / Fotoğraf</label>
                                <input name='image' type='file' onChange={onChangeImage} />
                            </Form.Field>

                            <Divider horizontal> PROTOKOL BİLGİSİ </Divider>

                            <Form.Field>
                                <label>Protokol</label>
                                <input name='protocol' type='file' onChange={onChangeProtocol} />
                            </Form.Field>

                            <Form.Field>
                                <Form.Field>
                                    {error && <Message size='tiny' color='yellow'>{error}</Message>}
                                </Form.Field>
                            </Form.Field>
                            <div>
                                <Button loading={pendingApiCall} inverted color='violet' type='submit' onClick={onClickLogin}>Kaydet</Button>
                            </div>
                        </Form>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
        </Container>
    );
};

export default CompanyAdd;