import React from 'react';
import { Image } from 'semantic-ui-react'
import avatarImage from '../assets/person.png';

const Avatar = (props) => {
    const { displayName } = props;
    return (
        <div>
            <span style={{ paddingRight: 15, }}>{displayName}</span>
            <Image src={avatarImage} avatar size='mini' />
        </div>
    );
};

export default Avatar;