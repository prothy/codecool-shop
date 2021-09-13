import React, {useState, useEffect} from 'react'
import {Link} from 'react-router-dom'
import {Row, Col, Image, ListGroup, Button, Card} from 'react-bootstrap'
import axios from 'axios'

function ProductPage({match}) {

    const [product, setProduct] = useState([])

    useEffect(() => {
        async function fetchProduct() {
            const {data} = await axios.get(`/api/products/${match.params.id}`)
            setProduct(data)
        }

        fetchProduct()
    }, [match.params.id])


    return (
        <div>
            <Link to="/" className="btn btn-dark my-3">Back</Link>
            <Row>
                {/* Image */}
                <Col md={6}>
                    <Image src={product.image} alt={product.name} fluid/>
                </Col>

                <Col md={3}>
                    <ListGroup variant="flush">
                        {/* Name */}
                        <ListGroup.Item>
                            <h3>{product.name}</h3>
                        </ListGroup.Item>

                        {/* Description */}
                        <ListGroup.Item>
                            <span>Description: {product.description}</span>
                        </ListGroup.Item>
                    </ListGroup>
                </Col>

                {/* Price */}
                <Col md={3}>
                    <Card>
                        <ListGroup variant="flush">
                            <ListGroup.Item>
                                <Row>
                                    <Col>Price: </Col>
                                    <Col>
                                        <strong>{product.price} $</strong>
                                    </Col>
                                </Row>
                            </ListGroup.Item>

                            <ListGroup.Item className="d-grid">
                                <Button className="btn-dark" type="button">Add to cart</Button>
                            </ListGroup.Item>
                        </ListGroup>
                    </Card>
                </Col>
            </Row>
        </div>
    )
}

export default ProductPage