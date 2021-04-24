import {UserLoginBody, UserRegisterBody} from "../interfaces/userInterface";

export function validateLogin(user: UserLoginBody) {
    return new Promise<string>(function (resolve, reject) {
        const hasNoUsername = user.username.length < 1;
        const hasNoPassword = user.username.length < 1;

        if (hasNoUsername) {
            reject({message: 'Username é obrigatório'});
        }
        if (hasNoPassword) {
            reject({message: 'Senha é obrigatória'});
        }

        const MIN_CHARACTER_LIMIT: number = 3;
        const usernameIsInvalid = user.username.length < MIN_CHARACTER_LIMIT;
        const passwordIsInvalid = user.password.length < MIN_CHARACTER_LIMIT;

        if (usernameIsInvalid) {
            reject({message: 'Username precisa ter, no mínimo, ' + MIN_CHARACTER_LIMIT + ' caracteres.'});
        }

        if (passwordIsInvalid) {
            reject({message: 'Senha precisa ter, no mínimo, ' + MIN_CHARACTER_LIMIT + ' caracteres.'});
        }

        const success = !hasNoPassword && !hasNoUsername && !usernameIsInvalid && !passwordIsInvalid;
        if (success){
            resolve('All fields has been successful validated.');
        }
    });
}

export function validateRegister(user: UserRegisterBody) {
    const userToBeValidate: UserLoginBody = {
        username: user.username,
        password: user.password
    }
    return validateLogin(userToBeValidate);
}