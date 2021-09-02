import React, { useEffect, useState } from 'react'
import ShoppingCartItem from '../ShoppingCartItem/ShoppingCartItem'
import './ShoppingCartPanel.css'
import { Button } from '@material-ui/core'
import { fetchCart } from '../../services/webshopAPI.js'

function ShoppingCartPanel() {
  const [cart, setCart] = useState([])

  useEffect(() => {
    fetchCart().then((cartItems) => setCart(cartItems))
  }, [])

  return (
    <div className="shopping-cart">
      <div>
        {(!cart.products || cart.products.length === 0) && (
          <span className="shopping-cart__empty-notice">Nothing in here.</span>
        )}
        {cart.products != undefined &&
          cart.products.length > 0 &&
          cart.products.map((el, i) => <ShoppingCartItem key={i} data={el} />)}
      </div>
      <div>
        <Button variant="contained">Checkout</Button>
      </div>
    </div>
  )
}

export default ShoppingCartPanel
