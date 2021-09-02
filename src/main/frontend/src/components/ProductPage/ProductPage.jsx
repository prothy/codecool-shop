import React from 'react'
import {useParams} from 'react-router-dom'
import Product from '../Product/Product'
// import {ListItem} from '@material-ui/core'


function ProductPage({products}) {
    const {productId} = useParams()
    const selectedProduct = products.filter(product => product.id === Number(productId))[0]

    return (
        <>
            <div>
                <Product product={selectedProduct}/>
                {/*<ListItem>{selectedProduct.name}</ListItem>*/}
                <p>INSERT DESCRIPTION HERE</p>
            </div>
        </>
    )
}

export default ProductPage