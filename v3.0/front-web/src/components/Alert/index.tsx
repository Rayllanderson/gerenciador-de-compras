import {Alert} from 'react-bootstrap'
import {useContext} from "react";
import {AlertContext} from "../../contexts/AlertContext";
import {Container} from './styles';
import {FiAlertTriangle} from "react-icons/all";

export default function MyAlert() {
    const {show, closeAlert, message} = useContext(AlertContext);
    return (
        <Container>
            <Alert show={show} className="alert-dismissible alert" transition={false}>
                <strong>
                    <FiAlertTriangle/> {message}
                </strong>
                <button type="button" className="btn-close" onClick={closeAlert} aria-label="Close"/>
            </Alert>
        </Container>
    );
}