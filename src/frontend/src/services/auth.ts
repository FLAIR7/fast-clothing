export function getAuthHeader(){
    const userToken = localStorage.getItem('@FastCloth:acess_token');
    if(userToken) {
        return userToken;
    } else {
        return {};
    }
}