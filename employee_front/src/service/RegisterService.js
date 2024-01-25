import axios from "axios";

const REGISTER_URL = "http://localhost:8080/verify-account/"
class RegisterService {

    verifyEmail(emailId) {
        console.log(emailId);
        console.log("http://localhost:8080/verify-account/" + emailId)
        return axios.post("http://localhost:8080/verify-account/" + emailId);
    }

    registerUser(otpCode, user) {
        console.log(otpCode);
        console.log(user)
        console.log("http://localhost:8080/keycloak/register/" + otpCode)
        return axios.post("http://localhost:8080/keycloak/register/" + otpCode, user);
    }

}

export default new RegisterService();