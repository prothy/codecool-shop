import React from 'react'
import { Switch, Route } from 'react-router-dom'
import './App.css'
import NavBar from './components/NavBar/NavBar'
import HomePage from './pages/HomePage'

function App() {
  const fallbackMessage = 'hello BELLO fallback'
  return (
    //Only one "parent" element can be rendered - you can use "fragments" like below
    <>
      <NavBar />
      <Switch>
        <Route exact path="/home">
          <HomePage />
        </Route>
        <Route path="/">
          <div>{fallbackMessage}</div>
        </Route>
      </Switch>
    </>
  )
}

export default App
