import React, { useEffect, useState } from 'react'
import Products from './components/Products/Products'
import Dashboard from './components/Dashboard/Dashboard'
import { fetchCart } from './services/webshopAPI'

const App = () => {
  const [cart, setCart] = useState([])

  useEffect(() => {
    fetchCart().then((c) => setCart(c))
  }, [])

  return (
    <>
      <Dashboard cart={cart} setCart={setCart}>
        <Products cart={cart} setCart={setCart} />
      </Dashboard>
    </>
  )
}

export default App
