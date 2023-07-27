import React from 'react'
import { FaUserCheck, FaUserLock } from 'react-icons/fa';

const ListOfUsers = ({ users, EditUser, AddUser }) => {
    return (
        <div>
            <div className='title'>List of users</div>
            <div className='user_list'>
                {
                    users.map(user => (

                        <div className='user_details' key={user.userId} onClick={() => EditUser(user.userId)}>
                            <div className='fullName'>{user.fullName}</div>
                            <div className='username'>{user.username}</div>
                            <div className='email'>{user.email}</div>
                            <div className='indicator'>{user.isApproved === 1 ? (<span style={{ color: "green" }}><FaUserCheck /></span>) : (<span style={{ color: "red" }}><FaUserLock /></span>)}</div>
                        </div>

                    ))
                }
            </div>
            <div className='users_button_group'>
                <button  onClick={AddUser} >AddUser</button>
            </div>
        </div>
    )
}

export default ListOfUsers