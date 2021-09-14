import React from 'react'
import {Card} from 'react-bootstrap'
import {Link} from 'react-router-dom'

function Product({product}) {

    const style = {
        width: '100%',
        height: '15vw',
        objectFit: 'contain'
    }

    return (
        <Card className="my-3 p-3 rounded">
            {/* Product Image */}
            <Link to={`/products/${product.id}`} style={{textDecoration: 'none'}}>
                <Card.Img src={product.image} style={style}/>
            </Link>

            <Card.Body>
                {/* Product Title */}
                <Link to={`/products/${product.id}`} style={{textDecoration: 'none'}}>
                    <Card.Title as="div">
                        <strong>{product.name}</strong>
                    </Card.Title>
                </Link>

                {/* Product price */}
                <Card.Text as="h3">
                    {product.price || product.yearlyPrice}$
                </Card.Text>
            </Card.Body>
        </Card>
    )
}

export default Product