export const getAllProducts = async () => {
    const response = await fetch('/api.noIDEaSHop', {
        headers: {
            'Content-Type': 'application/json',
        },
    })
    return await response.json()
}

export const getProductByCategory = async (productCategory) => {
    console.log(productCategory)
    const response = await fetch(`/api.noIDEaSHop?category=${productCategory}`, {
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
  return response.json()
}

export const addProductToCart = async (product) => {
  await fetch('/api.cart?action=add', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json',
    },
    body: {
      product: JSON.stringify(product),
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
      product: JSON.stringify(product),
    },
  })
}
