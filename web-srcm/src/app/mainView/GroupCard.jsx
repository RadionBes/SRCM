import style from '/src/css/api/GroupCard.module.css'

export function GroupCard({value}) {
    const {id,name,year} = value
    return (
        <>
            <div key={id} className={`${style.group_card_main} ${style.header_text_style}`}>
                <div>{name}</div>
                <div className={style.description_text_style}>{year}</div>
            </div>
        </>
    )
}