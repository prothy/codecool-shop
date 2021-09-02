import React, { useEffect, useState } from 'react'
import { Grid } from '@material-ui/core'
import Product from '../Product/Product'
import { getAllProducts } from '../../services/webshopAPI'

const Products = (props) => {
  const [products, setProducts] = useState([])

  useEffect(() => {
    getAllProducts().then((products) => setProducts(products))
  }, [])

  return (
    <Grid container justifyContent="center" spacing={4}>
      {products.map((product) => (
        <Grid item key={product.id} xs={12} sm={6} md={4} lg={3}>
          <Product
            product={product}
            cart={props.cart}
            setCart={props.setCart}
          />
        </Grid>
      ))}
    </Grid>
  )
}

export default Products
