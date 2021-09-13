import React from 'react'
import {Card} from 'react-bootstrap'
import {Link} from 'react-router-dom'

function Product({product}) {
    return (
        <Card className="my-3 p-3 rounded">
            {/* Product Image */}
            <Link to={`/products/${product.id}`}>
                <Card.Img src={product.image}/>
            </Link>

            <Card.Body>
                {/* Product Title */}
                <Link to={`/products/${product.id}`}>
                    <Card.Title as="div">
                        <strong>{product.name}</strong>
                    </Card.Title>
                </Link>

                {/* Product price */}
                <Card.Text as="h3">
                    {product.price}$
                </Card.Text>
            </Card.Body>
        </Card>
    )
}

export default Product