import React from 'react'
import Products from './components/Products/Products'
import Dashboard from './components/Dashboard/Dashboard'

const App = () => {
    return (
        <>
            <Dashboard>
                <Products/>
            </Dashboard>
        </>
    )
}

export default App