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
// import MenuIcon from '@material-ui/icons/Menu'
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft'
import useStyles from './styledDashboard'
import {
    Collapse,
    Container,
    ListItem,
    ListItemIcon,
    ListItemText,
} from '@material-ui/core'
import {
    ExpandLess,
    ExpandMore,
    ShoppingCart,
    Person,
    Store,
    Favorite,
    Web,
    RemoveFromQueue,
    CloudCircle, Home, Build, Business, Adb, LaptopWindows, TextFormat, ListAlt, AllOut, FontDownload
} from '@material-ui/icons'
import ShoppingCartPanel from '../ShoppingCart/ShoppingCartPanel'
import {Link} from 'react-router-dom'

export default function Dashboard({ children, cart, handleSetProducts, handleSupplier}) {

    const classes = useStyles()
    const [open, setOpen] = useState(true)
    const [openProduct, setOpenProduct] = useState(true)
    const [openSupplier, setSupplier] = useState(true)
    const [show, showCart] = useState(false)

    // Click functions
    const handleClick = () => {
        setOpenProduct(!openProduct)
    }
    const handleClickSupplier = () => {
        setSupplier(!openSupplier)
    }
    const handleDrawerOpen = () => {
        setOpen(true)
    }
    const handleDrawerClose = () => {
        setOpen(false)
    }


    const dashBoardListElements = (
        <div>
            <List>
                {/* HOME */}
                <Link to="/" style={{color: 'black', textDecoration: 'none'}}>
                    <ListItem button onClick={() => handleSetProducts('all')}>
                        <ListItemIcon>
                            <Home/>
                        </ListItemIcon>
                        <ListItemText primary="Home"/>
                    </ListItem>
                </Link>
                <Divider/>

                {/* USER */}
                {/* TODO: Add route to user information (2nd Sprint) */}
                <ListItem button>
                    <ListItemIcon>
                        <Person/>
                    </ListItemIcon>
                    <ListItemText primary="User Information"/>
                </ListItem>
                <Divider/>
                {/* PRODUCTS */}
                <ListItem button onClick={handleClick}>
                    <ListItemIcon>
                        <Store/>
                    </ListItemIcon>
                    <ListItemText primary="Products"/>
                    {open ? <ExpandLess/> : <ExpandMore/>}
                </ListItem>
                <Divider/>
                {/* COLLAPSE BAR*/}
                <Collapse in={openProduct} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        {/* FAVORITE */}
                        <ListItem button className={classes.nested}>
                            <ListItemIcon>
                                <Favorite/>
                            </ListItemIcon>
                            <ListItemText primary="Favorite"/>
                        </ListItem>
                        {/* OS */}
                        <ListItem button className={classes.nested} onClick={() => handleSetProducts('OS')}>
                            <ListItemIcon>
                                <Web/>
                            </ListItemIcon>
                            <ListItemText primary="OS"/>
                        </ListItem>
                        {/* IDE */}
                        <ListItem button className={classes.nested} onClick={() => handleSetProducts('IDE')}>
                            <ListItemIcon>
                                <RemoveFromQueue/>
                            </ListItemIcon>
                            <ListItemText primary="IDE"/>
                        </ListItem>
                        {/* CLOUD */}
                        <ListItem button className={classes.nested} onClick={() => handleSetProducts('Cloud')}>
                            <ListItemIcon>
                                <CloudCircle/>
                            </ListItemIcon>
                            <ListItemText primary="Cloud"/>
                        </ListItem>
                        {/* COLLABORATION TOOL */}
                        <ListItem button className={classes.nested} onClick={() => handleSetProducts('WorkTool')}>
                            <ListItemIcon>
                                <Build/>
                            </ListItemIcon>
                            <ListItemText primary="Collaboration Tool"/>
                        </ListItem>
                    </List>
                </Collapse>
                <Divider/>
                {/* SUPPLIERS */}
                <ListItem button onClick={handleClickSupplier}>
                    <ListItemIcon>
                        <Business/>
                    </ListItemIcon>
                    <ListItemText primary="Suppliers"/>
                    {open ? <ExpandLess/> : <ExpandMore/>}
                </ListItem>
                <Divider/>
                {/* COLLAPSE BAR*/}
                <Collapse in={openSupplier} timeout="auto" unmountOnExit>
                    <List component="div" disablePadding>
                        {/* Google */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('Google')}>
                            <ListItemIcon>
                                <Adb/>
                            </ListItemIcon>
                            <ListItemText primary="Google"/>
                        </ListItem>
                        {/* Microsoft */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('Microsoft')}>
                            <ListItemIcon>
                                <LaptopWindows/>
                            </ListItemIcon>
                            <ListItemText primary="Microsoft"/>
                        </ListItem>
                        {/* Amazon */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('Amazon')}>
                            <ListItemIcon>
                                <TextFormat/>
                            </ListItemIcon>
                            <ListItemText primary="Amazon"/>
                        </ListItem>
                        {/* JetBrains */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('JetBrains')}>
                            <ListItemIcon>
                                <ListAlt/>
                            </ListItemIcon>
                            <ListItemText primary="JetBrains"/>
                        </ListItem>
                        {/* Slack */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('Slack')}>
                            <ListItemIcon>
                                <AllOut/>
                            </ListItemIcon>
                            <ListItemText primary="Slack"/>
                        </ListItem>
                        {/* Atlassian */}
                        <ListItem button className={classes.nested} onClick={() => handleSupplier('Atlassian')}>
                            <ListItemIcon>
                                <FontDownload/>
                            </ListItemIcon>
                            <ListItemText primary="Atlassian"/>
                        </ListItem>
                    </List>
                </Collapse>
            </List>
        </div>
    )

    return (
        <div className={classes.root}>
            <CssBaseline/>
            <AppBar
                position="absolute"
                color="primary"
                className={clsx(classes.appBar, open && classes.appBarShift)}
            >
                <Toolbar className={classes.toolbar} style={{color: 'black'}}>
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
                        {/*<MenuIcon />*/}
                    </IconButton>
                    <Typography
                        component="h1"
                        variant="h5"
                        color="inherit"
                        noWrap
                        className={classes.title}
                    >
                        <strong>NoIDEa - The Software Store</strong>
                    </Typography>

          <IconButton
            color="inherit"
            className="shopping-cart__button"
            onClick={() => showCart(!show)}
          >
            <Badge badgeContent={cart.itemNumber} color="secondary">
              <ShoppingCart />
            </Badge>
            {show && <ShoppingCartPanel />}
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
        <List>{dashBoardListElements}</List>
      </Drawer>

            <main className={classes.content}>
                <div className={classes.appBarSpacer}/>
                <Container maxWidth="lg" className={classes.container}>
                    {children}
                </Container>
            </main>
        </div>
    )
}
