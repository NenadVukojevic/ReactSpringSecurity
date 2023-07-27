import { useState } from 'react'
import useAuth from '../hooks/useAuth';

import { useNavigate } from "react-router-dom";
import './Login.css';

import axios from '../../api/axios';
const LOGIN_URL = '/login';

const Login = () => {
    const navigate = useNavigate();
    const { setAuth } = useAuth ();
    const [credentials, setCredentials] =
        useState({
            username: ''
            , password: ''
        });

    const [ errorMessage, setErrorMessage] = useState('');

    const handleChange = (ev) => {
        setCredentials({ ...credentials, [ev.target.name]: ev.target.value });
        setErrorMessage('');
    }

    const login = async (ev) => {
        ev.preventDefault();

        try{
            const response = await axios.post(LOGIN_URL, credentials
                );
            const jwt = response.data.jwt;
            console.log(jwt);
            const user = credentials.username;
            
            setAuth({user , jwt});
            navigate("/");
        }
        catch(err)
        {
            setErrorMessage("unsuccessful login!");
            console.log(err);
        }
    }


    return (
        <main>
            <h1>
                Login
            </h1>
            <p className={errorMessage? "errorMessage":"hidden"}>{errorMessage}</p>
            <form>
                <label htmlFor='username'>Username:</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    value={credentials.username}
                    autoComplete='off'
                    required
                    onChange={handleChange}
                />
                <label htmlFor='password'>password:</label>
                <input
                    type="password"
                    id="password"
                    value={credentials.password}
                    name="password"
                    required
                    onChange={handleChange}
                />
                <button onClick={login}>Login</button>
            </form>
        </main>
    )
}

export default Login