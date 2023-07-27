import { useLocation, Navigate, Outlet } from "react-router-dom";
import useAuth from "../hooks/useAuth";
import Nav from "../Navbar/Nav";
import useAxiosJWT from "./useAxiosJWT";
import { useEffect, useState } from "react";

const RequireAuth = () => {
    const { auth } = useAuth();
    const location = useLocation();
    const [roles, setRoles] = useState([])

    const axiosJWT = useAxiosJWT();

    useEffect(() => {
        const getResponse = async () => {

            try {
                const responseRoles = await axiosJWT.get('/public/usermanagement/user/roles/' + auth.user);
                setRoles(responseRoles.data);
            }
            catch (err) {
                console.log(err)
            }
        };

        getResponse();

    }, [axiosJWT, auth])

    return (
        auth?.user ?
            <>
                <Nav roles={roles}/>
                <Outlet />
            </>

            : <Navigate to="/login" state={{ from: location }} replace />
    );
}

export default RequireAuth;