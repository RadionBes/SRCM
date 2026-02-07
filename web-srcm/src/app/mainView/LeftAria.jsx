import nav from '/src/staticData/navigate.json'
import style from '/src/css/mainView/LeftAria.module.css'

export function LeftAria(props) {
    const nav_list = nav.list.map((value, index) => {
            if (value.label === 'Группы') {
                return (
                    <li className={style.nav_element}
                        key={index}
                    >
                        <button onClick={() => {
                            props.onGroupsClick();
                            props.onSetValueState(value.state)
                        }
                        }>{value.label}</button>
                    </li>
                )
            } else {
                return (
                    <li className={style.nav_element} key={index}>
                        <button onClick={
                            () => {
                                props.onGroupsClick();
                                props.onSetValueState(value.state)
                            }
                        }>{value.label}</button>
                    </li>
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