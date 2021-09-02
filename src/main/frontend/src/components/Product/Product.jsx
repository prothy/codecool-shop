import React from 'react'
import {
    Card,
    CardActions,
    CardContent,
    CardMedia,
    IconButton,
    Typography,
} from '@material-ui/core'
import {AddShoppingCart, Favorite} from '@material-ui/icons'
import {Link} from 'react-router-dom'

import useStyles from './styledProduct'
import { addProductToCart, fetchCart } from '../../services/webshopAPI'

const Product = ({ product, setCart }) => {
  const classes = useStyles()

  const addToCart = () => {
    addProductToCart(product)
    fetchCart().then((products) => setCart(products))
  }

  return (
    <Card className={classes.root}>
        <Link to={`/products/${product.id}`}>
            < CardMedia
                className={classes.media}
                image={product.image}
                title={product.name}
            />
        </Link>
      <CardContent>
        <div className={classes.cardContent}>
          <Typography variant="body1" gutterBottom>
            {product.name}
          </Typography>
          <Typography variant="body1">
            ${product.price}
            {product.yearlyPrice}
          </Typography>
        </div>
        {/*TODO: Product description*/}
        {/*<Typography variant='h2' color='textSecondary'>*/}
        {/*    {product.description}*/}
        {/*</Typography>*/}
      </CardContent>
      {/* Button functionality*/}
      <CardActions disableSpacing className={classes.cardActions}>
        {/*TODO: Add functionality to shopping cart button*/}
        <IconButton aria-label="Add to Cart" onClick={() => addToCart()}>
          <AddShoppingCart />
        </IconButton>
        <IconButton aria-label="Favorites">
          {/*TODO: Add functionality to favorite button*/}
          <Favorite />
        </IconButton>
      </CardActions>
    </Card>
  )
}

export default Product
