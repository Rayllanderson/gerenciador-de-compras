import {FiAlertCircle, FiCheckCircle, FiInfo, FiXCircle} from "react-icons/fi";
import {useContext, useEffect} from 'react';
import {ToastContext, ToastMessage} from "../../contexts/ToastContext";
import {ThemeContext} from "styled-components";
import {cyan} from "../../utils/colorsUtil";

interface Props {
    type?: string;
    message: ToastMessage;
}

export default function Toast({message}: Props) {
    const {colors} = useContext(ThemeContext)
    const {removeToast} = useContext(ToastContext);
    let background = colors.backgroundSecondary;
    let color = cyan;
    const icons = {
        info: <FiInfo size={20}/>,
        success: <FiCheckCircle size={20}/>,
        error: <FiAlertCircle size={20}/>,
    }
    switch (message.type) {
        case 'success':
            color = 'var(--green)';
            break;
        case 'error':
            color = 'var(--red)';
            break;
    }

    useEffect(() => {
        const timer = setTimeout(() => {
            removeToast(message.id)
        }, 4000)
        return () => {
            clearTimeout(timer)
        };
    }, [removeToast, message.id])

    return (
        <div className="toast-element toast-right" style={{background: background, color: color}}>
            {icons[message.type || 'info']}
            <div>
                <strong> {message.title} </strong>
                <p>{message.description}</p>
            </div>
            <button onClick={() => removeToast(message.id)}>
                <FiXCircle size={18}/>
            </button>
        </div>
    )
}