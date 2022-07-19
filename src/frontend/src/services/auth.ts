export function getAuthHeader(){
    const userToken = localStorage.getItem('@FastCloth:auth_token');
    if(userToken) {
        return userToken;
    } else {
        return {};
    }
}