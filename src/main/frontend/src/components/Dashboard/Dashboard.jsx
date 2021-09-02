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
	CloudCircle, Home, Build
} from '@material-ui/icons'
import ShoppingCartPanel from '../ShoppingCart/ShoppingCartPanel'

export default function Dashboard({children, handleSetProducts}) {

	const classes = useStyles()
	const [open, setOpen] = useState(true)
	const [openProduct, setOpenProduct] = useState(true)
	const [show, showCart] = useState(false)

	// Click functions
	const handleClick = () => {
		setOpenProduct(!openProduct)
	}
	const handleDrawerOpen = () => {
		setOpen(true)
	}
	const handleDrawerClose = () => {
		setOpen(false)
	}


	const mainListItems = (
		<div>
			<List>
				{/* HOME */}
				{/* TODO: Add route to home */}
				<ListItem button onClick={() => handleSetProducts('all')}>
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
			</List>
		</div>
	)

	const fakesonData = [
		{name: 'basketball', price: '$500', url: ''},
		{name: 'fakeball', price: '$500', url: ''},
		{name: 'fakeball', price: '$500', url: ''},
	]


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
