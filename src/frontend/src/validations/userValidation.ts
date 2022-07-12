import { UserPostRequest} from "../types/types";

export function validateLogin(user: UserPostRequest) {
    return new Promise<string>(function (resolve, reject) {
        const hasNoEmail = user.email.length < 1;
        const hasNoPassword = user.password.length < 1;

        if(hasNoEmail) {
            reject({message: 'Email is necessary'});
        }

        if(hasNoPassword) {
            reject({message: 'Password is necessary'});
        }

        const MIN_CHARACTER_LIMIT = 8;
        const passwordIsInvalid = user.password.length < MIN_CHARACTER_LIMIT;

        if(passwordIsInvalid) {
            reject({message: 'password must have ' + MIN_CHARACTER_LIMIT + ' characters'});
        }

        const success = !hasNoEmail && !hasNoPassword && !passwordIsInvalid;
        if(success) {
            resolve('All fields has been successful validated');
        }
    });
}

export function validateRegister(user: UserPostRequest) {
    const userToBeValidate: UserPostRequest = {
        email: user.email,
        password: user.password
    }
    return validateLogin(userToBeValidate);
}