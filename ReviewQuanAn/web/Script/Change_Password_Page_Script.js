function validatePasswords() {
    var newPassword = document.getElementById("newPassword").value;
    var confirmPassword = document.getElementById("confirmPassword").value;
    var message = document.getElementById("message1");
    if (newPassword !== confirmPassword) {
        document.getElementById("submitForm").disabled = true;
        document.getElementById("sendCodeButton").disabled = true;
        message.style.display = "block";
        message.innerHTML = "Passwords do not match!";
        return false;
    } else if (newPassword === confirmPassword && newPassword !== null) {
        document.getElementById("submitForm").disabled = false;
        document.getElementById("sendCodeButton").disabled = false;
        message.style.display = "block";
        message.innerHTML = "Passwords matched, continue to verify";
        return true;
    }
}

function sendCode() {
    // Function to handle sending the verification code
    document.getElementById("sendCodeButton").disabled = true;

}
window.onload = function () {
    document.getElementById("sendCodeButton").disabled = true;
    document.getElementById("message1").style.display = "block";
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');
    const messageDiv = document.getElementById('message');

    if (status === '1') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Could not find your email in our database!';
        messageDiv.style.display = 'block';
    } else if (status === '2') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Could not send verify code to your email!';
        messageDiv.style.display = 'block';
    } else if (status === '3') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Password changed!';
        messageDiv.style.display = 'block';
    } else if (status === '4') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Something went wrong when changing your password!';
        messageDiv.style.display = 'block';
    } else if (status === '5') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Incorect Verify Code!';
        messageDiv.style.display = 'block';
    } else if (status === '7') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Password changed!';
        messageDiv.style.display = 'block';
    } else if (status === '8') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Could not change pasword (update step)!';
        messageDiv.style.display = 'block';
    } else if (status === '9') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'User null or password null!';
        messageDiv.style.display = 'block';
    }
};
document.getElementById('sendCodeButton').addEventListener('click', function () {
    var email = document.getElementById('email').value;
    document.getElementById("sendCodeButton").disabled = false;
    // Ensure the email field is not empty before sending the request
    if (!email) {
        document.getElementById('message1').innerText = 'Please enter your email address first.';
        document.getElementById("sendCodeButton").disabled = false;
        return;
    }

    // Create the XMLHttpRequest object
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'SendVerifyCode', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Handle the response from the server
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.status);
                document.getElementById('message1').innerText = 'Verification code sent to your email.';
            } else if (xhr.status === 1) {
                console.log(xhr.status);
                document.getElementById('message1').innerText = 'Could not find your email in the database.';
                return;
            } else {
                console.log(xhr.status);
                document.getElementById('message1').innerText = 'Failed to send verification code.';
            }
        }
        document.getElementById("sendCodeButton").disabled = false;
    };

    // Send the request with the email parameter
    xhr.send('service=sendVerificationCode&email=' + encodeURIComponent(email));
});

//======================================================================================================
//CHECK BEFORE SUBMIT
document.getElementById('passwordForm').addEventListener('submit', function (event) {

    var form = document.getElementById("passwordForm");
    var email = document.getElementById('email').value;
    var messageElement = document.getElementById('message1'); // Changed ID
    document.getElementById("sendCodeButton").disabled = true;
    if (messageElement.innerHTML !== 'Verification code is correct.') {
        event.preventDefault();
    } else {
        document.getElementById("sendCodeButton").disabled = false;
        return;
    }

    if (!email) {
        messageElement.innerText = 'Please enter your email address first.';
        document.getElementById("sendCodeButton").disabled = true;
        return;
    }
    if (!validatePasswords()) {
        messageElement.innerText = 'Passwords are not similar';
        document.getElementById("submitForm").disabled = true;
        return;
    }

    var code = document.getElementById("verifyCode").value;
    var pass = document.getElementById("newPassword").value;
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'ChangePassword', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                console.log(xhr.status);
                messageElement.innerText = 'Verification code is correct.';
                form.submit();
            } else {
                console.log(xhr.status);
                // Handle other status codes appropriately
                messageElement.innerHTML = 'Verification code is incorrect.';
            }
        }
        document.getElementById("sendCodeButton").disabled = false;
    };

    xhr.send('service=checkCode&code=' + encodeURIComponent(code) + '&pass=' + encodeURIComponent(pass));
});
//DONE!
//========================================================================================================


//========================================================================================================
//INFOMATION POPUP
document.querySelector('.icon').addEventListener('click', function () {
      const popup = document.getElementById('passwordPopup');
      if (popup.style.display === 'none' || popup.style.display === '') {
        popup.style.display = 'block';
      } else {
        popup.style.display = 'none';
      }
    });
//DONE
//========================================================================================================    
