import React, { useState } from 'react'
import { Button } from '@material-ui/core'
import {
  addProductToCart,
  removeProductFromCart,
} from '../../services/webshopAPI.js'

function ShoppingCartItem({ data }) {
  const [quantity, setQuantity] = useState(data.quantity)

  function validateInput(ev) {
    const value = parseInt(ev.target.value)
    if (!Number.isInteger(value)) {
      ev.target.value = ''
    } else {
      setQuantity(value)
    }
  }

  function addProduct() {
    addProductToCart(data)
  }

  function removeProduct() {
    removeProductFromCart(data)
  }

  return (
    <div className="shopping-cart__product">
      <span className="shopping-cart__product-title">{data.productName}</span>
      <span>
        <span className="shopping-cart__product-quantity">
          <Button variant="contained" onClick={removeProduct}>
            -
          </Button>
          <input onKeyPress={(ev) => validateInput(ev)} value={quantity} />
          <Button variant="contained" onClick={addProduct}>
            +
          </Button>
        </span>
        <span className="shopping-cart__product-price">{data.price}</span>
      </span>
    </div>
  )
}

export default ShoppingCartItem
