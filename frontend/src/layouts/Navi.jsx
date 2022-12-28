import React, { useState } from 'react'
import { Menu, Segment, Button } from 'semantic-ui-react'
import MyLogo from '../images/logo.png';
import Avatar from '../components/Avatar';
import { useSelector } from 'react-redux';
import { useNavigate } from 'react-router-dom';

const Navi = () => {

  const { isLoggedIn, displayName } = useSelector((store) => {
    return { isLoggedIn: store.isLoggedIn, displayName: store.displayName };
  });

  const [activeItem, setActiveItem] = useState();
  const navigate = useNavigate();  
  const handleItemClick = (name) => setActiveItem(name);

  


  return (
    <Segment style={{ marginTop: 15, borderRadius: 15 }}>
      <Menu secondary >
        <Menu.Item>
          <img src={MyLogo} alt="logo" />
        </Menu.Item>

	
        <Menu.Item
          name='Şirketler'
          active={activeItem === 'Şirketler'}
          onClick={() => {
            handleItemClick('Şirketler');
            navigate('/');
          }}
        />
	
	{isLoggedIn &&
        <Menu.Item
          name='Şirket ekle'
          active={activeItem === 'Şirket ekle'}
          onClick={() => {
            handleItemClick('Şirket ekle');
            navigate("/company-add")
          }}
        />
	}
       
        {isLoggedIn &&
          <Menu.Menu position='right'>
            <Menu.Item>
              <Avatar displayName={displayName} />
            </Menu.Item>
          </Menu.Menu>
        }
	{!isLoggedIn &&
	<Button />
	}
      </Menu>



    </Segment>
  )
}



export default Navi;