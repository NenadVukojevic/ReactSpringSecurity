import {useEffect} from 'react'
import useAuth from '../hooks/useAuth';
import { useNavigate } from "react-router-dom";

export const Logout = () => {

    const navigate = useNavigate();
    const  {setAuth} = useAuth();

    useEffect(() => {
      setAuth({})
      navigate('/login')
    }, [ navigate, setAuth])
    
    return (
    null
  )
}
