import React from 'react'

const Roles = ({ roles, userRoles, setRoles }) => {
    console.log(userRoles);
    return (
        <div className='roles'>
            <div>
                Roles
            </div>
            <ul>
                {
                    roles.map(role => (
                        <li className='role_checkbox' key={role.id} onClick={()=>setRoles(role)}>
                            <input type="checkbox" 
                            checked={userRoles.some(userRole=> userRole.id === role.id )} 
                            onChange={()=>setRoles(role)}/>
                            <label>{role.roleName}</label>
                        </li>
                    ))
                }
            </ul>
        </div>
    )
}

export default Roles