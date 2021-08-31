import React from 'react'
import './Product.css'

// Every component takes one argument called "props", which is a JS object {}
// props = { name: "blah", price: "$5 }
function Product({ imageURL, name, price }) {
  return (
    <div className="product-card">
      <img src={imageURL} alt={name} />
      <div className="product-card-info">
        <span>{name}</span>
        <span>{price}</span>
      </div>
    </div>
  )
}

export default Product
