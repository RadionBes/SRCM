import {LeftAria} from "./mainView/LeftAria.jsx";
import {MainListGroup} from "./mainView/MainListGroup.jsx";
import style from '/src/css/App.module.css'
import {useFetchGroups} from "../api/UseFetchGroups.js";
import {useState} from "react";

export function App() {
    const {groups, loading, error, fetchGroups} = useFetchGroups();

    const [button, setButtonState] = useState("Undefine")

    return (
        <div className={style.app_container}>
            <LeftAria
                onGroupsClick={fetchGroups}
                groups={groups} loading={loading} error={error}
                valueClick={button} onSetValueState={setButtonState}
            />

            {
                button === "Groups" && (
                <>
                    <MainListGroup groups={groups} loading={loading} error={error}/>
                </>
                )
            }


        </div>
    )
}