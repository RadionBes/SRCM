import {Logo} from "./Logo.jsx";
import {list} from "/src/staticData/navigate.json"
import {NavigationItem} from "./NavigationItem.jsx";
import style from '/src/styles/navigate/Navigation.module.scss'

export function Navigation() {
    const navList = list.map(value => {
            return (
                <div key={value.id}>
                    <NavigationItem
                        label={value.label}
                        state={value.state}
                        onClick={(val) => {
                            console.log(val)
                        }}
                    />
                </div>
            )
        }
    )

    return (
        <div className={style.orientationFlex}>
            <Logo/>
            <div className={style.navOrientationFlex}>{navList}</div>
        </div>
    )
}