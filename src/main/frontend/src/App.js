import React, { useEffect, useState } from 'react'
import Products from './components/Products/Products'
import Dashboard from './components/Dashboard/Dashboard'
import {getAllProducts, getProductByCategory, getSuppliers} from './services/webshopAPI'
import {Switch, Route} from 'react-router-dom'
import ProductPage from './components/ProductPage/ProductPage'
import { fetchCart } from './services/webshopAPI'

const App = () => {

    const [cart, setCart] = useState([])
    const [products, setProducts] = useState([])
    const [category, setCategory] = useState('all')
    const [supplier, setSupplier] = useState('')

    const handleSetProducts = (category) => {
        setCategory(category)
    }

    const handleSupplier = (supplier) => {
        setSupplier(supplier)
    }

    useEffect(() => {
        if (category === 'all') {
            getAllProducts().then(products => {
                setProducts(products)
                setCategory('')
            })
        } else if (category.length > 0) {
            getProductByCategory(category).then(products => {
                setProducts(products)
                setCategory('')
            })
        } else if (supplier.length > 0) {
            getSuppliers(supplier).then(supplier => {
                setProducts(supplier)
                setSupplier('')
            })
        }
        fetchCart().then((c) => setCart(c))
    }, [category, supplier])

    return (
        <>
            <Dashboard products={products} handleSetProducts={handleSetProducts} handleSupplier={handleSupplier} cart={cart} setCart={setCart}>
                <Switch>
                    <Route exact path="/">
                        <Products products={products} cart={cart} setCart={setCart}/>
                    </Route>
                    <Route exact path="/products/:productId">
                        <ProductPage products={products}/>
                    </Route>
                </Switch>
            </Dashboard>
        </>
    )
}

export default App
