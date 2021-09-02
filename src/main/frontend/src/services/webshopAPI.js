export const getAllProducts = async () => {
    const response = await fetch('/api.noIDEaSHop', {
        headers: {
            'Content-Type': 'application/json',
        },
    })
    return await response.json()
}

// TODO: Implement fetch cart call:
