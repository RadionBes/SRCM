import style from '/src/css/mainView/MainBody.module.css'
import {GroupCard} from "./GroupCard.jsx";

export function MainListGroup(props){
    const list = props.groups.map((value) =>
        <GroupCard id={value.id} value={value}/>
    );

    return(
        <main>
            <div className={`${style.groups_container}`}>
                {list}
            </div>
        </main>
    )
}