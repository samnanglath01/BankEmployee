import axios from "axios"

const EMPLOYEE_API_BASE_URL = "http://localhost:8080/employees"

const headers = {
    headers:{
        'Authorization': "Bearer " + localStorage.getItem("token")
    }
}


class EmployeeService{
    
    saveEmployee(employee){
        return axios.post(EMPLOYEE_API_BASE_URL, employee, headers);
    }

    getEmployees(search, sortBy, page, direction){
        return axios.get(`http://localhost:8080/employees/search?search=${search}&sortBy=${sortBy}&page=${page}&size=5&direction=${direction}` ,
        headers);
    }

    deleteEmployee(id){
        return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id, headers)
    }

    // deleteEmployee(id, headers) {
    //     return axios.delete(EMPLOYEE_API_BASE_URL + "/" + id, { headers });
    // }
    

    getEmployeeById(id){
        return axios.get(EMPLOYEE_API_BASE_URL + "/" + id, headers)
    }

    updateEmployee(id, employee){
        return axios.put(EMPLOYEE_API_BASE_URL + "/" + id, employee, headers)
    }

    searchEmployee(name, page){
        return axios.get(`http://localhost:8080/employees/search?search=${name}&page=${page}&size=5`, headers)
    }


}
export default new EmployeeService();