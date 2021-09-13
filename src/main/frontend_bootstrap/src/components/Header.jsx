import React from 'react'
import {Container, Nav, Navbar} from 'react-bootstrap'
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome'
import {LinkContainer} from 'react-router-bootstrap'

function Header() {
    return (
        <header>
            <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
                <Container>
                    {/* Route to the home page */}
                    <LinkContainer to="/">
                        <Navbar.Brand>eCommerce Site</Navbar.Brand>
                    </LinkContainer>
                    <Navbar.Toggle aria-controls="basic-navbar-nav"/>
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav className="me-auto">
                            {/* Route for the shopping cart */}
                            <LinkContainer to="/cart">
                                <Nav.Link><FontAwesomeIcon icon="shopping-cart"/> Shopping Cart</Nav.Link>
                            </LinkContainer>
                            {/* Route for login */}
                            <LinkContainer to="/login">
                                <Nav.Link><FontAwesomeIcon icon="user"/> Login</Nav.Link>
                            </LinkContainer>
                        </Nav>
                    </Navbar.Collapse>
                </Container>
            </Navbar>
        </header>
    )
}

export default Header