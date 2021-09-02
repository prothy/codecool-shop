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
  return await fetch('/api.cart', {
    headers: {
      'Content-Type': 'application/json',
    },
  })
}

export const addProductToCart = async (product) => {
  await fetch('/api.cart?action=add', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: {
      product: product,
    },
  })
}

export const removeProductToCart = async (product) => {
  await fetch('/api.cart?action=remove', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: {
      product: product,
    },
  })
}
