import {LeftAria} from "./mainView/LeftAria.jsx";
import {MainBody} from "./mainView/MainBody.jsx";
import style from '/src/css/App.module.css'

export function App() {
    return (
        <div className={style.app_container}>
            <LeftAria/>
            <MainBody/>
        </div>
    )
}