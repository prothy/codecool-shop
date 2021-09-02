import React, { useEffect, useState } from 'react'
import { Button } from '@material-ui/core'
import {
  addProductToCart,
  getAllProducts,
  removeProductFromCart,
} from '../../services/webshopAPI.js'

function ShoppingCartItem({ data }) {
  const [quantity, setQuantity] = useState(data.quantity)
  const [product, setProduct] = useState({})

  function validateInput(ev) {
    const value = parseInt(ev.target.value)
    if (!Number.isInteger(value)) {
      ev.target.value = ''
    } else {
      setQuantity(value)
    }
  }

  useEffect(() => {
    getAllProducts().then((products) =>
      setProduct(products.filter((prod) => prod.name === data.productName)[0])
    )
  })

  function addProduct() {
    addProductToCart(product)
  }

  function removeProduct() {
    removeProductFromCart(product)
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
        <span className="shopping-cart__product-price">{data.sumPrice}</span>
      </span>
    </div>
  )
}

export default ShoppingCartItem
