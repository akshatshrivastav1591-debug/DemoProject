export default function Buttons({children,OnChange}){
    return(
        <>
        <div>
            <button  className="flex bg-red-300 rounded-md border-2 border-black mt-0.5 p-1" onClick={OnChange}>{children}</button>
        </div>
        </>
    )
}