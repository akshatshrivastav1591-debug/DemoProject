import { Link, Outlet } from "react-router-dom";

function RootElement() {
  return (
    <>
      <div className=" flex flex-col justify-center items-center gap-1 bg-amber-300">
        <div className="bg-red-400 w-full flex justify-center">
          <h1>Wecome to the Root Page</h1>
        </div>
        <div className="bg-green-400 flex justify-center w-full">
          <h2>
            click here to go to login page:
           <Link to={"/login"}>Login</Link>
          </h2>
        </div>

        <div className="bg-blue-400 flex justify-center w-full">
          <h2>
            click here to go to Register page:
           <Link to={"/register"}>Register here</Link>
          </h2>
        </div>
        <Outlet />
      </div>
    </>
  );
}
export default RootElement;
