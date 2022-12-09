import React, { useEffect, useState } from 'react'
import { Card, Icon } from 'semantic-ui-react'
import orion from '../images/orion.png';
import logdef from '../images/logdef.png';
import { getAllCompany } from './../api/ApiCalls';

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
        setCompanies({ content: response.data.data.content});
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
              //'Yenişehir Mah. Osmanlı Bulvarı A Blok No: 11A/30 Pendik İstanbul'
             
              <Card
                image={`images/${company.image}`}
                header={company.companyName}
                meta={company.company}
                description= {
                  company.address.neighborhood + " " +company.address.road+ " " +company.address.no
                  + " " +company.address.province + " / " +company.address.district
                }
                extra={
                  <a href='/'>
                    <Icon name='phone' />
                    {company.companyPhone}
                  </a>
                }

              />
            );
          })}



        </Card.Group>
      </>
    </div>
  );
};

export default MyCard;



