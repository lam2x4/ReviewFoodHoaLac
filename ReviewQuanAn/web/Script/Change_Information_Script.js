/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('change-information-form');

    const avatarUpload = document.getElementById('avatarUpload');
    const avatarPreview = document.getElementById('avatarPreview');

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

    if (avatarUpload) {
        avatarUpload.addEventListener('change', () => {
            handleFilePreview(avatarUpload, avatarPreview);
        });
    } else {
        console.error("Element with ID 'avatarUpload' not found.");
    }
});