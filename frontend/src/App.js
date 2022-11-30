import './App.css';
import Navi from './layouts/Navi';
import { Container } from 'semantic-ui-react';
import Body from './layouts/Body';

function App() {
  return (
    <div>
      <Container>
        <Navi />
        <Body />
      </Container>

    </div>
  );
}

export default App;
