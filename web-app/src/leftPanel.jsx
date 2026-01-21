import style_panel from './css/leftPanel.module.css'
import roboto_style from './css/textStyle/robotoStyle.module.css'
import navigationData  from '../src/staticData/navigation.json'

export function LeftPanel(){
    const listItems = navigationData.navigation_items.map((item, index) =>
        <li
            key={index}
            className={style_panel.textCenter}
        >
            {item}
        </li>
    )

    return(
        <>
            <aside className={style_panel.leftAside}>
                <ul className={roboto_style.roboto_nav}>
                    {listItems}
                </ul>
            </aside>
        </>
    )
}