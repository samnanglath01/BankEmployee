import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import RegisterService from "../service/RegisterService";

const Register = () => {
  const [showModal, setShowModal] = useState(false);
  const [otpCode, setOtpCode] = useState("");
  const navigate = useNavigate();

  //   useEffect(() => {
  //     if (localStorage.getItem("token") === null) {
  //       navigate("/login");
  //     }
  //   }, [navigate]);

  const [user, setUser] = useState({
    emailId: "",
    userName: "",
    firstname: "",
    lastName: "",
    password: "",
  });

  const handleChange = (e) => {
    const { name, type, value } = e.target;
    if (type === "text") {
      setUser({ ...user, [name]: value });
    } else if (type === "email") {
      setUser({ ...user, [name]: value });
    } else if (type === "password") {
      setUser({ ...user, [name]: value });
    }
    if (name === "otpCode") {
      setOtpCode(value);
    }
  };

  const verifyEmail = (e) => {
    e.preventDefault();
    console.log(user.emailId);

    RegisterService.verifyEmail(user.emailId)
      .then((response) => {
        console.log(response);
        // navigate("/");
      })
      .catch((error) => {
        // const { data } = error.response;
        // window.alert(
        // `First Name: ${data.firstName}\n\nLast Name: ${data.lastName}\n\nPhone Number: ${data.phoneNumber}\n\nEmail: ${data.email}`
        // );
        console.log(error);
      });

    setShowModal(true);
  };

  const registerUser = (e) => {
    e.preventDefault();
    console.log(otpCode);
    console.log(user)

    RegisterService.registerUser(otpCode, user)
      .then((response) => {
        console.log(response);
        setShowModal(false)
        navigate("/login");
      })
      .catch((error) => {
        const { data } = error.response;
        console.log(data.message)
        window.alert(data.message);
        console.log(error);
      });
    setOtpCode("")
  };

  const reset = (e) => {
    e.preventDefault();
    setUser({
      emailId: "",
      userName: "",
      firstname: "",
      lastName: "",
      password: "",
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b flex justify-center">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Employee</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Username
          </label>
          <input
            type="text"
            name="userName"
            required
            value={user.userName}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            First Name
          </label>
          <input
            required
            type="text"
            name="firstname"
            value={user.firstname}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Last Name
          </label>
          <input
            required
            type="text"
            name="lastName"
            value={user.lastName}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Email
          </label>
          <input
            required
            type="email"
            name="emailId"
            value={user.emailId}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Password
          </label>
          <input
            required
            type="password"
            name="password"
            value={user.password}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          {/* <button
            onClick={registerUser}
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6"
          >
            Register
          </button> */}

          <button
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6 shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
            type="button"
            onClick={verifyEmail}
          >
            Register
          </button>
          {showModal ? (
            <>
              <div className="justify-center items-center flex overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none">
                <div className="relative w-auto my-6 mx-auto max-w-3xl">
                  {/*content*/}
                  <div className="border-0 rounded-lg shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                    {/*header*/}
                    <div className="flex justify-center p-5 border-b border-solid border-slate-200 rounded-t">
                      <h3 className="text-3xl font-semibold">
                        Please Verify your Email
                      </h3>
                    </div>
                    {/*body*/}
                    <div className="relative p-6 flex-auto">
                      <div className="flex  items-center justify-center h-14 w-full my-4">
                        <input
                          type="text"
                          name="otpCode"
                          placeholder="Enter verify code"
                          value={otpCode}
                          onChange={handleChange}
                          className="h-10 w-50 border-2 border-black mt-2 px-2 py-2"
                        />
                      </div>
                    </div>
                    {/*footer*/}
                    <div className="flex items-center justify-end p-6 border-t border-solid border-slate-200 rounded-b">
                      <button
                        className="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                        type="button"
                        onClick={() => setShowModal(false)}
                      >
                        Close
                      </button>
                      <button
                        className="bg-emerald-500 text-white active:bg-emerald-600 font-bold uppercase text-sm px-6 py-3 rounded shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1 ease-linear transition-all duration-150"
                        type="button"
                        onClick={registerUser}
                      >
                        Register
                      </button>
                    </div>
                  </div>
                </div>
              </div>
              <div className="opacity-25 fixed inset-0 z-40 bg-black"></div>
            </>
          ) : null}

          <button
            onClick={reset}
            className="rounded text-white font-semibold bg-red-400 hover:bg-red-700 py-2 px-6"
          >
            Clear
          </button>
        </div>
      </div>
    </div>
  );
};

export default Register;
