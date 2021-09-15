import React, {useEffect} from 'react'
import {Row, Col, Dropdown, ListGroup, Container} from 'react-bootstrap'
import Product from '../components/Product'
import Loader from '../components/Loader'
import Message from '../components/Message'
import {useDispatch, useSelector} from 'react-redux'
import {listProducts} from '../actions/productActions'
import {setFilters} from '../actions/productActions'

// TODO: FIX -> Fetch it from the backend:
const categories = ['OS', 'WorkTool', 'IDE', 'Cloud']
const suppliers = ['Microsoft', 'Google', 'JetBrains', 'Amazon', 'Slack', 'Atlassian']

// TODO: REFACTOR -> Place it into a util/helper folder and import it:
const filterProducts = ({category, supplier}, products) => {
    if (category) {
        products = products.filter(product => product.productCategory.name === category)
    }

    if (supplier) {
        products = products.filter(product => product.supplier.name === supplier)
    }
    return products
}

function HomePage() {
    const dispatch = useDispatch()
    const productList = useSelector(state => state.productList)
    const filters = useSelector(state => state.productFilters)
    const {error, loading, products} = productList

    useEffect(() => {
        dispatch(listProducts())
    }, [dispatch])

    const handleSetFilter = ({category, supplier}) => {
        dispatch(setFilters({category, supplier}))
    }

    const filteredProducts = filterProducts(filters, products)


    return (
        <div>
            <h1>Choose your product</h1>
            {loading ? <Loader/>
                : error ? <Message variant="danger">{error}</Message>
                    :
                    <>
                        <Container>
                            <Col>
                                {/* TODO: FIX -> Button alignment */}
                                <Dropdown className="py-2">
                                    <Dropdown.Toggle variant="outline-secondary" id="dropdown-basic">
                                        {filters.category.length > 0 ? filters.category : 'Category'}
                                    </Dropdown.Toggle>

                                    <Dropdown.Menu>
                                        <Dropdown.Item
                                            onClick={() => handleSetFilter({category: ''})}>---</Dropdown.Item>
                                        <Dropdown.Divider/>
                                        {
                                            categories.map(category => (
                                                <Dropdown.Item
                                                    onClick={() => handleSetFilter({category})}>{category}</Dropdown.Item>
                                            ))
                                        }
                                    </Dropdown.Menu>
                                </Dropdown>
                            </Col>
                            <Col>
                                <Dropdown className="py2">
                                    <Dropdown.Toggle variant="outline-secondary" id="dropdown-basic">
                                        {filters.supplier.length > 0 ? filters.supplier : 'Supplier'}
                                    </Dropdown.Toggle>
                                    <Dropdown.Menu>
                                        <Dropdown.Item
                                            onClick={() => handleSetFilter({supplier: ''})}>---</Dropdown.Item>
                                        <Dropdown.Divider/>
                                        {
                                            suppliers.map(supplier => (
                                                <Dropdown.Item
                                                    onClick={() => handleSetFilter({supplier})}>{supplier}</Dropdown.Item>
                                            ))
                                        }
                                    </Dropdown.Menu>
                                </Dropdown>
                            </Col>
                        </Container>
                        <Row>
                            {filteredProducts.map(product => (
                                <Col key={product.id} sm={12} md={6} lg={4} xl={3}>
                                    <Product product={product}/>
                                </Col>
                            ))}
                        </Row>
                    </>
            }
        </div>
    )
}

export default HomePage