import React from 'react'

function ShoppingCartItem(props) {
  console.log(props)
  return (
    <div className="shopping-cart__product">
      <span className="shopping-cart__product-title">{props.data.name}</span>
      <span>
        <span className="shopping-cart__product-quantity">
          <button>-</button>
          <input type="number" min="1"></input>
          <button>+</button>
        </span>
        <span className="shopping-cart__product-price"></span>
      </span>
    </div>
  )
}

export default ShoppingCartItem
