import React, { useEffect, useState } from 'react'
import useAxiosJWT from '../hooks/useAxiosJWT'
import UserForm from './UserForm';
import ListOfUsers from './ListOfUsers';

const FormState = {
    LIST: 0,
    EDIT: 1,
    NEW: 2,
}

const UserManagement = () => {
    const URL = '/admin/usermanagement/roles';
    const URL_Users = '/admin/usermanagement/users';
    const URL_Save = '/admin/usermanagement/user/save';
    const URL_Delete = '/admin/usermanagement/user/delete/';

    const [roles, setRoles] = useState([]);
    const [users, setUsers] = useState([]);
    const [user, setUser] = useState({});
    const [state, setState] = useState(FormState.LIST);


    const axiosJWT = useAxiosJWT();

    useEffect(() => {
        const getResponse = async () => {

            try {
                const response = await axiosJWT.get(URL);
                console.log("UserManagement->GetResponse", response);
                setRoles(response.data);
            }
            catch (err) {
                console.log(err)
            }
        };

        getResponse();

    }, [axiosJWT])



    useEffect(() => {
        const getUsers = async () => {

            try {
                const response = await axiosJWT.get(URL_Users);
                console.log("UserManagement->GetUsers", response);
                setUsers(response.data);
            }
            catch (err) {
                console.log(err)
            }
        };
        getUsers();

    }, [axiosJWT, state])


    const EditUser = (id) => {
        console.log(id);
        const selected = users.find(user => user.userId === id);
        setUser(selected);
        setState(FormState.EDIT);
    }

    const AddUser = () => {
        setUser({
            "userId": 0,
            "email": "",
            "password": "",
            "isApproved": 0,
            "fullName": "",
            "authorities": [],
            "enabled": true,
            "username": "",
            "accountNonExpired": true,
            "accountNonLocked": true,
            "credentialsNonExpired": true
        });
        setState(FormState.NEW);
    }

    const Cancel = () => {
        setState(FormState.LIST);
    }

    const Save = async () => {
        try {
            await axiosJWT.post(URL_Save, user);
            setState(FormState.LIST);
        }
        catch (err) {
            console.log(err)
        }
    }


    const Delete = async (id) => {
        try {
            await axiosJWT.get(URL_Delete + id);
            setState(FormState.LIST);
        }
        catch (err) {
            console.log(err)
        }
    }

    return (
        <div>

            {
                state === FormState.LIST && (<ListOfUsers users={users} EditUser={EditUser} AddUser={AddUser} />)
            }
            {
                state === FormState.EDIT && (
                    <UserForm user={user}
                        setUser={setUser}
                        roles={roles}
                        Cancel={Cancel}
                        Save={Save}
                        Delete={Delete}
                    />)
            }
            {
                state === FormState.NEW && (
                    <UserForm user={user}
                        setUser={setUser}
                        roles={roles}
                        Cancel={Cancel}
                        Save={Save} />)
            }

        </div>
    )
}

export default UserManagement