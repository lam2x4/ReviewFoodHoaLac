window.onload = function () {
    const urlParams = new URLSearchParams(window.location.search);
    const status = urlParams.get('status');
    const messageDiv = document.getElementById('message');

    if (status === '1') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Account or Password is incorrect!';
        messageDiv.style.display = 'block';
    } else if (status === '2') {
        messageDiv.className = 'alert alert-danger';
        messageDiv.innerHTML = 'Something went wrong!';
        messageDiv.style.display = 'block';
    }
};
