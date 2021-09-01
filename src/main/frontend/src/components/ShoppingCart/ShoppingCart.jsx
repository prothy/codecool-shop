import React from 'react'
import './ShoppingCart.css'

function ShoppingCart(jsonData) {
  return (
    <div className="shopping-cart">
      <div>
        {jsonData.data.map((el) => (
          <div className="shopping-cart__product" key="{el.name}">
            <span className="shopping-cart__product-title">{el.name}</span>
            <span>
              <span className="shopping-cart__product-quantity">
                <button>+</button>
                <input type="number" value="1"></input>
                <button>-</button>
              </span>
              <span className="shopping-cart__product-price">{el.price}</span>
            </span>
          </div>
        ))}
      </div>
      <button>Checkout</button>
    </div>
  )
}

export default ShoppingCart
