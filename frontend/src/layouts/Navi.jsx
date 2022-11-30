import React, { useState } from 'react'
import { Menu, Segment } from 'semantic-ui-react'
import MyLogo from '../images/logo.png';
import '../css/Navi.css';
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
          name='home'
          active={activeItem === 'home'}
          onClick={() => {
            handleItemClick('home');
            navigate('/');
          }}

        />
        <Menu.Item
          name='messages'
          active={activeItem === 'messages'}
          onClick={() => handleItemClick('messages')}
        />
        <Menu.Item
          name='friends'
          active={activeItem === 'friends'}
          onClick={() => handleItemClick('friends')}
        />
        {isLoggedIn &&
          <Menu.Menu position='right'>
            <Menu.Item>
              <Avatar displayName={displayName} />
            </Menu.Item>
          </Menu.Menu>
        }
      </Menu>



    </Segment>
  )
}



export default Navi;