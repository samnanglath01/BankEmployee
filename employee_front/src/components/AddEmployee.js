import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../service/EmployeeService";

const AddEmployee = () => {
  const navigate = useNavigate();

  useEffect(() => {
    if (localStorage.getItem("token") === null) {
      navigate("/login");
    }
  }, [navigate]);

  const [employee, setEmployee] = useState({
    id: "",
    file: null,
    firstName: "",
    lastName: "",
    phoneNumber: "",
    email: "",
  });

  const handleChange = (e) => {
    const { name, type, value, files } = e.target;
    if (type === "text") {
      setEmployee({ ...employee, [name]: value });
    } else if (type === "file") {
      setEmployee({ ...employee, file: files[0] });
    } else if (type === "email") {
      setEmployee({ ...employee, [name]: value });
    } else if (type === "tel") {
      setEmployee({ ...employee, [name]: value });
    }
  };

  const saveEmployee = (e) => {
    e.preventDefault();
    const data = new FormData();
    data.append("file", employee.file);
    data.append("firstName", employee.firstName);
    data.append("lastName", employee.lastName);
    data.append("phoneNumber", employee.phoneNumber);
    data.append("email", employee.email);

    console.log(data.get("firstName"));

    EmployeeService.saveEmployee(data)
      .then((response) => {
        console.log(response);
        navigate("/");
      })
      .catch((error) => {
        const { data } = error.response;
        window.alert(
          `First Name: ${data.firstName}\n\nLast Name: ${data.lastName}\n\nPhone Number: ${data.phoneNumber}\n\nEmail: ${data.email}`
        );
        console.log(error);
      });
  };

  const reset = (e) => {
    e.preventDefault();
    setEmployee({
      id: "",
      file: null,
      firstName: "",
      lastName: "",
      phoneNumber: "",
      email: "",
    });
  };

  return (
    <div className="flex max-w-2xl mx-auto shadow border-b">
      <div className="px-8 py-8">
        <div className="font-thin text-2xl tracking-wider">
          <h1>Add New Employee</h1>
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Photo
          </label>
          <input
            type="file"
            name="file"
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            First Name
          </label>
          <input
            type="text"
            name="firstName"
            value={employee.firstName}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Last Name
          </label>
          <input
            type="text"
            name="lastName"
            value={employee.lastName}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Phone Number
          </label>
          <input
            type="tel"
            name="phoneNumber"
            value={employee.phoneNumber}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>
        <div className="items-center justify-center h-14 w-full my-4">
          <label className="block text-gray-600 text-sm font-normal">
            Email
          </label>
          <input
            type="text"
            name="email"
            value={employee.email}
            onChange={handleChange}
            className="h-10 w-96 border mt-2 px-2 py-2"
          />
        </div>

        <div className="items-center justify-center h-14 w-full my-4 space-x-4 pt-4">
          <button
            onClick={saveEmployee}
            className="rounded text-white font-semibold bg-green-400 hover:bg-green-700 py-2 px-6"
          >
            Save
          </button>
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

export default AddEmployee;
