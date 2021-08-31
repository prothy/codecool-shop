import React from 'react'
import {Switch, Route} from 'react-router-dom'
import './App.css'
import Header from './components/Header/Header'
import HomePage from './pages/HomePage'

function App() {
	return (
		//Only one "parent" element can be rendered - you can use "fragments" like below
		<>
			<Header/>
			<Switch>
				<Route exact path="/home">
					<HomePage/>
				</Route>
				<Route path="/">
					<HomePage/>
				</Route>
			</Switch>
		</>
	)
}

export default App
