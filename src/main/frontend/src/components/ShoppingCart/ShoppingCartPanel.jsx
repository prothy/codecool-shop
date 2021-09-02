import React from 'react'
import ShoppingCartItem from '../ShoppingCartItem/ShoppingCartItem'
import './ShoppingCartPanel.css'

function ShoppingCartPanel(props) {
  return (
    <div className="shopping-cart">
      <div>
        {props.data.map((el, i) => {
          console.log(el)
          return <ShoppingCartItem key={i} data={el} />
        })}
      </div>
      <button>Checkout</button>
    </div>
  )
}

export default ShoppingCartPanel
