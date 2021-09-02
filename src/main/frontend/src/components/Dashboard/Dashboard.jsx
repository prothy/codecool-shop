import React, {useState} from 'react'
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
	CloudCircle, Home
} from '@material-ui/icons'
import ShoppingCartPanel from '../ShoppingCart/ShoppingCartPanel'


export default function Dashboard({children}) {
	const classes = useStyles()
	const [open, setOpen] = useState(true)
	const [openProduct, setOpenProduct] = useState(true)
	const [show, showCart] = useState(false)

	const handleClick = () => {
		setOpenProduct(!openProduct)
	}

	const mainListItems = (
		<div>
			<List>
				{/* HOME */}
				{/* TODO: Add route to home */}
				<ListItem button>
					<ListItemIcon>
						<Home/>
					</ListItemIcon>
					<ListItemText primary="Home"/>
				</ListItem>
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
				{/* STORE */}
				<ListItem button onClick={handleClick}>
					<ListItemIcon>
						<Store/>
					</ListItemIcon>
					<ListItemText primary="Products"/>
					{open ? <ExpandLess/> : <ExpandMore/>}
				</ListItem>
				<Divider/>
				<Collapse in={openProduct} timeout="auto" unmountOnExit>
					<List component="div" disablePadding>
						<ListItem button className={classes.nested}>
							<ListItemIcon>
								<Favorite/>
							</ListItemIcon>
							<ListItemText primary="Favorite"/>
						</ListItem>
						<ListItem button className={classes.nested}>
							<ListItemIcon>
								<Web/>
							</ListItemIcon>
							<ListItemText primary="OS"/>
						</ListItem>
						<ListItem button className={classes.nested}>
							<ListItemIcon>
								<RemoveFromQueue/>
							</ListItemIcon>
							<ListItemText primary="IDE"/>
						</ListItem>
						<ListItem button className={classes.nested}>
							<ListItemIcon>
								<CloudCircle/>
							</ListItemIcon>
							<ListItemText primary="Cloud"/>
						</ListItem>
					</List>
				</Collapse>
			</List>
		</div>
	)

	const fakesonData = [
		{name: 'basketball', price: '$500', url: ''},
		{name: 'fakeball', price: '$500', url: ''},
		{name: 'fakeball', price: '$500', url: ''},
	]

	const handleDrawerOpen = () => {
		setOpen(true)
	}
	const handleDrawerClose = () => {
		setOpen(false)
	}

	return (
		<div className={classes.root}>
			<CssBaseline/>
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
					{/*TODO: Add badge content numbers according to the items in your cart*/}

					<IconButton
						color="inherit"
						className="shopping-cart__button"
						onClick={() => showCart(!show)}
					>
						<Badge badgeContent={4} color="secondary">
							<ShoppingCart/>
						</Badge>
						{show && <ShoppingCartPanel data={fakesonData}/>}
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
						<ChevronLeftIcon/>
					</IconButton>
				</div>
				<Divider/>
				<List>{mainListItems}</List>
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
