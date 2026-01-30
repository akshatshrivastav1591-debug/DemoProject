import Buttons from "./Buttons.jsx";
import { useState } from "react";
import FetchedData from "./FetchedData.jsx";
import SingleFecthedData from "./SingleFetchedData.jsx";
import CreateOperation from "./SendingData.jsx";

function HomePage(){
    const [value, setvalue] = useState({
    Alldata: "",
    Singledata: "",
  });
  function Showdata(Operation) {
    if (Operation === "Alldata") {
      setvalue({
        Alldata: "Alldata",
        Singledata: "",
      });
    }

    if (Operation === "Singledata") {
      setvalue({
        Alldata: "",
        Singledata: "Singledata",
      });
    }
    if (Operation === "Cleardata") {
      setvalue({
        Alldata: "",
        Singledata: "",
      });
    }
  }

  return (
    <>
      <div className="flex justify-center bg-red-500">
        <h3>welcome to the world of react and spring:</h3>
      </div>
      <div className="flex justify-center gap-0.5">
        <Buttons OnChange={() => Showdata("Alldata")}>Show Alldata</Buttons>
        <Buttons OnChange={() => Showdata("Singledata")}>
          Show single data
        </Buttons>
      </div>
      <div className="flex justify-center">
        {value.Alldata === "Alldata" ? (
          <FetchedData Onchange={() => Showdata("Cleardata")} />
        ) : (
          "press the button to fetched data:"
        )}
      </div>

      <div className="flex justify-center">
        {value.Singledata === "Singledata" ? (
          <SingleFecthedData OnChange={() => Showdata("Cleardata")} />
        ) : (
          "press to fetched the data of a single bike:"
        )}
      </div>
      <div className="flex justify-center mt-2 bg-blue-600 border-2 border-black text-amber-500">
        <p>Enter the data to send it in the backend:</p>

        <div className="mt-6 relative right-66">
          <CreateOperation />
        </div>
      </div>
    </>
  );
}
export default HomePage;
    
