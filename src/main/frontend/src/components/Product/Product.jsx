import React from 'react'

// Every component takes one argument called "props", which is a JS object {}
// props = { name: "blah", price: "$5 }
function Product({imageURL, name, price}) {
    return (
        <div>
            <img src={imageURL} alt={name}/>
            My product item: {name} and it costs {price}
        </div>
    )
}

export default Product