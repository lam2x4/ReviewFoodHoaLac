document.getElementById('comment-input').addEventListener('focus', function () {
    document.getElementById('cancel-button').style.display = 'inline-flex';
    document.getElementById('add-comment-button').style.display = 'inline-flex';
});

document.getElementById('cancel-button').addEventListener('click', function () {
    document.getElementById('cancel-button').style.display = 'none';
    document.getElementById('add-comment-button').style.display = 'none';
});

function postComment(username, profPic) {
    const commentInput = document.getElementById('comment-input');
    const commentValue = commentInput.value.trim();
    
    const date = new Date();
    let day = date.getDate();
    let month = date.getMonth() + 1;
    let year = date.getFullYear();
    
    let currentDate = `${day}/${month}/${year}`;

    if (commentValue) {
        const newComment = document.createElement('div');
        newComment.classList.add('comment');
        newComment.innerHTML = `
            <div class="thumbnail">
                <a class="toProfile">
                    <img src="${profPic}" alt="Profile Picture" class="profile-pic">
                </a>
            </div>
            <div class="comment-body">
                <p><a href="" class="profile-link">${username}</a> ${currentDate}</p>
                <p>${commentValue}</p>
                <div class="comment-actions">
                    <button class="rating"><i class="fa-regular fa-thumbs-up"></i></button>   
                    0 likes   
                    <button class="reply-button" onclick="showReplyInput(this, '${username}', '${profPic}')">Reply</button>
                </div>
                <div class="replies"></div>
            </div>
        `;

        // Append the new comment to the comment list
        const commentList = document.querySelector('.comment-list');
        commentList.prepend(newComment);

        // Clear the input field and hide the buttons
        commentInput.value = '';
        document.getElementById('cancel-button').style.display = 'none';
        document.getElementById('add-comment-button').style.display = 'none';

        updateCommentCount();
    }
}

document.getElementById('commentButton').addEventListener('click', function () {
    document.getElementById('comment-box').scrollIntoView({behavior: 'smooth'});
    document.getElementById('comment-input').focus();
});

document.addEventListener('DOMContentLoaded', function () {
    const postImagesContainer = document.getElementById('post-images');
    const postImages = postImagesContainer.getElementsByClassName('post-image');

    if (postImages.length > 4) {
        for (let i = 4; i < postImages.length; i++) {
            postImages[i].style.display = 'none';
        }
        const remainingImages = postImages.length - 4;
        const fourthImage = postImages[3];

        const moreOverlayDiv = document.createElement('div');
        moreOverlayDiv.className = 'more-overlay';
        moreOverlayDiv.innerText = "+" + remainingImages;

        // Wrap the fourth image with a new div
        const wrapperDiv = document.createElement('div');
        wrapperDiv.className = 'post-image';
        wrapperDiv.style.position = 'relative';
        wrapperDiv.style.height = '100%';
        wrapperDiv.style.width = '100%';
        wrapperDiv.appendChild(fourthImage.cloneNode(true));
        wrapperDiv.appendChild(moreOverlayDiv);

        // Replace the original fourth image with the new wrapped div
        postImagesContainer.replaceChild(wrapperDiv, fourthImage);
    }
});

function showReplyInput(replyBtn, username, profPic) {
    // Check if reply input already exists
    let existingInput = replyBtn.parentElement.nextElementSibling;
    if (existingInput && existingInput.classList.contains('reply-section')) {
        existingInput.style.display = 'inline';
        return;
    }

    // Create a new reply input element
    const replyInput = document.createElement('div');
    replyInput.classList.add('reply-section');
    replyInput.id = "reply-section";
    replyInput.innerHTML = `<img src="${profPic}" alt="Profile Picture" class="profile-pic">
                            <input type="text" name="add-reply" id="comment-input" class="glowing-input" placeholder="Add a reply...">
                            <div class="buttons">
                                <button class="button" onclick="cancelReply(this)">Cancel</button>
                                <button class="button" onclick="postReply(this, '${username}', '${profPic}')">Reply</button>
                            </div>`;

    // Insert the reply input element after the comment actions
    replyBtn.parentElement.parentElement.appendChild(replyInput);
}

function cancelReply(cancelButton) {
    // Hide the reply input form
    cancelButton.closest('.reply-section').style.display = 'none';
}

function postReply(replyButton, username, profPic) {
    const replyInputValue = replyButton.closest('.reply-section').querySelector('input').value;

    // Check if the input is empty
    if (!replyInputValue.trim())
        return;

    // Create a new reply element
    const reply = document.createElement('div');
    reply.classList.add('comment', 'reply');
    reply.innerHTML = `
        <div class="thumbnail">
            <a class="toProfile">
                <img src="${profPic}" alt="Profile Picture" class="profile-pic">
            </a>
        </div>
        <div class="comment-body">
            <p><a href="" class="profile-link">${username}</a></p>
            <p>${replyInputValue}</p>
            <div class="comment-actions">
                <button class="rating"><i class="fa-regular fa-thumbs-up"></i></button>   
                0 likes   
                <button class="reply-button" onclick="showReplyInput(this, '${username}', '${profPic}')">Reply</button>
            </div>
            <div class="replies"></div>
        </div>
    `;

    // Find the replies container and prepend the new reply
    const repliesContainer = replyButton.closest('.comment-body').querySelector('.replies');
    repliesContainer.prepend(reply);

    // Clear and hide the reply input
    replyButton.closest('.reply-section').querySelector('input').value = '';
    replyButton.closest('.reply-section').style.display = 'none';
}

document.getElementById('commentButton').addEventListener('click', function () {
    document.getElementById('comment-box').scrollIntoView({behavior: 'smooth'});
    document.getElementById('comment-input').focus();
});

// Function to count the comments and update the span
function updateCommentCount() {
    // Select the comment list
    const commentList = document.querySelector('.comment-list');

    // Count the number of comments (excluding replies)
    const comments = commentList.querySelectorAll('.comment:not(.reply)');
    const commentCount = comments.length;

    // Update the span with the new comment count
    document.getElementById('commentCount').textContent = `Comments: ${commentCount}`;
}

function toggleLike() {
  let likeBtn = document.getElementById("like-button");
  let likeCount = document.getElementById("likeCount");
  
  let bloglikes = parseInt(likeCount.innerText.split(': ')[1]);

  const isPressed = likeBtn.getAttribute('aria-pressed') === 'true';
  likeBtn.setAttribute('aria-pressed', !isPressed);
  
  isPressed ? bloglikes-- : bloglikes++;
  likeCount.innerHTML = `<i class="fa-solid fa-thumbs-up"></i> Likes: ${bloglikes}`;
}

function toggleCommentLike(){
    
}

//Light box
//------------------------------------------------------------------------------
function openModal() {
    document.getElementById("myModal").style.display = "block";
}

function closeModal() {
    document.getElementById("myModal").style.display = "none";
}

var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}

function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("demo");
    var captionText = document.getElementById("caption");
    if (n > slides.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = slides.length;
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(" active", "");
    }
    slides[slideIndex - 1].style.display = "block";
    dots[slideIndex - 1].className += " active";
    captionText.innerHTML = dots[slideIndex - 1].alt;
}
//------------------------------------------------------------------------------
