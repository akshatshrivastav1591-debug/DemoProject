import { useNavigate } from "react-router-dom";
import Buttons from "./Buttons";
import { useRef, useState } from "react";

function Login() {
  const userName = useRef();
  const userPassword = useRef();
  const [Unauthorize, SetUnauthorize] = useState(false);
  const navigate = useNavigate();
  async function AuthenticatingUser() {
    const UserDetails = {
      ownername: userName.current.value,
      ownerpassword: userPassword.current.value,
    };
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(UserDetails),
    });
    if (response.ok) {
      const JwtToken = await response.text();
      localStorage.setItem("token", JwtToken);

      navigate("/Home");
    } else {
      SetUnauthorize(true);
      setTimeout(() => {
        SetUnauthorize(false);
      }, 5000);
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
              <div>
                <Buttons OnChange={AuthenticatingUser}>Login</Buttons>
              </div>

              <div className="bg-red-600">
                {Unauthorize
                  ? "usernot found or some error is ocurred:"
                  : undefined}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
export default Login;
