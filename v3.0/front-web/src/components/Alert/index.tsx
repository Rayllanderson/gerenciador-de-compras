import {Alert} from 'react-bootstrap'
import {useContext} from "react";
import {AlertContext} from "../../context/AlertContext";


export default function MyAlert() {
    const {show, closeAlert, message} = useContext(AlertContext);
    return (
        <Alert show={show} variant="danger" className="alert-dismissible alert" transition={false}>
            <strong>
                {message}
            </strong>
            <button type="button" className="btn-close" onClick={closeAlert} aria-label="Close"/>
        </Alert>
    );
}