import {useFetchGroups} from "../api/UseFetchGroups.js";
import {useState} from "react";
import {useFetchStudents} from "../api/UseFetchStudent.js";
import {Navigation} from "./navigation/Navigation.jsx";
import '/src/styles/default/_index_default.scss'

export function App() {
    const {groups, loadingGroup, errorGroup, fetchGroups} = useFetchGroups();
    const {students, loadingStudent, errorStudent, fetchStudents} = useFetchStudents();

    const [button, setButtonState] = useState("Undefine")

    const handleGroupsClick = () => {
        if (button !== "Groups") {
            fetchGroups().then(() => null);
        }
        setButtonState("Groups");
    };

    const handleStudentsClick = () => {
        if (button !== "Students") {
            fetchStudents().then(() => null);
        }
        setButtonState("Students");
    };

    return (
        <>
            <Navigation/>
        </>
    )
}