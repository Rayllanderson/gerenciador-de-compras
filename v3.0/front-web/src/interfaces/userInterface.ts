interface UserModel {
    username: string;
    password: string;
}

export interface UserLoginBody extends UserModel {

}

export interface UserRegisterBody extends UserModel{
    name: string;
}

export interface UserResponseBody {
    id: string,
    name: string,
    username: string,
    base64: string
}

export interface UserPutBody {
    name: string,
    username: string,
    email: string
}