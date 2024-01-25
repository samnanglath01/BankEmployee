const expiresInSeconds = 36000; // The expires_in value from the token response
const tokenExpirationTimestamp = Date.now() + expiresInSeconds * 1000; // Convert expires_in to milliseconds and calculate the expiration timestamp
const currentTime = Date.now(); // Get the current timestamp

class ValidateToken{
    
    checkToken(){
        if (currentTime >= tokenExpirationTimestamp) {
            console.log('Token has expired.');
        } else {
            console.log('Token is still valid.');
        }
    }

}