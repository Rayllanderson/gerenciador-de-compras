import './toast.css'
import Toast from "./Toast";
import {ToastMessage} from "../../context/ToastContext";

interface Props {
  messages: ToastMessage[];
}
export default function ToastContainer({ messages }: Props) {
  return (
    <div className="toastContainer">
      {messages.map((message) => (
        <Toast message={message} key={message.id}/>
      ))}

    </div>
  );
}
