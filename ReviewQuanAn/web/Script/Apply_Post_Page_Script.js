/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("apply-post-form");
    const messageDiv = document.getElementById("message");

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const formData = new FormData(form);

        fetch(form.action, {
            method: form.method,
            body: formData
        })
                .then(response => {
                    if (response.ok) {
                        showMessage("Your post application has been submitted successfully!", "alert-success");
                    } else {
                        showMessage("There was an error submitting your application. Please try again.", "alert-danger");
                    }
                })
//                .catch(error => {
//                    showMessage("An error occurred. Please try again later.", "alert-danger");
//                })
                ;

    });

    function showMessage(message, alertClass) {
        messageDiv.textContent = message;
        messageDiv.className = `alert ${alertClass}`;
        messageDiv.style.display = "block";
    }
});