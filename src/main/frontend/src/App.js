import React, {useEffect, useState} from 'react'
import Products from './components/Products/Products'
import Dashboard from './components/Dashboard/Dashboard'
import {getAllProducts, getProductByCategory} from './services/webshopAPI'

const App = () => {
	const [products, setProducts] = useState([])
	const [category, setCategory] = useState('all')

	const handleSetProducts = (category) => {
		setCategory(category)
	}

	useEffect(() => {
		if (category === 'all') {
			getAllProducts().then(products => setProducts(products))
		} else if (category.length > 0) {
			getProductByCategory(category).then(products => setProducts(products))
		}
	}, [category])


	return (
		<>
			<Dashboard products={products} handleSetProducts={handleSetProducts}>
				<Products products={products}/>
			</Dashboard>
		</>
	)
}

export default App