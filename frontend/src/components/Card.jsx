import React, { useEffect, useState } from 'react'
import { Card, Icon, Image } from 'semantic-ui-react'
import { getAllCompany } from './../api/ApiCalls';
import '../css/Card.css';

const MyCard = () => {

  const [companies, setCompanies] = useState(
    {
      content: [],
    }
  );

  useEffect(() => {
    const loadCompanies = async (page) => {
      try {
        const response = await getAllCompany(page);
        setCompanies({ content: response.data.data.content });
        console.log(response);
      } catch (error) { }
    }
    loadCompanies();
  }, []);

  return (
    <div>
      <>
        <Card.Group>

          {companies.content.map((company) => {
            return (
              <Card key={company.id}>
                <Image src={`images/${company.image}`} wrapped className='company-image' />
                <Card.Content>
                  <Card.Header>{company.companyName}</Card.Header>
                  <Card.Meta>{company.companyEmail}</Card.Meta>
                  <Card.Description>
                    {
                      company.address.neighborhood + " " + company.address.road + " " + company.address.no
                      + " " + company.address.province + " / " + company.address.district
                    }
                  </Card.Description>
                </Card.Content>
                <Card.Content extra>
                  <a href='tel:'>
                    <Icon name='phone' />
                    {company.companyPhone}
                  </a>
                </Card.Content>
                {
                  company.protocol &&
                  <Card.Content extra>
                    <a href={`protocols/${company.protocol.protocolName}.pdf`}>
                      <Icon name='book' />
                      {"Protokol: "+company.companyName}
                    </a>
                   
                  </Card.Content>
                }

              </Card>



            );
          })}



        </Card.Group>
      </>
    </div>
  );
};

export default MyCard;



