import React from 'react'
import Roles from './Roles';

const UserForm = ({ user, setUser, roles, Cancel, Save, Delete }) => {

    const handleChange = (ev) => {
        setUser({ ...user, [ev.target.id]: ev.target.value });
    }

    const handleCheckBoxChange = (ev) =>{
        console.log(ev.target.id);
        setUser({ ...user, [ev.target.id]: user[ev.target.id]===1? 0 : 1})
    }

    const handleFocus = (ev) =>{
        ev.target.select();
    }

    const setRoles = (roleToChange) =>{
        const temp = [...user.authorities];
        var changed;
        // if user has that role
        // it would be removed
        if(temp.some(role => role.id  === roleToChange.id))
        {
            changed = temp.filter(role => role.id !== roleToChange.id);
        }
        // otherwise it would be added
        else
        {
            changed = [...temp, roleToChange];
        }
        setUser({...user, 'authorities':changed});
    }


    return (
        <div>
            <div className='title'>
                Edit User
            </div>
            <div className='two_columns'>
                <div className='user_form'>
                    <div className='form_control'>
                        <label htmlFor='fullName'>full name:</label>
                        <input
                            id="fullName"
                            type="text"
                            value={user.fullName}
                            onChange={handleChange} />
                    </div>
                    <div className='form_control'>
                        <label htmlFor='username'>username:</label>
                        <input
                            id="username"
                            type="text"
                            value={user.username}
                            onChange={handleChange} />
                    </div>
                    <div className='form_control'>
                        <label htmlFor='password'>password:</label>
                        <input
                            id="password"
                            type="password"
                            value={user.password}
                            onChange={handleChange} 
                            onFocus={handleFocus}/>
                    </div>
                    <div className='form_control'>
                        <label htmlFor='email'>email:</label>
                        <input
                            id="email"
                            type="email"
                            value={user.email}
                            onChange={handleChange} />
                    </div>
                    <div className='form_control_checkbox'>
                        <input
                            id="isApproved"
                            type="checkbox"
                            checked={user.isApproved === 1 ? true : false}
                            onChange={handleCheckBoxChange} />
                        <label htmlFor='isApproved'>active:</label>
                    </div>
                </div>
                <div>
                    <Roles roles={roles} userRoles={user.authorities} setRoles={setRoles}/>
                </div>
            </div>
            <div className='users_button_group'>
                <button  onClick={Cancel} >Cancel</button>
                <button onClick={Save} >Save</button>
                {
                    Delete && (
                        <button onClick={()=>Delete(user.userId)} >Delete</button>
                    )
                }
            </div>
        </div>
    )
}

export default UserForm