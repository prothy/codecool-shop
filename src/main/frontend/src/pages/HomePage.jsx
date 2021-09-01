import React from 'react'
import {Link} from 'react-router-dom'
import Product from '../components/Product/Product'
import SidePanel from '../components/Prototype/Prototype'
import ShoppingCart from '../components/ShoppingCart/ShoppingCart'
import Dropdown from '../components/Dropdown/Dropdown'

function HomePage() {
	const fakesonData = [
		{name: 'basketball', price: '$500', url: ''},
		{name: 'fakeball', price: '$500', url: ''},
	]

	// HOOKS
	// Read about useState - which keeps "state" for your component

	// Read about useEffect for fetching API data on component mount - put onto the page
	// useEffect(() => {
	//     fetch
	// }, [])
	return (
		<div>
			<SidePanel/>
			<div>
				<div>
					<Dropdown/>
					<ShoppingCart/>
				</div>
				{/* You can write JS code in the return statement by wrapping it in {} */}
				{fakesonData.map((item) => (
					<Product
						key={item.name}
						name={item.name}
						price={item.price}
						imageURL={item.url}
					/>
				))}
				<Link to="/">
					<button>Home</button>
				</Link>
			</div>
		</div>
	)
}

export default HomePage
