import style from '/src/styles/navigate/NavigationItem.module.scss'

export function NavigationItem(props){
    return(
        <li
            className={style.navItem}
            onClick={props.onClick(props.state)}
        >
            <p className={style.text}>{props.label}</p>
        </li>
    )
}