import { ToastMessage } from "../../contexts/ToastContext";
import Toast from "./Toast";

interface Props {
    messages: ToastMessage[];
}

export default function ToastContainer({messages}: Props){
    return (
        <div className="toastContainer">
            {messages.map((message) => (
                <Toast message={message} key={message.id}/>
            ))}
        </div>
    );
}