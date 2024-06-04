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
        messageDiv.innerHTML = 'Email or Phone number already been registered!';
        messageDiv.style.display = 'block';
    } else if (status === '2') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Account creation failed. Email or phone number might already been taken.';
        messageDiv.style.display = 'block';
    } else if (status === '3') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Something went wrong! Internal Error 2)';
        messageDiv.style.display = 'block';
    } else if (status === '4') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Register new account or back to login if you registered but have not verified!';
        messageDiv.style.display = 'block';
    } else if (status === '5') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Something went wrong! (Internal Error 2)';
        messageDiv.style.display = 'block';
    } else if (status === '6') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Could not find user in the database';
        messageDiv.style.display = 'block';
    }
};
function validatePassword(password) {
    const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    return pattern.test(password);
}