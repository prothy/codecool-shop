import React from 'react'
import ShoppingCartItem from '../ShoppingCartItem/ShoppingCartItem'
import './ShoppingCartPanel.css'
import { Button } from '@material-ui/core'

function ShoppingCartPanel({ cart }) {
  const cartSet = new Set(cart)
  const cartQuantity = []

  cartSet.forEach((item) =>
    cartQuantity.push({
      product: item,
      quantity: cart.filter((el) => el.name == item.name),
    })
  )

  return (
    <div className="shopping-cart">
      <div>
        {cart.length == 0 && (
          <span className="shopping-cart__empty-notice">Nothing in here.</span>
        )}
        {cart.map((el, i) => {
          console.log(el)
          return <ShoppingCartItem key={i} data={el} />
        })}
      </div>
      <div>
        <Button variant="contained">Checkout</Button>
      </div>
    </div>
  )
}

export default ShoppingCartPanel
