import React from "react";
import { useNavigate } from "react-router-dom";

const Employee = ({ employee, deleteEmployee }) => {
  const navigate = useNavigate();

  const editEmployee = (e, id) => {
    e.preventDefault();
    navigate(`/updateEmployee/${id}`);
  };

  return (
    <tr key={employee.id}>
      <td className="text-left px-6 py-4 whitespace-nowrap">
      <img src={`data:image/jpeg;base64,${employee.imagePath}`} alt="photo" className="cursor-pointer object-fit bg-yellow-300 w-16 h-16 rounded-full border-3 border-indigo-600" />
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{employee.firstName}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{employee.lastName}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{employee.phoneNumber}</div>
      </td>
      <td className="text-left px-6 py-4 whitespace-nowrap">
        <div className="text-sm text-gray-500">{employee.email}</div>
      </td>
      <td className="text-right px-6 py-4 whitespace-nowrap font-medium text-sm">
        <a
          onClick={(e, id) => editEmployee(e, employee.id)}
          className="text-indigo-600 hover:text-indigo-800 px-4 hover:cursor-pointer">
          Edit
        </a>
        <a
          onClick={(e, id) => deleteEmployee(e, employee.id)}
          className="text-indigo-600 hover:text-indigo-800 hover:cursor-pointer">
          Delete
        </a>
      </td>
    </tr>
  );
};

export default Employee;