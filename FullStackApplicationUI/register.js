const apiBaseURL = "http://localhost:8080";

if (document.getElementById("registerForm")) {
  document
    .getElementById("registerForm")
    .addEventListener("submit", async (e) => {
      e.preventDefault();
      const username = document.getElementById("username").value;
      const email = document.getElementById("email").value;
      const password = document.getElementById("password").value;
      const dateOfBirth = document.getElementById("date").value;
      const profilePic = document.getElementById("profilePic").value;
      const cv = document.getElementById("cv").value;
      const role = document.getElementById("role").value;
      const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

      if (!emailRegex.test(email)) {
        alert("Please enter a valid email address.");
        event.preventDefault(); // Prevent form submission if validation fails
        return;
      }
      const user = {
        name: username,
        email: email,
        password: password,
        dateOfBirth: dateOfBirth, 
        profilePic : profilePic,
        cv:cv,
        role: role.toUpperCase()
      };

      try {
        const response = await fetch(`${apiBaseURL}/register`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(user), 
        });

        const result = await response.text();

        if (response.ok) {
          alert("Registration successful");
          localStorage.setItem("registeredEmail", email);
           window.location.href = "login.html";
        } else {
          alert(result || "Registration failed.");
        }
      } catch (error) {
        console.error("Error:", error);
        alert("An error occurred. Please try again.");
      }
    });
}

if (document.getElementById("otpForm")) {
  document.getElementById("otpForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const otp = document.getElementById("otp").value;
    
    const email = localStorage.getItem("registeredEmail");

    try {
      const response = await fetch(`${apiBaseURL}/verify-otp`, {
        method: "POST",

        headers: { "Content-Type": "application/json" },

        body: JSON.stringify({ email, otp }),
      });

      const result = await response.json();

      if (response.ok) {
        alert("OTP verified successfully.");

        window.location.href = "login.html";
      } else {
        alert(result.message || "Invalid OTP.");
      }
    } catch (error) {
      console.error("Error:", error);

      alert("An error occurred. Please try again.");
    }
  });
}