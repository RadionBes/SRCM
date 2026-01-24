import style from '/src/css/api/GroupCard.module.css'

export function GroupCard({id, name}) {
    return (
        <>
            <div key={id} className={`${style.group_card_main} ${style.style_text}`}>
                <div>{name}</div>
                <div></div>
            </div>
        </>
    )
}