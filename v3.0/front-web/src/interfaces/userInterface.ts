interface UserModel {
    username: string;
    password: string;
}

export interface UserLoginBody extends UserModel {

}

export interface UserRegisterBody extends UserModel{
    name: string;
}