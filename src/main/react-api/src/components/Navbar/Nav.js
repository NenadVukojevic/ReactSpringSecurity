import React from 'react'
import { Link } from 'react-router-dom'
import './Nav.css'


const Nav = ({roles}) => {
    

    
    return (

        <nav className='nav'>
            <Link className='nav-main' to="/">BlueBirch</Link>
            <ul>
                <li>
                    <Link to="/shopping">Shopping List</Link>
                </li>
                {
                    roles.includes('ADMIN') &&
                    (
                        <li>
                            <Link to="/userManagement">Users</Link>
                        </li>

                    )
                }
                {
                    roles.includes('ADMIN') &&
                    (
                        <li>
                            <Link to="/hello">Hello</Link>
                        </li>
                    )
                }
                <li>
                    <Link to="/version">Version</Link>
                </li>
                <li>
                    <Link to="/logout">Logout</Link>
                </li>
            </ul>
        </nav>
    )


}

export default Nav