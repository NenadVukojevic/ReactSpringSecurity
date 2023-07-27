import axios from "../../api/axios";
import { useEffect } from "react";
import useAuth from "./useAuth";

const useAxiosJWT = () => {

    const { auth, setAuth } = useAuth();

    useEffect(() => {

        const requestIntercept = axios.interceptors.request.use(
            (request) => {
                if (!request.headers['Authorization']) {
                    request.headers['Authorization'] = `Bearer ${auth?.jwt}`;
                }
                return request;
            }, (error) => Promise.reject(error)
        );

        const responseIntercept = axios.interceptors.response.use(
            response => { return response },
            async (error) => {
                /*if (error?.response?.status === 403 ){
                    //if expired - mark as logged out
                    
                }*/
                  const prevRequest = error?.config;
                  if (error?.response?.status === 403 && !prevRequest?.sent) {
                      prevRequest.sent = true;
                      console.log("refreshed token");
                      return axios(prevRequest);
                  }
                setAuth(null);  
                return Promise.reject(error);
            }
        );

        return () => {
            axios.interceptors.request.eject(requestIntercept);
            axios.interceptors.response.eject(responseIntercept);
        }
    }, [auth, setAuth])

    return axios;
}

export default useAxiosJWT