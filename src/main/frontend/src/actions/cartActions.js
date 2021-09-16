import axios from 'axios'
import {CART_ADD_ITEM, CART_REMOVE_ITEM, CART_SAVE_SHIPPING_ADDRESS, CART_SAVE_PAYMENT_METHOD} from '../constants/cartConstants'

export const addToCart = (id, qty) => async (dispatch, getState) => {
    // TODO: Fix it from the api 2.
    // const {data} = await axios.get(`/api/products/${id}`)
    const {data} = await axios.get('/api/products')
    const datum = data.filter(item => item.id === Number(id))[0]

    dispatch({
        type: CART_ADD_ITEM,
        payload: {
            product: datum.id,
            name: datum.name,
            image: datum.image,
            price: datum.price,
            qty
        }
    })

    localStorage.setItem('cartItems', JSON.stringify(getState().cart.cartItems))
}

export const removeFromCart = (id) => (dispatch, getState) => {
    dispatch({
        type: CART_REMOVE_ITEM,
        payload: id,
    })

    localStorage.setItem('cartItems', JSON.stringify(getState().cart.cartItems))
}

export const saveShippingAddress = (data) => (dispatch) => {
    dispatch({
        type: CART_SAVE_SHIPPING_ADDRESS,
        payload: data,
    })

    localStorage.setItem('shippingAddress', JSON.stringify(data))
}

export const savePaymentMethod = (data) => (dispatch) => {
    dispatch({
        type: CART_SAVE_PAYMENT_METHOD,
        payload: data,
    })

    localStorage.setItem('paymentMethod', JSON.stringify(data))
}