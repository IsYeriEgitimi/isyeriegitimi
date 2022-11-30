import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Routes } from 'react-router-dom';
import HomePage from './../pages/HomePage';
import LoginPage from './../pages/LoginPage';
import AdminPanel from './../pages/AdminPanel';

const Body = () => {
  const { isLoggedIn } = useSelector((store) => ({ isLoggedIn: store.isLoggedIn }));
  return (
    <Routes>
      <Route path='/' element={<HomePage />} />
      {!isLoggedIn && <Route path='/admin-login' element={<LoginPage />} />}
      {isLoggedIn  && <Route path='/admin-panel' element={<AdminPanel />} />}
    </Routes>
  );
};

export default Body;