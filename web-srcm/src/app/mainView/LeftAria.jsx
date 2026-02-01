import nav from '/src/staticData/navigate.json'
import style from '/src/css/mainView/LeftAria.module.css'

export function LeftAria({ onGroupsClick }) {
    const nav_list = nav.list.map((value, index) => {
            if (value.label === 'Группы') {
                return (
                    <li className={style.nav_element}
                        key={index}
                        onClick={onGroupsClick}
                    >{value.label}</li>
                )
            } else {
                return(
                    <li className={style.nav_element} key={index}>{value.label}</li>
                )
            }

        }
    );
    return (
        <aside className={style.roboto}>
            <nav className={style.nav_bar}>
                <ul>
                    {nav_list}
                </ul>
            </nav>
        </aside>
    )
}