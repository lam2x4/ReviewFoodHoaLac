/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');
    const messageDiv = document.getElementById('message');

    if (status === '1') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Verify code is incorrect!';
        messageDiv.style.display = 'block';
    } else if (status === '2') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Verify code resent!';
        messageDiv.style.display = 'block';
    } else if (status === '3') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Could not resend email!';
        messageDiv.style.display = 'block';
    } else if (status === '4') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'Something went wrong. Try again';
        messageDiv.style.display = 'block';
    } else if (status === '5') {
        messageDiv.className = 'alert alert-success';
        messageDiv.innerHTML = 'User is null!';
        messageDiv.style.display = 'block';
    }
};

