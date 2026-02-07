import { useRef } from "react";
export default function CreateOperation() {
  const id = useRef();
  const name = useRef();
  const owner = useRef();

  async function SendData() {
    const Details = {
      bikeid: id.current.value,
      bikeName: name.current.value,
      bikeOwner: owner.current.value,
    };
    const response = await fetch("http://localhost:8091/getdata", {
      method: "POST",
      headers: { "Content-Type": "application/json",
      Authorization:`Bearer ${localStorage.getItem("token")}`
       },
      body: JSON.stringify(Details),
    });
    if (!response.ok) {
      console.log("data not send:");
    } else {
      console.log("data is send:");
    }
  }

  async function UpdateData() {
    const Details = {
      bikeid: id.current.value,
      bikeName: name.current.value,
      bikeOwner: owner.current.value,
    };
    const response = await fetch("http://localhost:8091/getdata", {
      method: "PUT",
      headers: { "Content-Type": "application/json" ,
      Authorization:`Bearer ${localStorage.getItem("token")}`
      },
      body: JSON.stringify(Details),
    });
    if (!response.ok) {
      console.log("data is not updated:");
    } else {
      console.log("Data is Udpated:");
    }
  }

  async function DeleteData() {
    const Details = {
      bikeid: id.current.value,
      bikeName: name.current.value,
      bikeOwner: owner.current.value,
    };
    const response = await fetch("http://localhost:8091/getdata", {
      method: "DELETE",
      headers: { "Content-Type": "application/json",
      Authorization:`Bearer ${localStorage.getItem("token")}`
       },
      body: JSON.stringify(Details),
    });
    if (!response.ok) {
      console.log("data is not Deleted:");
    } else {
      console.log("Data is Deleted:");
    }
  }

  return (
    <>
      <div className="border-4 border-red-400 bg-amber-300 text-black ">
        <div>
          Enter Bike id:
          <input type="number" className="bg-white" ref={id} />
        </div>
        <div>
          BikeName:
          <input type="text" className="bg-white mt-1.5" ref={name} />
        </div>
        <div>
          BikeOwner:
          <input type="text" className="bg-white mt-1.5" ref={owner} />
        </div>
        <div className="relative left-25">
          <button
            className="flex bg-red-300 rounded-md border-2 border-black mt-2 p-1"
            onClick={SendData}
          >
            Add data
          </button>
          <button
            className="flex bg-red-300 rounded-md border-2 border-black mt-2 p-1"
            onClick={UpdateData}
          >
            Update Data
          </button>{" "}
          <button
            className="flex bg-red-300 rounded-md border-2 border-black mt-2 p-1"
            onClick={DeleteData}
          >
            Delete Data
          </button>{" "}
        </div>
      </div>
    </>
  );
}
