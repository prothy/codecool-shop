import React, { useState } from 'react'
import { Button } from '@material-ui/core'

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

  return (
    <div className="shopping-cart__product">
      <span className="shopping-cart__product-title">{data.productName}</span>
      <span>
        <span className="shopping-cart__product-quantity">
          <Button variant="contained" onClick={() => setQuantity(quantity - 1)}>
            -
          </Button>
          <input onKeyPress={(ev) => validateInput(ev)} value={quantity} />
          <Button variant="contained" onClick={() => setQuantity(quantity + 1)}>
            +
          </Button>
        </span>
        <span className="shopping-cart__product-price">{data.price}</span>
      </span>
    </div>
  )
}

export default ShoppingCartItem
