import React from 'react';
import { useSelector } from 'react-redux';
import { Route, Routes } from 'react-router-dom';
import HomePage from './../pages/HomePage';
import LoginPage from './../pages/LoginPage';
import AdminPanel from './../pages/AdminPanel';
import CompanyAdd from '../pages/CompanyAdd';

const Body = () => {
  const { isLoggedIn } = useSelector((store) => ({ isLoggedIn: store.isLoggedIn }));
  return (
    <Routes>
      <Route path='/' element={<HomePage />} />
      {!isLoggedIn && <Route path='/admin-login' element={<LoginPage />} />}
      {isLoggedIn  && <Route path='/admin-panel' element={<AdminPanel />} />}
      {isLoggedIn  && <Route path='/company-add' element={<CompanyAdd />} />}
    </Routes>
  );
};

export default Body;