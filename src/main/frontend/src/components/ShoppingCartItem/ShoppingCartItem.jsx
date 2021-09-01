import React, { useState } from 'react'

function ShoppingCartItem(props) {
  const [quantity, setQuantity] = useState(1)

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
      <span className="shopping-cart__product-title">{props.data.name}</span>
      <span>
        <span className="shopping-cart__product-quantity">
          <button onClick={() => setQuantity(quantity - 1)}>-</button>
          <input onKeyPress={(ev) => validateInput(ev)} />
          <button onClick={() => setQuantity(quantity + 1)}>+</button>
        </span>
        <span className="shopping-cart__product-price">${quantity * 500}</span>
      </span>
    </div>
  )
}

export default ShoppingCartItem
