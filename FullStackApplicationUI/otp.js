const apiBaseURL = "http://localhost:8080"; 

const otpForm = document.getElementById("otpForm");

otpForm.addEventListener("submit", async (event) => {
  event.preventDefault(); 

  const otp = document.getElementById("otp").value;

  if (!otp) {
    alert("Please enter the OTP.");
    return;
  }

  try {

    const response = await fetch(`${apiBaseURL}/verifyOtp`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        otp: otp,
      }),
    });

    const result = await response.text();

    if (response.ok) {
      alert(result); 
      window.location.href = "dashboard.html";
    } else {
      alert(result || "Invalid OTP. Please try again.");
    }
  } catch (error) {
    console.error("Error:", error);
    alert("An error occurred. Please try again.");
  }
});
