import { useEffect, useState } from 'react'
import useAxiosJWT from '../hooks/useAxiosJWT'

const Hello = () => {
    const URL = '/admin/hello'
    const [message, setmessage] = useState('');

    const axiosJWT = useAxiosJWT();
    useEffect(() => {
        const getResponse = async () => {
            
            try{
                const response = await axiosJWT.get(URL);
                setmessage(response.data);
                
                console.log(response);
            }
            catch(err)
            {
                console.log(err)
            }
        };
        
        getResponse();
        
    }, [axiosJWT])
    

    return (
        <div>{message}</div>
    )
}

export default Hello