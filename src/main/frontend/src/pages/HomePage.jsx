import React, { useState } from 'react'
import Product from '../components/Product/Product'
import SidePanel from '../components/SidePanel/SidePanel'
import ShoppingCart from '../components/ShoppingCart/ShoppingCart'
import Dropdown from '../components/Dropdown/Dropdown'
import './HomePage.css'

function HomePage() {
  const fakesonData = [
    { name: 'basketball', price: '$500', url: '' },
    { name: 'fakeball', price: '$500', url: '' },
    { name: 'fakeball', price: '$500', url: '' },
  ]

  // HOOKS
  // Read about useState - which keeps "state" for your component

  // Read about useEffect for fetching API data on component mount - put onto the page
  // useEffect(() => {
  //     fetch
  // }, [])

  const [show, showCart] = useState(false)

  // const fetchApi = async () => {
  //   return fetch('/api.noIDEaSHop', {
  //     headers: {
  //       'Content-Type': 'application/json',
  //     },
  //   })
  //     .then((res) => res.json())
  //     .then((data) => console.log(data))
  // }

  return (
    <div className="product-page">
      <SidePanel />
      <div>
        <div className="sort-cart-container">
          <Dropdown />
          <div>
            <button
              className="shopping-cart__button"
              onClick={() => showCart(show ? false : true)}
            >
              Shopping Cart
            </button>
            {show && <ShoppingCart data={fakesonData} />}
          </div>
        </div>
        {/* You can write JS code in the return statement by wrapping it in {} */}
        <div className="product-card-container">
          {fakesonData.map((item) => (
            <Product
              key={item.name}
              name={item.name}
              price={item.price}
              imageURL={item.url}
            />
          ))}
        </div>
      </div>
    </div>
  )
}

export default HomePage
