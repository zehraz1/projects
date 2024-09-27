import React, { useState, useEffect } from 'react';
import Header from './Header';
import ProductList from './ProductList';
import Cart from './Cart';
import Footer from './Footer';

const Productpage = () => {
  const [cartItems, setCartItems] = useState([]);
  useEffect(() => {
    const savedCartItems = JSON.parse(localStorage.getItem('cartItems')) || [];
    setCartItems(savedCartItems);
  }, []);
  useEffect(() => {
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
  }, [cartItems]);
  const addToCart = (product) => {
    const existingItem = cartItems.find((item) => item.id === product.id);

    if (existingItem) {
      setCartItems(
        cartItems.map((item) =>
          item.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
        )
      );
    } else {
      setCartItems([...cartItems, { ...product, quantity: 1 }]);
    }
  };

  const removeFromCart = (id) => {
    setCartItems(cartItems.filter((item) => item.id !== id));
  };

  return (
    <div className="product-page">
      <Header />
      <table>
        <tbody>
          <tr>
            <td><ProductList addToCart={addToCart} /></td>
            <td style={{ verticalAlign: 'top' }}><Cart cartItems={cartItems} removeFromCart={removeFromCart} /></td>
          </tr>
        </tbody>
      </table>
      <Footer />
    </div>
  );
};

export default Productpage;
