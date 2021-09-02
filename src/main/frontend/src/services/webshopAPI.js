export const getAllProducts = async () => {
  const response = await fetch('/api.noIDEaSHop', {
    headers: {
      'Content-Type': 'application/json',
    },
  })
  return await response.json()
}

// TODO: Implement fetch cart call:
export const fetchCart = async () => {
  const response = await fetch('/api.cart', {
    headers: {
      'Content-Type': 'application/json',
    },
  })
  console.log('its happening!')
  return response.json()
}

export const addProductToCart = async (product) => {
  await fetch('/api.cart?action=add', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(product),
  })
}

export const removeProductFromCart = async (product) => {
  await fetch('/api.cart?action=remove', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(product),
  })
}
