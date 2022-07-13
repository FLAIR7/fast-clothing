import { useContext, useEffect } from "react";
import {FiAlertCircle, FiCheckCircle, FiInfo, FiXCircle} from "react-icons/fi";
import { ToastContext, ToastMessage } from "../../contexts/ToastContext";
import "./toast.css";

interface Props {
    type?: string;
    message: ToastMessage;
}

export default function Toast({message}: Props) {
    const {removeToast} = useContext(ToastContext);

    const icons = {
        info: <FiInfo size={20}/>,
        success: <FiCheckCircle size={20}/>,
        error: <FiAlertCircle size={20}/>,
    }

    useEffect(() => {
        const timer = setTimeout(() => {
            removeToast(message.id);
        }, 4000)
        return () => {
            clearTimeout(timer);
        };
    }, [removeToast, message.id])

    return (
        <div className="toast-element toast-right">
            {icons[message.type || 'info']}
            <div>
                <strong>{message.type}</strong>
                <p>{message.description}</p>
            </div>
            <button onClick={() => removeToast(message.id)}>
                <FiXCircle size={18}/>
            </button>
        </div>
    )

}