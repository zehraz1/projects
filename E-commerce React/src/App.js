import React from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Productpage from './components/ProductPage'; 
import Homepage from './components/Homepage';
import './App.css';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/products" element={<Productpage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;