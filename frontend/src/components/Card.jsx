import React from 'react'
import { Card, Icon } from 'semantic-ui-react'
import orion from '../images/orion.png';
import logdef from '../images/logdef.png';

const extra = (
  <a href='/'>
    <Icon name='phone' />
    0 (216) 522 21 00
  </a>
)

const CardExampleCardProps = () => (
  <>
    <Card.Group>
      <Card
        image={orion}
        header='Orion Innovation'
        meta='info@orioninc.com'
        description='Yenişehir Mah. Osmanlı Bulvarı
    A Blok No: 11A/30 Pendik
    
    İstanbul'
        extra={extra}
        
      />
      <Card
        image={logdef}
        header='LOG DEF'
        meta='info@logdef.com'
        description='Çaydaçıra Mahallesi, Hacı Ömer Bilginoğlu Caddesi No:61/211 Fırat Teknokent Merkez

    Elazığ'
        extra={extra}
      />
    </Card.Group>
  </>
)

export default CardExampleCardProps
