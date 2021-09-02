import React, { useState } from 'react'
import Products from './components/Products/Products'
import Dashboard from './components/Dashboard/Dashboard'

const App = () => {
  const [cart, setCart] = useState([])

  return (
    <>
      <Dashboard cart={cart} setCart={setCart}>
        <Products cart={cart} setCart={setCart} />
      </Dashboard>
    </>
  )
}

export default App
