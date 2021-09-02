import React from 'react'
import {Grid} from '@material-ui/core'
import Product from '../Product/Product'

const Products = ({products}) => {

    return (
        <Grid container justifyContent="center" spacing={4}>
            {products.map((product) => (
                <Grid item key={product.id} xs={12} sm={6} md={4} lg={3}>
                    <Product product={product}/>
                </Grid>
            ))}
        </Grid>)
}

export default Products