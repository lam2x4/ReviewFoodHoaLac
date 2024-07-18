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

//    const editorConfigs = ['postDescription', 'postTitle'];
//    editorConfigs.forEach(function (id) {
//        var editor = CKEDITOR.replace(id);
//        editor.on('required', function (evt) {
//            editor.showNotification('This field is required.', 'warning');
//            evt.cancel();
//        });
//    });

    const editorConfigs = [
        {id: 'postTitle', config: 'basic'},
        {id: 'postDescription', config: 'standard'}
    ];

    editorConfigs.forEach(function (item) {
        let editor;
        if (item.config === 'basic') {
            editor = CKEDITOR.replace(item.id, {toolbar: 'Basic'});
        } else if (item.config === 'standard') {
            editor = CKEDITOR.replace(item.id, {toolbar: 'Full'});
        }
        editor.on('required', function (evt) {
            editor.showNotification('This field is required.', 'warning');
            evt.cancel();
        });
    });

    CKEDITOR.editorConfig = function (config) {
        config.versionCheck = false; // Disable version check
    };

    CKEDITOR.config.toolbar_Basic = [
        //{name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo']},
        //{name: 'editing', items: ['Find', 'Replace', '-', 'SelectAll', '-', 'Scayt']},
        {name: 'forms', items: ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField']},
        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript', '-', 'RemoveFormat']},
        {name: 'document', items: ['Source', '-', 'NewPage', 'Preview', 'Print', '-', 'Templates']},
        //{name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize']},
        {name: 'tools', items: ['Maximize']}
    ];

    CKEDITOR.config.toolbar_Full = [
        //{name: 'clipboard', items: ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-', 'Undo', 'Redo']},
        //{name: 'editing', items: ['Find', 'Replace', '-', 'SelectAll', '-', 'Scayt']},
        {name: 'forms', items: ['Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 'HiddenField']},
        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript', 'Superscript', '-', 'RemoveFormat']},
        {name: 'paragraph', items: ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', '-', 'Blockquote', 'CreateDiv', '-', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
        {name: 'document', items: ['Source', '-', 'NewPage', 'Preview', 'Print', '-', 'Templates']},

        '/',
        {name: 'links', items: ['Link', 'Unlink', 'Anchor']},
        {name: 'insert', items: ['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak']},
        {name: 'styles', items: ['Styles', 'Format', 'Font', 'FontSize']},
        {name: 'tools', items: ['Maximize']}
    ];

    //Custom form reset
    form.addEventListener("reset", () => {
        reset();
    });

    function reset() {
        // Clear session storage for form data
        /* global CKEDITOR */
        for (let instance in CKEDITOR.instances) {
            CKEDITOR.instances[instance].setData('');
        }

        imageUpload.value = '';
        billUpload.value = '';

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
                        img.classList.add('preview'); // Add 'preview' class to the image
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
});