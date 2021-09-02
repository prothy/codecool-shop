import React from 'react'
import {
  Card,
  CardActions,
  CardContent,
  CardMedia,
  IconButton,
  Typography,
} from '@material-ui/core'
import { AddShoppingCart, Favorite } from '@material-ui/icons'

import useStyles from './styledProduct'

const Product = ({ product, onAddToCart }) => {
  const classes = useStyles()

  return (
    <Card className={classes.root}>
      <CardMedia
        className={classes.media}
        image={product.image}
        title={product.name}
      />
      <CardContent>
        <div className={classes.cardContent}>
          <Typography variant="body1" gutterBottom>
            {product.name}
          </Typography>
          <Typography variant="body1">
            {product.price} {product.defaultCurrency}
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
        <IconButton
          aria-label="Add to Cart"
          onClick={() => onAddToCart(product.id, 1)}
        >
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
