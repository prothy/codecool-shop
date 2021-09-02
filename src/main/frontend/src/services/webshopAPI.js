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
