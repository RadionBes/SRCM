import {LeftAria} from "./mainView/LeftAria.jsx";
import {MainListGroup} from "./mainView/MainListGroup.jsx";
import style from '/src/css/App.module.css'
import {useFetchGroups} from "../api/UseFetchGroups.js";

export function App() {
    const { groups, loading, error, fetchGroups } = useFetchGroups();


    return (
        <div className={style.app_container}>
            <LeftAria onGroupsClick={fetchGroups} groups={groups} loading={loading} error={error} />
            <MainListGroup groups={groups} loading={loading} error={error} />
        </div>
    )
}