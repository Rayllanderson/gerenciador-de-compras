import React, {createContext, ReactNode, useCallback, useContext, useState} from 'react';
import {UserResponseBody} from "../interfaces/userInterface";
import UserController from "../controllers/userController";
import {validateField} from "../validations/userValidation";
import {ToastContext} from "./ToastContext";
import {AlertContext} from "./AlertContext";
import {ModalContext} from "./ModalContext";

interface AccountContextProviderProps {
    children: ReactNode;
}

interface AccountContextContextData {
    user: UserResponseBody,
    name: string,
    username: string,
    password: string,
    hasChangedImage: boolean,
    fetchUser: () => void;
    update: () => void;
    updatePassword: () => void;
    clearPassword: () => void;
    uploadFile: () => void;
    setName: (value: string) => void;
    setUsername: (value: string) => void;
    handleNameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    handleUsernameChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    handlePasswordChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
    handleImageChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

export const AccountContext = createContext<AccountContextContextData>({} as AccountContextContextData);

export function AccountProvider({children}: AccountContextProviderProps) {

    const [user, setUser] = useState<UserResponseBody>({} as UserResponseBody);
    const [name, setName] = useState<string>('');
    const [username, setUsername] = useState<string>('');
    const [password, setPassword] = useState<string>('');
    const [photo, setPhoto] = useState<string | Blob>();
    const [hasChangedImage, setHasChangedImage] = useState<boolean>(false)

    const {addToast} = useContext(ToastContext);
    const {addAlert} = useContext(AlertContext);
    const {
        closeChangeDataModal,
        closeChangePasswordModal,
        openPreviewPhotoModal,
        closePreviewPhotoModal
    } = useContext(ModalContext);

    const fetchUser = useCallback(async () => {
        await new UserController().fetchUser().then((response) => {
            setUser(response.data);
        }).catch((err) => console.log(err))
    }, [setUser])

    const handleNameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setName(e.target.value);
    }, [])

    const handleUsernameChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setUsername(e.target.value);
    }, [])

    const handlePasswordChange = useCallback((e: React.ChangeEvent<HTMLInputElement>) => {
        setPassword(e.target.value);
    }, [])

    const clearPassword = useCallback(() => setPassword(''), []);

    const update = useCallback(() => {
        validateField(username, 'Username').then(async () => {
            await new UserController().updateData({username: username, name: name, email: ''})
                .then(() => {
                    addToast({
                        type: 'success',
                        title: 'Pronto',
                        description: 'Seus dados foram alterados!'
                    })
                    fetchUser();
                    closeChangeDataModal();
                }).catch(err => {
                    addAlert(err.message) // tratar
                })
        }).catch(err => {
            addAlert(err.message)
        })
    }, [addToast, fetchUser, name, username, addAlert, closeChangeDataModal])

    const updatePassword = useCallback(() => {
        validateField(password, 'Senha').then(async () => {
            await new UserController().updatePassword(password)
                .then(() => {
                    addToast({
                        type: 'success',
                        title: 'Pronto',
                        description: 'Sua Senha foi alterada!'
                    })
                    closeChangePasswordModal();
                }).catch(err => {
                    addAlert(err.message) // tratar
                })
        }).catch(err => {
            addAlert(err.message)
        })
    }, [addToast, password, addAlert, closeChangePasswordModal])

    const handleImageChange = useCallback(async (e: any) => {
        const file = e.target.files[0];
        if (!!file) {
            setPhoto(file);
            await openPreviewPhotoModal();
            const target = document.getElementById('image');
            const previewPhoto = () => {
                let reader = new FileReader();
                reader.onloadend = function () {
                    // @ts-ignore
                    target.src = reader.result;
                };
                reader.readAsDataURL(file)
            }
            previewPhoto();
        }
    }, [openPreviewPhotoModal])

    const uploadFile = useCallback(() => {
        const data = new FormData();
        data.append('file', photo as string | Blob);
        new UserController().uploadPhoto(data)
            .then(() => {
                closePreviewPhotoModal();
                addToast({
                    type: 'success',
                    title: 'Pronto',
                    description: 'Sua foto foi alterada!'
                })
                fetchUser().then();
                setHasChangedImage(i => !i);
                // @ts-ignore
                document.getElementById('profileImage').click(); //pra sumir o card de 'upload e remove'
            }).catch(err => addAlert(err.message)) // tratar erro
    }, [photo, closePreviewPhotoModal, addToast, fetchUser, addAlert])

    return (
        <AccountContext.Provider value={{
            fetchUser, user, handleUsernameChange, handleNameChange, name, username, setUsername, setName,
            handlePasswordChange, password, update, updatePassword, clearPassword, handleImageChange, uploadFile, hasChangedImage
        }}>
            {children}
        </AccountContext.Provider>
    )
}

