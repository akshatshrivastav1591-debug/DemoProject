import { RouterProvider } from "react-router-dom";
import { createBrowserRouter } from "react-router-dom";
import Login from "./assets/Components/LoginForm";
import HomePage from "./assets/Components/Home";
import RootElement from "./assets/Components/Root";
import { RegistredUser } from "./assets/Components/Register";

function App() {
  const DefindedRoutes = createBrowserRouter([
    {
      path: "/",
      errorElement: <h1>Something went wrong</h1>,
      element: <RootElement />,
      children: [
        { path: "/Home", element: <HomePage /> },
        { path: "/login", element: <Login /> },
        { path: "/register", element: <RegistredUser /> },
      ],
    },
  ]);

  return (
    <>
      <RouterProvider router={DefindedRoutes} />
    </>
  );
}
export default App;
