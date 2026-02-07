import { useEffect } from "react";
import { useState } from "react";
export default function FetchedData({ Onchange }) {
  const [fetcheddata, setfetcheddata] = useState([]);
  const [Errormessage, setErrormessage] = useState(false);

  useEffect(() => {
    const fetchingdata = async () => {
      const response = await fetch("http://localhost:8091/getdata",{
        headers:{
          Authorization:`Bearer ${localStorage.getItem("token")}`
        }
      });
      if (!response.ok) {
        setErrormessage(true);
      } else {
        setErrormessage(false);
        const data = await response.json();
        setfetcheddata(data);
      }
    };
    fetchingdata();
  }, []);

  return (
    <>
      <div className="flex  bg-green-400 gap-1">
        {!Errormessage || fetcheddata !== 0
          ? fetcheddata.map((bikes, index) => (
              <div key={index}>
                <p>Id:{bikes.bikeid}</p>
                <p>BikeName:{bikes.bikeName}</p>
                <p>BikeOwner:{bikes.bikeOwner}</p>
              </div>
            ))
          : "data is not found:"}
      </div>
      <div>
        <button
          className="flex justify-center bg-red-300 rounded-md border-2 border-black mt-0.5 p-1"
          onClick={Onchange}
        >
          clear data
        </button>
      </div>
    </>
  );
}
