//can you understand my code? and rewrite it to improve quality
import React, { useEffect, useState } from "react";
import ReactPaginate from "react-paginate";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import EmployeeService from "../service/EmployeeService";
import Employee from "./Employee";

const EmployeeList = () => {
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [employees, setEmployees] = useState(null);
  const [currentPage, setCurrentPage] = useState(0);
  const [search, setSearch] = useState("");
  const [direction, setDirection] = useState("desc");
  const [sortBy, setSortBy] = useState("id");
  const [pageCount, setPageCount] = useState(0);

  useEffect(() => {
    //write code to refresh page
    
    if (localStorage.getItem("token") === null) {
      navigate("/login");
    }

    if (localStorage.getItem("isValidToken") < Date.now()) {
      const fetchNewToken = async () => {
        setLoading(true);
        try {
          const response = await axios.post(
            "http://localhost:8081/realms/keycloak_realm/protocol/openid-connect/token",
            {
              grant_type: "refresh_token",
              client_id: "client_employee",
              client_secret: "si70rp5V8VQXm5p0PP73LtOc9QxMzZBr",
              refresh_token: localStorage.getItem("refresh_token"),
            },
            {
              headers: {
                "Content-Type": "application/x-www-form-urlencoded",
              },
            }
          );
          if (response.status === 200) {
            localStorage.setItem(
              "isValidToken",
              Date.now() + response.data.expires_in * 1000
            );
            localStorage.setItem("token", response.data.access_token);
            localStorage.setItem("refresh_token", response.data.refresh_token);
          } else {
            console.log("error");
          }
        } catch (error) {
          console.log(error);
        }
        setLoading(false);
      };

      fetchNewToken();
    }

    const fetchData = async () => {
      // console.log(localStorage.getItem("token"));
      setLoading(true);
      try {
        const response = await EmployeeService.getEmployees(
          search,
          sortBy,
          currentPage,
          direction
        );
        if (response.status === 200) {
          setEmployees(response.data.content);
          setPageCount(response.data.totalPages);
          console.log(response);
        }
        // else {
          
        // }
      } catch (error) {
        console.log(error);
      }
      setLoading(false);
    };

    fetchData();
  }, []);

  const deleteEmployee = async (e, id) => {
    e.preventDefault();
    try {
      const response = await EmployeeService.deleteEmployee(id);
      // if (response.status === 200) {
      setEmployees((prevElement) => {
        return prevElement.filter((employee) => employee.id !== id);
      });
      //   });
      // } else {
      //       throw new Error("You do not have permission to delete this employee");
      // }
    } catch (error) {
      console.log(error);
      window.alert("You do not have permission to delete this employee");
    }
  };

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  const fetchData = async (search, sortBy, currentPage, direction) => {
    setLoading(true);
    try {
      const response = await EmployeeService.getEmployees(
        search,
        sortBy,
        currentPage,
        direction
      );
        console.log("==========" + response + "===========");
        setEmployees(response.data.content);
        setPageCount(response.data.totalPages);
        console.log(response.data);
        console.log("search value: " + search);
        console.log("sortBy value: " + sortBy);
        console.log("currentPage value: " + currentPage);
        console.log("direction value: " + direction);
    } catch (error) {
      window.alert(error.response.data.message);
      console.log(error.response.data.message);
    }
    setLoading(false);
  };

  const handleSearch = async (e) => {
    e.preventDefault();

    fetchData(search, sortBy, currentPage, direction);
  };

  const handlePageClick = (data) => {
    console.log(search);
    const page = data.selected;
    setCurrentPage(page);
    console.log(page);

    console.log(search + " and " + page + "is null");
    fetchData(search, sortBy, page, direction);
  };

  //when click sort by lastName
  const handleSort = (sort) => {
    setDirection((prevDirection) =>
      prevDirection === "desc" ? "asc" : "desc"
    );
    console.log(direction);
    fetchData(search, sort, currentPage, direction);
  };
  

  return (
    <div className="container mx-auto my-8">
      <div className="h-12">
        <button
          onClick={() => navigate("/addEmployee")}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold me-5"
        >
          Add Employee
        </button>
        <input
          type="text"
          name="firstName"
          value={search}
          placeholder="Search..."
          onChange={(e) => setSearch(e.target.value)}
          className="h-10 w-96 border mt-2 px-2 py-2"
        ></input>

        <button
          onClick={handleSearch}
          className="rounded bg-slate-600 text-white px-6 py-2 font-semibold me-5"
        >
          Search
        </button>
      </div>
      <div className="h-12 relative ">
        <button
          onClick={logout}
          className="right-0 top-0 absolute rounded bg-slate-600 text-white px-6 py-2 font-semibold"
        >
          Logout
        </button>
      </div>
      <div className="flex shadow border-b">
        <table className="min-w-full">
          <thead className="bg-gray-50">
            <tr>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Photo
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                First Name&nbsp;&nbsp;
                <button
                  className="bg-slate-400 cursor-pointer"
                  onClick={() => handleSort("firstName")}
                >{direction === "desc" ?
                  (<svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="w-6 h-6"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M12 19.5v-15m0 0l-6.75 6.75M12 4.5l6.75 6.75"
                    />
                  </svg>
                  ) : (
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m0 0l6.75-6.75M12 19.5l-6.75-6.75" />
                    </svg>)}
                </button>
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Last Name&nbsp;&nbsp;
                <button
                  className="bg-slate-400 cursor-pointer"
                  onClick={() => handleSort("lastName")}
                >{direction === "desc" ?
                  (<svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="w-6 h-6"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M12 19.5v-15m0 0l-6.75 6.75M12 4.5l6.75 6.75"
                    />
                  </svg>
                  ) : (
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m0 0l6.75-6.75M12 19.5l-6.75-6.75" />
                    </svg>)}




                </button>
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Phone Number&nbsp;&nbsp;
                <button
                  className="bg-slate-400 cursor-pointer"
                  onClick={() => handleSort("phoneNumber")}
                >{direction === "desc" ?
                  (<svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="w-6 h-6"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M12 19.5v-15m0 0l-6.75 6.75M12 4.5l6.75 6.75"
                    />
                  </svg>
                  ) : (
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m0 0l6.75-6.75M12 19.5l-6.75-6.75" />
                    </svg>)}
                </button>
              </th>
              <th className="text-left font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Email ID&nbsp;&nbsp;
                <button
                  className="bg-slate-400 cursor-pointer"
                  onClick={() => handleSort("email")}
                >{direction === "desc" ?
                  (<svg
                    xmlns="http://www.w3.org/2000/svg"
                    fill="none"
                    viewBox="0 0 24 24"
                    stroke-width="1.5"
                    stroke="currentColor"
                    class="w-6 h-6"
                  >
                    <path
                      stroke-linecap="round"
                      stroke-linejoin="round"
                      d="M12 19.5v-15m0 0l-6.75 6.75M12 4.5l6.75 6.75"
                    />
                  </svg>
                  ) : (
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round" d="M12 4.5v15m0 0l6.75-6.75M12 19.5l-6.75-6.75" />
                    </svg>)}
                </button>
              </th>
              <th className="text-right font-medium text-gray-500 uppercase tracking-wider py-3 px-6">
                Actions
              </th>
            </tr>
          </thead>
          {!loading && (
            <tbody className="bg-white">
              {employees.map((employee) => (
                <Employee
                  employee={employee}
                  deleteEmployee={deleteEmployee}
                  key={employee.id}
                ></Employee>
              ))}
            </tbody>
          )}
        </table>
      </div>

      <div className="h-12 relative">
        <ReactPaginate
          className="d-flex gap-2 right-0 top-0 absolute rounded bg-slate-600 text-white px-6 py-2 font-semibold"
          previousLabel={"← Previous"}
          nextLabel={"Next →"}
          breakLabel={"..."}
          pageCount={pageCount}
          marginPagesDisplayed={2}
          containerClassName={"pagination"}
          pageClassName={"page-item"}
          pageLinkClassName={"page-link"}
          previousClassName={"page-item"}
          previousLinkClassName={"page-link"}
          nextClassName={"page-item"}
          nextLinkClassName={"page-link"}
          activeClassName="active"
          onPageChange={handlePageClick}
        />
      </div>
    </div>
  );
};

export default EmployeeList;
