import {BrowserRouter, Route} from 'react-router-dom'
import Header from './components/Header'
import Footer from './components/Footer'
import HomePage from './pages/HomePage'
import ProductPage from './pages/ProductPage'
import CartPage from './pages/CartPage'
import LoginPage from './pages/LoginPage'
import RegisterPage from './pages/RegisterPage'
import ShippingPage from './pages/ShippingPage'
import PaymentPage from './pages/PaymentPage'
import PlaceOrderPage from './pages/PlaceOrderPage'
import OrderPage from './pages/OrderPage'
import {Container} from 'react-bootstrap'
// Library Core:
import {library} from '@fortawesome/fontawesome-svg-core'
// Brand Icons:
import {fab} from '@fortawesome/free-brands-svg-icons'
// FontAwesome icons used in the project:
import {faShoppingCart, faUser, faTrash} from '@fortawesome/free-solid-svg-icons'
// Collect the icons into the library:
library.add(fab, faShoppingCart, faUser, faTrash)

function App() {
    return (
        <BrowserRouter>
            <Header/>
            <main className="py-3">
                <Container>
                    <Route path="/" component={HomePage} exact/>
                    <Route path="/login" component={LoginPage}/>
                    <Route path="/register" component={RegisterPage}/>
                    <Route path="/shipping" component={ShippingPage}/>
                    <Route path="/placeorder" component={PlaceOrderPage}/>
                    <Route path="/order/:id" component={OrderPage}/>
                    <Route path="/payment" component={PaymentPage}/>
                    <Route path="/products/:id" component={ProductPage}/>
                    <Route path="/cart/:id?" component={CartPage}/>
                </Container>
            </main>
            <Footer/>
        </BrowserRouter>
    )
}

export default App
