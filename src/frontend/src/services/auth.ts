export function getAuthHeader(){
    const userToken = localStorage.getItem('acess_token');
    if(userToken) {
        return {'Authorization': 'Bearer ' + userToken };
    } else {
        return {};
    }
}