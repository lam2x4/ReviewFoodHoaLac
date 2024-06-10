/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


window.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('apply-post-form');

    const postTitle = document.getElementById('postTitle');
    const postDescription = document.getElementById('postDescription');

    const billUpload = document.getElementById('billUpload');
    const billPreview = document.getElementById('billPreview');

    const imageUpload = document.getElementById('imageUpload');
    const imagePreview = document.getElementById('imagePreview');

    const clearButton = form.querySelector('input[type="reset"]');

    function showMessage(message, alertClass) {
        const messageDiv = document.getElementById("message");
        if (message !== null) {
            messageDiv.textContent = message;
            messageDiv.className = `alert ${alertClass}`;
            messageDiv.style.display = "flex";
        } else {
            messageDiv.style.display = "none";
        }
    }

    // Load data from session storage if available
    if (sessionStorage.getItem('formData')) {
        const formData = JSON.parse(sessionStorage.getItem('formData'));
        postTitle.value = formData.postTitle;
        postDescription.value = formData.postDescription;
    }

    // Save form data to session storage whenever there's a change in form fields
    form.addEventListener('input', () => {
        const formData = {
            postTitle: postTitle.value,
            postDescription: postDescription.value
        };
        sessionStorage.setItem('formData', JSON.stringify(formData));
    });

    if (clearButton) {
        clearButton.addEventListener('click', () => {
            clear(true);
        });
    }
    function clear(clearAlert) {
        if (clearAlert) {
           showMessage(null);
        }

        // Clear session storage for form data
        sessionStorage.removeItem('formData');
        form.reset();
        
        // Clear previews
        imagePreview.innerHTML = '';
        billPreview.innerHTML = '';
    }

    function handleFilePreview(fileInput, previewContainer) {
        previewContainer.innerHTML = ''; // Clear existing previews

        const files = fileInput.files;
        if (files) {
            for (let i = 0; i < files.length; i++) {
                const file = files[i];
                if (file) {
                    const reader = new FileReader();
                    reader.onload = (e) => {
                        const img = document.createElement('img');
                        img.src = e.target.result;
                        img.style.maxWidth = '200px'; // Limit the width to avoid stretching
                        img.style.margin = '5px'; // Add margin around each image
                        previewContainer.appendChild(img);
                    };
                    reader.readAsDataURL(file);
                }
            }
        }
        previewContainer.style.display = 'flex'; // Display the preview container
    }

    if (billUpload) {
        billUpload.addEventListener('change', () => {
            handleFilePreview(billUpload, billPreview);
        });
    } else {
        console.error("Element with ID 'billUpload' not found.");
    }

    if (imageUpload) {
        imageUpload.addEventListener('change', () => {
            handleFilePreview(imageUpload, imagePreview);
        });
    } else {
        console.error("Element with ID 'imageUpload' not found.");
    }

    $("textarea").each(function () {
        // Store the original height of the textarea
        this.originalHeight = this.scrollHeight;
        this.style.overflowY = 'hidden';
        this.style.resize = 'none'; // Disable manual resizing
    }).on("input", function () {
        // If scrollHeight exceeds the clientHeight, adjust the height and show scrollbar
        if (this.scrollHeight > this.clientHeight) {
            this.style.height = 0;
            this.style.height = (this.scrollHeight) + "px";
        } else {
            this.style.height = this.originalHeight + 'px'; // Restore original height
        }
    });

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
                        clear(false);
                    } else {
                        showMessage("There was an error submitting your application. Please try again.", "alert-danger");
                    }
                })
//                .catch(error => {
//                    showMessage("An error occurred. Please try again later.", "alert-danger");
//                })
                ;
    });
});