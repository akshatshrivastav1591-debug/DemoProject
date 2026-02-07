import Buttons from "./Buttons";
import { useRef, useState } from "react";

export function RegistredUser() {
  const userName = useRef();
  const userPassword = useRef();
  const [registered, setregistesteres] = useState(false);

  async function NewUserRegistration() {
    const NewUSer = {
      ownername: userName.current.value,
      ownerpassword: userPassword.current.value,
    };
    const response = await fetch("http://localhost:8091/register", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(NewUSer),
    });

    if (response.ok) {
      setregistesteres(true);
      setTimeout(() => {
        setregistesteres(false);
      }, 5000);
    } else {
      console.log("something went wrong:");
    }
  }

  return (
    <>
      <div className="flex justify-center gap-1  border-2 border-black h-25">
        <div>
          <div className="flex">
            <input
              type="text"
              placeholder="enter username:"
              className="bg-amber-300"
              ref={userName}
            />
          </div>
          <div>
            <input
              type="text"
              placeholder="enter password:"
              className="relative top-1 bg-amber-300"
              ref={userPassword}
            />
            <div className="flex  gap-1 relative top-3 ">
              <div className=" ">
                <Buttons OnChange={NewUserRegistration}>Register</Buttons>
              </div>
              <div className="bg-pink-600">
                {registered ? "New User succuesfully registeres" : ""}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
