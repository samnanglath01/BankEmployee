import { BrowserRouter, Routes, Route } from 'react-router-dom';
import './App.css';
import AddEmployee from './components/AddEmployee';
import Navbar from './components/Navbar';
import EmployeeList from './components/EmployeeList';
import UpdateEmployee from './components/UpdateEmployee';
import Login from './components/Login';
import Register from './components/Register';

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path='/register' element={<Register/>}/>
        <Route path='/login' element={<Login/>}/>

        <Route path="/" element={<EmployeeList/>}/>

        <Route path='/addEmployee' element={<AddEmployee/>}/>
        
        <Route path='/updateEmployee/:id' element={<UpdateEmployee/>}/>
      </Routes>
    </BrowserRouter>
    

  );
}

export default App;
