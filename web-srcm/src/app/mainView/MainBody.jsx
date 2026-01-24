import style from '/src/css/mainView/MainBody.module.css'
import {useFetchGroups} from '/src/api/UseFetchGroups.js'
import {GroupCard} from "./GroupCard.jsx";

export function MainBody(){
    const listGroups = useFetchGroups().groups.map((value) =>
        <GroupCard id={value.id} name={value.name}/>
    );

    return(
        <main>
            <div className={`${style.groups_container}`}>
                {listGroups}
            </div>
        </main>
    )
}