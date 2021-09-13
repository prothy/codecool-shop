import React, {useState, useEffect} from 'react'
import {useDispatch, useSelector} from 'react-redux'
import {listProductDetails} from '../actions/productActions'
import {Link} from 'react-router-dom'
import Message from '../components/Message'
import Loader from '../components/Loader'
import {Row, Col, Image, ListGroup, Button, Card, Form} from 'react-bootstrap'

function ProductPage({match}) {
    const [qty, setQty] = useState(1)

    const style = {
        width: '100%',
        height: '15vw',
        objectFit: 'contain'
    }

    const dispatch = useDispatch()
    const productDetails = useSelector(state => state.productDetails)
    const {loading, error, product} = productDetails

    useEffect(() => {
        dispatch(listProductDetails(match.params.id))
    }, [dispatch, match])


    return (
        <div>
            <Link to="/" className="btn btn-dark my-3">Back</Link>
            {
                loading ? <Loader/> : error ? <Message variant="danger">{error}</Message> :
                    (
                        <Row>
                            {/* Image */}
                            <Col md={6}>
                                <Image src={product.image} alt={product.name} style={style} fluid/>
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
                                                    <strong>{product.price || product.yearlyPrice} $</strong>
                                                </Col>
                                            </Row>
                                        </ListGroup.Item>

                                        <ListGroup.Item>
                                            <Row>
                                                <Col>Quantity: </Col>
                                                <Col xs="" className="my-1">
                                                    <Form.Control
                                                        type="number"
                                                        min="1"
                                                        value={qty}
                                                        onChange={(e) => setQty(e.target.value)}
                                                        {/*  TODO: Finish add to cart  */}
                                                    >
                                                    </Form.Control>
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
                    )
            }
        </div>
    )
}

export default ProductPage