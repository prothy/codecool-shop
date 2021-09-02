import React, { useState } from 'react'
import clsx from 'clsx'
import CssBaseline from '@material-ui/core/CssBaseline'
import Drawer from '@material-ui/core/Drawer'
import AppBar from '@material-ui/core/AppBar'
import Toolbar from '@material-ui/core/Toolbar'
import List from '@material-ui/core/List'
import Typography from '@material-ui/core/Typography'
import Divider from '@material-ui/core/Divider'
import IconButton from '@material-ui/core/IconButton'
import Badge from '@material-ui/core/Badge'
import MenuIcon from '@material-ui/icons/Menu'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import useStyles from './styledDashboard'
import {
  Container,
  ListItem,
  ListItemIcon,
  ListItemText,
} from '@material-ui/core'
import DashboardIcon from '@material-ui/icons/Dashboard'
import { ShoppingCart } from '@material-ui/icons'
import ShoppingCartPanel from '../ShoppingCart/ShoppingCartPanel'

const mainListItems = (
  <div>
    <ListItem button>
      <ListItemIcon>
        <DashboardIcon />
      </ListItemIcon>
      <ListItemText primary="Dashboard" />
    </ListItem>
  </div>
)

export default function Dashboard({ children }) {
  const classes = useStyles()
  const [open, setOpen] = useState(true)
  const [show, showCart] = useState(false)

  const fakesonData = [
    { name: 'basketball', price: '$500', url: '' },
    { name: 'fakeball', price: '$500', url: '' },
    { name: 'fakeball', price: '$500', url: '' },
  ]

  const handleDrawerOpen = () => {
    setOpen(true)
  }
  const handleDrawerClose = () => {
    setOpen(false)
  }

  return (
    <div className={classes.root}>
      <CssBaseline />
      <AppBar
        position="absolute"
        color="transparent"
        className={clsx(classes.appBar, open && classes.appBarShift)}
      >
        <Toolbar className={classes.toolbar}>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            className={clsx(
              classes.menuButton,
              open && classes.menuButtonHidden
            )}
          >
            <MenuIcon />
          </IconButton>
          <Typography
            component="h1"
            variant="h5"
            color="inherit"
            noWrap
            className={classes.title}
          >
            {/*TODO: Fix logo sizing*/}
            {/*<img src={logo} alt="NoIDEa" className={classes.image}/>*/}
            <strong>NoIDEa - The Software Store</strong>
          </Typography>
          {/*TODO: Add badge content numbers according to the items in your cart*/}

          <IconButton
            color="inherit"
            className="shopping-cart__button"
            onClick={() => showCart(show ? false : true)}
          >
            <Badge badgeContent={4} color="secondary">
              <ShoppingCart />
            </Badge>
            {show && <ShoppingCartPanel data={fakesonData} />}
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        classes={{
          paper: clsx(classes.drawerPaper, !open && classes.drawerPaperClose),
        }}
        open={open}
      >
        <div className={classes.toolbarIcon}>
          <IconButton onClick={handleDrawerClose}>
            <ChevronLeftIcon />
          </IconButton>
        </div>
        <Divider />
        <List>{mainListItems}</List>
      </Drawer>

      <main className={classes.content}>
        <div className={classes.appBarSpacer} />
        <Container maxWidth="lg" className={classes.container}>
          {children}
        </Container>
      </main>
    </div>
  )
}
