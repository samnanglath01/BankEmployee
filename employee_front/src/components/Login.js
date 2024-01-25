import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import Cookies from 'js-cookie';
const Login = () =>{
    const navigate = useNavigate();
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [token, setToken] = useState('');
    console.log(Date.now())
    const handleSubmit = async(event) => {
        event.preventDefault();
        
        const tokenEndpoint = 'http://localhost:8081/realms/keycloak_realm/protocol/openid-connect/token';

        try{
            const response = await axios.post(tokenEndpoint, {
                                    grant_type: 'password',
                                    client_id: 'client_employee',
                                    client_secret: 'si70rp5V8VQXm5p0PP73LtOc9QxMzZBr',
                                    username: username,
                                    password: password
                                    }, {
                                        headers: {
                                            'Content-Type': 'application/x-www-form-urlencoded'
                                        }
                                    })

            const accessToken = response.data.access_token;
            console.log(response.data)
            localStorage.setItem("isValidToken", Date.now() + response.data.expires_in * 1000)
            // localStorage.setItem("expires_in", response.data.expires_in)
            localStorage.setItem("token", accessToken)
            localStorage.setItem("refresh_token", response.data.refresh_token)
            setToken(accessToken);
            Cookies.set('token', accessToken, { expires: 1 }); // Store the token in a cookie that expires in 1 day
            
            if(accessToken !== null){
                navigate("/"); // Redirect to the home page
            }
        }
        catch (error) {
        console.error('Error retrieving token:', error);
        window.alert('Username or Password is incorrect');
        };
    }

    const getJson = async (event) => {
        event.preventDefault();
        console.log("using method getJson()")
        const url = 'http://localhost:8080/myAcc';
      
        try {
          const response = await axios.get(url, {
            headers: {
                // "Content-Type": "application/json",
              "Authorization": "Bearer " + localStorage.getItem("token")
            }
          });
      
          const jsonString = response.data;
          console.log(response.data);
        } catch (error) {
          console.error('Error retrieving token:', error);
        }
    };

    return (
        <div>
            <form onSubmit={handleSubmit}>

            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">
                    Username
                </label>
                <input
                    type="text"
                    value={username}
                    placeholder='Username or Email'
                    onChange={event => setUsername(event.target.value)}
                    className="h-10 w-96 border mt-2 px-2 py-2">
                
                </input>
            </div>
            <div className="items-center justify-center h-14 w-full my-4">
                <label className="block text-gray-600 text-sm font-normal">
                    Password
                </label>
                <input
                type="password"
                value={password}
                placeholder="Password"
                onChange={event => setPassword(event.target.value)}
                className="h-10 w-96 border mt-2 px-2 py-2">

                </input>
            </div>

                <button type="submit" className='rounded bg-slate-600 text-white px-6 py-2 font-semibold'>Login</button>
            </form>
            <p>Token: {token}</p>


            <button onClick={getJson} className='rounded bg-slate-600 text-white px-6 py-2 font-semibold'>fetch json</button>

        </div>
        );

}
export default Login;
