import { useState, useRef } from "react";

export default function SingleFecthedData({ OnChange }) {
  const CurrentValue = useRef();
  const [Data, SetData] = useState({
    bikeid: 0,
    bikeName: "",
    bikeOwner: "",
  });
  const [message, setmessage] = useState("");

  async function SingleFetchedBike() {
    const Bikeid = CurrentValue.current.value;
    const response = await fetch(
      `http://localhost:8091/getSingleData/${Bikeid}`,{
        headers:{
          Authorization:`Bearer ${localStorage.getItem("token")}`
        }
      }
    );
    if (!response.ok) {
      setmessage("data not found:");
    } else {
      const SingleFetchedBike = await response.json();
      SetData({
        bikeid: SingleFetchedBike.bikeid,
        bikeName: SingleFetchedBike.bikeName,
        bikeOwner: SingleFetchedBike.bikeOwner,
      });
    }
  }

  return (
    <>
      <div>
        <input type="number" ref={CurrentValue} />
      </div>

      <div className="bg-yellow-600">
        {message === "" ? (
          <div>
            <p>Bikeid:{Data.bikeid}</p>
            <p>BikeName:{Data.bikeName}</p>
            <p>BikeOwner:{Data.bikeOwner}</p>
          </div>
        ) : (
          message
        )}
      </div>
      <div>
        <button
          onClick={SingleFetchedBike}
          className="flex bg-red-300 rounded-md border-2 border-black mt-0.5 p-1"
        >
          saw data
        </button>
      </div>
      <div>
        <button
          onClick={OnChange}
          className="flex bg-red-300 rounded-md border-2 border-black mt-0.5 p-1"
        >
          Clear data
        </button>
      </div>
    </>
  );
}
