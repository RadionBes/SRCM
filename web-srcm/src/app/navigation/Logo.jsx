import {logoAppName} from '/src/staticData/navigate.json'
import {logotype} from '/src/staticData/navigate.json'
import style from '/src/styles/navigate/Logo.module.scss'

export function Logo(){
    return(
        <div className={style.mainBlock}>
            <img className={style.logo} src={logotype} alt={logoAppName}/>
            <p className={style.title}>{logoAppName}</p>
        </div>
    )
}