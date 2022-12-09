import React from 'react';
import { Image } from 'semantic-ui-react'
import avatarImage from '../assets/person.png';
import { useNavigate } from 'react-router-dom';

const Avatar = (props) => {
    const { displayName } = props;
    const navigate = useNavigate();  
    return (
        <div onClick={() => navigate("/admin-panel") } style= {{cursor:'pointer'}}>
            <span style={{ paddingRight: 15, }}>{displayName}</span>
            <Image src={avatarImage} avatar size='mini' />
        </div>
    );
};

export default Avatar;