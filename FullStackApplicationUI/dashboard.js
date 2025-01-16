// Backend API URL
const apiBaseURL = "http://localhost:8080";

// Function to show modal
function showModal(modalId) {
  document.getElementById(modalId).style.display = "block";
}

// Function to close modal
function closeModal(modalId) {
  document.getElementById(modalId).style.display = "none";
}

// Event listener for Create Customer button
document.getElementById("createCustomer").addEventListener("click", () => {
  showModal("createCustomerModal");
});

// Event listener for closing the Create Customer modal
document.getElementById("closeCustomerModal").addEventListener("click", () => {
  closeModal("createCustomerModal");
});
document.getElementById("createService").addEventListener("click", () => {
  showModal("createServiceModal");
});

// Event listener for closing the Create Service modal
document.getElementById("closeModal").addEventListener("click", () => {
  closeModal("createServiceModal");
});

// Event listener for Create Order button
document.getElementById("createOrder").addEventListener("click", () => {
  showModal("createOrderModal");
});

// Event listener for closing the Create Order modal
document.getElementById("closeOrderModal").addEventListener("click", () => {
  closeModal("createOrderModal");
});

const servicesInput = document.getElementById("services");
 
// Ensure that Choices.js is initialized on the multi-select dropdown
const choices = new Choices(servicesInput, {
  removeItemButton: true, // Allow removal of selected items
  maxItemCount: 5, // Limit the number of items that can be selected
  searchEnabled: true, // Enable searching for services
  itemSelectText: "Click to select services", // Tooltip text for selection
});
 
// Event listener for the Create Order form submission
document.getElementById("orderForm").addEventListener("submit", async (e) => {
  e.preventDefault(); // Prevent default form submission

  // Get form data
  const orderId = document.getElementById("orderId").value;
  const customerId = document.getElementById("customerId").value;
  const services = document.getElementById("services").value;
  const totalBill = document.getElementById("totalBill").value;
  const message = document.getElementById("message").value;

  const order = {
    orderId,
    customerId,
    services,
    totalBill: parseFloat(totalBill),
    message,
  };

  try {
    // Make the API call to create the order
    const response = await fetch(`${apiBaseURL}/createOrder`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(order),
    });

    const result = await response.json();

    if (response.ok) {
      alert("Order created successfully!");
      closeModal("createOrderModal"); // Close the modal after successful submission
    } else {
      alert(result.message || "Failed to create order.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("An error occurred. Please try again.");
  }
});

// Event listener for the customer form submission
document.getElementById("customerForm").addEventListener("submit", async (e) => {
  e.preventDefault(); // Prevent default form submission

  // Get form data
  const customerName = document.getElementById("customerName").value;
  const customerMobile = document.getElementById("customerMobile").value;
  const customerEmail = document.getElementById("customerEmail").value;

  const customer = {
    name: customerName,
    mobile: customerMobile,
    email: customerEmail,
  };

  try {
    // Make the API call to register the customer
    const response = await fetch(`${apiBaseURL}/createCustomer`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(customer),
    });

    const result = await response.json();

    if (response.ok) {
      alert("Customer created successfully!");
      closeModal("createCustomerModal"); // Close the modal after successful submission
    } else {
      alert(result.message || "Failed to create customer.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("An error occurred. Please try again.");
  }
});

// Function for exporting all users to PDF
async function exportAllUsersToPDF() {
  try {
    const response = await fetch(`${apiBaseURL}/exportAllUser`, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });
    if (!response.ok) {
      throw new Error("Failed to export user data.");
    }

    const pdfBlob = await response.blob();

    const link = document.createElement("a");
    const url = URL.createObjectURL(pdfBlob);
    link.href = url;
    link.download = "users.pdf";
    document.body.appendChild(link);
    link.click();

    document.body.removeChild(link);
  } catch (error) {
    console.error("Error fetching user data:", error);
    alert("Failed to export user data. Please try again.");
  }
}

document.getElementById("exportData").addEventListener("click", exportAllUsersToPDF);

//add service
document.getElementById("serviceForm").addEventListener("submit", async (e) => {
  e.preventDefault(); // Prevent default form submission

  // Get form data
  const name = document.getElementById("serviceName").value;
  const categoryImage = document.getElementById("serviceImage").value;
  const status = document.getElementById("serviceStatus").value;

  const servicesCategory = {
    name: name,
    categoryImage: categoryImage,
    status: status,
  };

  try {
    const response = await fetch(`${apiBaseURL}/createServices`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(servicesCategory),
    });

    const result = await response.json();

    if (response.ok) {
      alert("Service created successfully!");
      closeModal("createServiceModal"); 
    } else {
      alert(result.message || "Failed to create customer.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("An error occurred. Please try again.");
  }
});

document.getElementById("orderForm").addEventListener("submit", async (e) => {
  e.preventDefault(); 

  const customerId = document.getElementById("customerId").value;
  const userId = document.getElementById("userId").value;
const selectedServices = choices.getValue(true)
  const totalBill = document.getElementById("totalBill").value;
  const message = document.getElementById("message").value;

  const orderRequest ={
    customerId: customerId,
    userId : userId,
     serviceIds: selectedServices,
    totalBill: totalBill,
    message: message
  }

  try {
    const response = await fetch(`${apiBaseURL}/createOrder`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(orderRequest),
    });

    if (response.ok) {
      alert("Order created successfully!");
      closeModal("createOrderModal"); 
    } else {
      alert(result.message || "Failed to create order.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("An error occurred. Please try again.");
  }
});
