document.getElementById('comment-input').addEventListener('focus', function () {
    document.getElementById('cancel-button').style.display = 'inline-flex';
    document.getElementById('add-comment-button').style.display = 'inline-flex';
    document.getElementById('add-comment-button').disabled = true;
});

document.getElementById('comment-input').addEventListener('input', function () {
    const commentValue = document.getElementById('comment-input').value.trim();
    const addButton = document.getElementById('add-comment-button');

    addButton.disabled = commentValue === '';
});

document.getElementById('cancel-button').addEventListener('click', function () {
    document.getElementById('comment-input').value = '';
    document.getElementById('comment-input').style.height = '30px';
    document.getElementById('cancel-button').style.display = 'none';
    document.getElementById('add-comment-button').style.display = 'none';
    document.getElementById('add-comment-button').disabled = true;
});

document.getElementById('commentForm').addEventListener('submit', function () {
    event.preventDefault();

    var imgElement = document.getElementById("UserPP");
    var profPic = imgElement.src;
    var username = document.getElementById("Username").value;
    const commentInput = document.getElementById('comment-input');
    const commentValue = commentInput.value.trim();

    var blogId = document.getElementById("BlogId").value;
    if (!commentValue) {
        console.log("Comment is empty");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                var commentId = xhr.responseText.trim();

                console.log(xhr.status);
                postComment(username, profPic, commentId);
                updateCommentCount(1);
            } else {
                console.log(xhr.status);
            }
        }
    };

    xhr.send('service=addComment&comment-input=' + encodeURIComponent(commentValue));

});

function postComment(username, profPic, commentId) {
    const commentInput = document.getElementById('comment-input');
    const commentValue = commentInput.value.trim();

    const date = new Date();
    const formattedDate = timeAgo(date);

    if (commentValue) {
        const newComment = document.createElement('div');
        newComment.classList.add('comment');
        newComment.id = `comment-${commentId}`;
        newComment.innerHTML = `<div class="thumbnail">
                            <a class="toProfile">
                                <img src="${profPic}" alt="Profile Picture" class="profile-pic">
                            </a>
                        </div>
                        <div class="comment-body">
                            <p>
                                <a href="" class="profile-link">${username}</a>
                                <span class="comment-date">just now</span>
                            </p>
                            <p style="word-wrap: break-word;">
                                <span id="commentContent-${commentId}">${commentValue}</span>
                                <textarea class="editCommentInput" id="editCommentInput-${commentId}" style="display: none;"></textarea>
                            </p>
                            <div id="editCommentControls-${commentId}" style="display: none;">
                                <button class="save-button button" onclick="saveEditedComment(${commentId})">Save</button>
                                <button class="cancel-button button" onclick="cancelEditComment(${commentId})">Cancel</button>
                            </div>
                            <div class="comment-actions">
                                <input type="hidden" id="commentLikes-${commentId}" value="0">
                                <button class="rating" id="like-button-${commentId}" onclick="toggleCommentLike(${commentId})" aria-pressed="false"><i class="fa-regular fa-thumbs-up"></i></button>
                                <span id="likeCommentCount-${commentId}">0 likes</span>
                                <button class="rating" id="dislike-button-${commentId}" onclick="toggleCommentDislike(${commentId})" aria-pressed="false"><i class="fa-regular fa-thumbs-down"></i></button>
                            </div>
                            <div id="editOptionsDiv-${commentId}">
                                <button class="menu-btn"><i class="fa-solid fa-ellipsis-vertical"></i></button>
                                <ul class="menu-options" id="editOptions-${commentId}" style="display: none;">
                                    <li onclick="toggleEditComment(${commentId}, '${commentValue}')"><i class="fa-regular fa-pen-to-square"></i>Edit</li>
                                    <li onclick="deleteComment(${commentId})"><i class="fa-solid fa-trash"></i>Delete</li>
                                </ul>
                            </div>
</div>`;


        // Append the new comment to the comment list
        const commentList = document.querySelector('.comment-list');
        commentList.prepend(newComment);

        // Clear the input field and hide the buttons
        commentInput.value = '';
        document.getElementById('cancel-button').style.display = 'none';
        document.getElementById('add-comment-button').style.display = 'none';
    }
}


document.addEventListener('DOMContentLoaded', function () {
    const postImagesContainer = document.getElementById('post-images');
    const postImages = postImagesContainer.getElementsByClassName('post-image');

    if (postImages.length > 5) {
        for (let i = 5; i < postImages.length; i++) {
            postImages[i].style.display = 'none';
        }
        const remainingImages = postImages.length - 5;
        const fifthImage = postImages[4];

        const moreOverlayDiv = document.createElement('div');
        moreOverlayDiv.className = 'more-overlay';
        moreOverlayDiv.innerText = "+" + remainingImages;

        // Wrap the fourth image with a new div
        const wrapperDiv = document.createElement('div');
        wrapperDiv.className = 'post-image';
        wrapperDiv.style.position = 'relative';
        wrapperDiv.style.height = '100%';
        wrapperDiv.style.width = '100%';
        wrapperDiv.appendChild(fifthImage.cloneNode(true));
        wrapperDiv.appendChild(moreOverlayDiv);

        // Replace the original fourth image with the new wrapped div
        postImagesContainer.replaceChild(wrapperDiv, fifthImage);
    }
});

// Function to count the comments and update the span
function updateCommentCount(val) {
    const commentCount = document.getElementById('commentCounter').value;
    // Update the span with the new comment count
    document.getElementById('commentCount').innerHTML = `<i class="fa-solid fa-comment"></i> Comments: ${formatNumber(parseInt(commentCount) + val)}`;
}

function formatNumber(count) {
    if (count >= 1000000) {
        return (Math.floor(count / 100000) / 10).toFixed(1) + 'M';
    } else if (count >= 1000) {
        return (Math.floor(count / 100) / 10).toFixed(1) + 'K';
    } else {
        return count.toString();
    }
}
//format likes count of the blog
document.addEventListener("DOMContentLoaded", function () {
    const blogLikeCount = document.getElementById('likeCount');
    const likes = document.getElementById('blogLikes').value;
    const formattedLikes = formatNumber(likes);
    blogLikeCount.innerHTML = `<i class="fa-solid fa-thumbs-up"></i> Likes: ${formattedLikes}`;
});
//format likes count of the comments
document.addEventListener("DOMContentLoaded", function () {
    let allCommentLikes = document.querySelectorAll('[id^="commentLikes-"]');
    let allLikeCommentCount = document.querySelectorAll('[id^="likeCommentCount-"]');

    for (let i = 0; i < allCommentLikes.length; i++) {
        const likes = allCommentLikes[i].value;
        allLikeCommentCount[i].innerHTML = `${formatNumber(likes)} likes`;
    }
});

function toggleLike() {
    let likeBtn = document.getElementById("like-button");
    let dislikeBtn = document.getElementById("dislike-button");
    let likeCount = document.getElementById("likeCount");

    let blogLikes = document.getElementById("blogLikes");
    let likes = blogLikes.value;

    const isPressed = likeBtn.getAttribute('aria-pressed') === 'true';
    likeBtn.setAttribute('aria-pressed', !isPressed);

    let interactionType = likeBtn.getAttribute('aria-pressed') === 'false' ? 'nothing' : 'like';


    if (!isPressed && dislikeBtn.getAttribute('aria-pressed') === 'true') {
        dislikeBtn.setAttribute('aria-pressed', 'false');
        likes++;
    }

    isPressed ? likes-- : likes++;
    blogLikes.setAttribute("value", likes);
    likeCount.innerHTML = `<i class="fa-solid fa-thumbs-up"></i> Likes: ${formatNumber(likes)}`;

    sendLikeUpdate(interactionType, likes);
}

function toggleDislike() {
    let likeBtn = document.getElementById("like-button");
    let dislikeBtn = document.getElementById("dislike-button");
    let likeCount = document.getElementById("likeCount");

    let blogLikes = document.getElementById("blogLikes");
    let likes = blogLikes.value;

    const isPressed = dislikeBtn.getAttribute('aria-pressed') === 'true';
    dislikeBtn.setAttribute('aria-pressed', !isPressed);

    let interactionType = dislikeBtn.getAttribute('aria-pressed') === 'false' ? 'nothing' : 'dislike';

    if (!isPressed && likeBtn.getAttribute('aria-pressed') === 'true') {
        likeBtn.setAttribute('aria-pressed', 'false');
        likes--;
    }

    isPressed ? likes++ : likes--;
    blogLikes.setAttribute("value", likes);
    likeCount.innerHTML = `<i class="fa-solid fa-thumbs-up"></i> Likes: ${formatNumber(likes)}`;

    sendLikeUpdate(interactionType, likes);
}

function sendLikeUpdate(interactionType, blogLikes) {
    var blogId = document.getElementById("BlogId").value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status !== 200) {
                console.error("Failed to update like/dislike. Status: " + xhr.status);
            }
        }
    };

    xhr.send(`service=handlePostLikes&interactionType=${encodeURIComponent(interactionType)}&blogLikes=${encodeURIComponent(blogLikes)}`);
}

function toggleCommentLike(commentId) {
    let likeBtn = document.getElementById(`like-button-${commentId}`);
    let dislikeBtn = document.getElementById(`dislike-button-${commentId}`);
    let likeCount = document.getElementById(`likeCommentCount-${commentId}`);

    let commentLikes = document.getElementById(`commentLikes-${commentId}`);
    let likes = commentLikes.value;

    const isPressed = likeBtn.getAttribute('aria-pressed') === 'true';
    likeBtn.setAttribute('aria-pressed', !isPressed);

    let interactionType = likeBtn.getAttribute('aria-pressed') === 'false' ? 'nothing' : 'like';

    if (!isPressed && dislikeBtn.getAttribute('aria-pressed') === 'true') {
        dislikeBtn.setAttribute('aria-pressed', 'false');
        dislikeBtn.innerHTML = `<i class="fa-regular fa-thumbs-down"></i>`;
        likes++;
    }

    if (isPressed) {
        likes--;
        likeBtn.innerHTML = `<i class="fa-regular fa-thumbs-up"></i>`;
    } else {
        likes++;
        likeBtn.innerHTML = `<i class="fa-solid fa-thumbs-up"></i>`;
    }
    commentLikes.setAttribute("value", likes);
    likeCount.innerText = `${formatNumber(likes)} likes`;

    sendCommentLikeUpdate(interactionType, commentId, likes);
}

function toggleCommentDislike(commentId) {
    let likeBtn = document.getElementById(`like-button-${commentId}`);
    let dislikeBtn = document.getElementById(`dislike-button-${commentId}`);
    let likeCount = document.getElementById(`likeCommentCount-${commentId}`);

    let commentLikes = document.getElementById(`commentLikes-${commentId}`);
    let likes = commentLikes.value;

    const isPressed = dislikeBtn.getAttribute('aria-pressed') === 'true';
    dislikeBtn.setAttribute('aria-pressed', !isPressed);

    let interactionType = dislikeBtn.getAttribute('aria-pressed') === 'false' ? 'nothing' : 'dislike';

    if (!isPressed && likeBtn.getAttribute('aria-pressed') === 'true') {
        likeBtn.setAttribute('aria-pressed', 'false');
        likeBtn.innerHTML = `<i class="fa-regular fa-thumbs-up"></i>`;
        likes--;
    }

    if (isPressed) {
        likes++;
        dislikeBtn.innerHTML = `<i class="fa-regular fa-thumbs-down"></i>`;
    } else {
        likes--;
        dislikeBtn.innerHTML = `<i class="fa-solid fa-thumbs-down"></i>`;
    }
    commentLikes.setAttribute("value", likes);
    likeCount.innerText = `${formatNumber(likes)} likes`;

    sendCommentLikeUpdate(interactionType, commentId, likes);
}

function sendCommentLikeUpdate(interactionType, commentId, commentLikes) {
    var blogId = document.getElementById("BlogId").value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status !== 200) {
                console.error("Failed to update like/dislike. Status: " + xhr.status);
            }
        }
    };

    xhr.send(`service=handleCommentLikes&interactionType=${encodeURIComponent(interactionType)}&commentId=${encodeURIComponent(commentId)}&commentLikes=${encodeURIComponent(commentLikes)}`);
}

textarea = document.querySelector("#comment-input");
textarea.addEventListener('input', autoResize, false);


function autoResize() {
    this.style.height = 'auto';
    this.style.height = this.scrollHeight + 'px';
}

//date for blog
document.addEventListener("DOMContentLoaded", function () {
    const blogDate = document.getElementById('blogPublishDate');
    const dateString = blogDate.innerHTML;
    const date = new Date(dateString);
    const relativeTime = timeAgo(date);
    blogDate.innerHTML = relativeTime;
});

function timeAgo(date) {
    const now = new Date();
    const secondsPast = (now.getTime() - date.getTime()) / 1000;

    if (secondsPast < 60) {
        return `${Math.floor(secondsPast)} second${Math.floor(secondsPast) === 1 ? '' : 's'} ago`;
    }
    if (secondsPast < 3600) {
        const minutes = Math.floor(secondsPast / 60);
        return `${minutes} minute${minutes === 1 ? '' : 's'} ago`;
    }
    if (secondsPast < 86400) {
        const hours = Math.floor(secondsPast / 3600);
        return `${hours} hour${hours === 1 ? '' : 's'} ago`;
    }
    if (secondsPast < 172800) {
        return 'Yesterday';
    }
    if (secondsPast < 604800) {
        const days = Math.floor(secondsPast / 86400);
        return `${days} day${days === 1 ? '' : 's'} ago`;
    }
    if (secondsPast < 2592000) {
        const weeks = Math.floor(secondsPast / 604800);
        return `${weeks} week${weeks === 1 ? '' : 's'} ago`;
    }
    if (secondsPast < 31536000) {
        const months = Math.floor(secondsPast / 2592000);
        return `${months} month${months === 1 ? '' : 's'} ago`;
    }
    const years = Math.floor(secondsPast / 31536000);
    return `${years} year${years === 1 ? '' : 's'} ago`;
}

function sortComments() {
    var sortOption = document.getElementById("sort-comments").value;
    var blogId = document.getElementById("BlogId").value;
    var pageNum = document.getElementById("pageNumber").value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId + "&page=" + pageNum + "&sortOption=" + sortOption, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Update the comment list with the new sorted comments
                document.getElementById('comment-list').innerHTML = xhr.responseText;
            } else {
                console.error("Failed to switch the comment option. Status: " + xhr.status);
            }
        }
    };

    xhr.send(`sortOption=${sortOption}&blogId=${encodeURIComponent(blogId)}"&sortBy=${encodeURIComponent(sortOption)}`);
}

document.addEventListener('DOMContentLoaded', function () {
    document.body.addEventListener('click', function (event) {
        // Toggle menu options
        if (event.target.closest('.menu-btn')) {
            event.stopPropagation();
            const button = event.target.closest('.menu-btn');
            const options = button.nextElementSibling;

            // Close all other open menus
            document.querySelectorAll('.menu-options').forEach(menu => {
                if (menu !== options) {
                    menu.style.display = 'none';
                }
            });

            // Toggle current menu
            options.style.display = options.style.display === 'block' ? 'none' : 'block';

            // Check if the menu is within the viewport
            const rect = options.getBoundingClientRect();
            if (rect.bottom > window.innerHeight) {
                options.classList.add('above');
            } else {
                options.classList.remove('above');
            }
        } else {
            // Close all menus when clicking anywhere else on the page
            document.querySelectorAll('.menu-options').forEach(menu => {
                menu.style.display = 'none';
            });
        }

        const textareas = document.querySelectorAll('.editCommentInput');

        textareas.forEach(textarea => {
            textarea.addEventListener('input', autoResize, false);
        });
    });

    // Prevent menu options click from closing the menu
    document.body.addEventListener('click', function (event) {
        if (event.target.closest('.menu-options')) {
            event.stopPropagation();
        }
    });
});

function toggleEditComment(commentId, currentContent) {
    var commentContentSpan = document.getElementById('commentContent-' + commentId);
    var editCommentInput = document.getElementById('editCommentInput-' + commentId);
    var editCommentControls = document.getElementById('editCommentControls-' + commentId);
    var editOptionsDiv = document.getElementById('editOptionsDiv-' + commentId);

    // Toggle display of content and input
    commentContentSpan.style.display = 'none';
    editCommentInput.style.display = 'inline-block';
    editCommentInput.value = currentContent;

    // Hide edit options and show edit controls (textarea, save, cancel)
    editOptionsDiv.style.display = 'none';
    editCommentControls.style.display = 'block';
}

function saveEditedComment(commentId) {
    var blogId = document.getElementById("BlogId").value;
    var newContent = document.getElementById('editCommentInput-' + commentId).value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Update the comment content on successful edit
                document.getElementById('commentContent-' + commentId).innerText = newContent;
                // Hide edit controls after saving
                hideEditControls(commentId);
                var editOptionsDiv = document.getElementById('editOptionsDiv-' + commentId);
                editOptionsDiv.style.display = 'block';
            } else {
                console.error("Failed to edit comment. Status: " + xhr.status);
            }
        }
    };

    xhr.send(`service=editComment&commentId=${encodeURIComponent(commentId)}&newContent=${encodeURIComponent(newContent)}`);
}

function deleteComment(commentId) {
    var blogId = document.getElementById("BlogId").value;
    const commentCount = document.getElementById('commentCount').value;

    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'BlogPageController?id=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var commentElement = document.getElementById('comment-' + commentId);
                commentElement.parentNode.removeChild(commentElement);
                updateCommentCount(-1);
            } else
                console.error("Failed to delete comment. Status: " + xhr.status);
        }
    };

    xhr.send(`service=deleteComment&commentId=${encodeURIComponent(commentId)}`);
}

function hideEditOptions(commentId) {
    var editOptions = document.getElementById('editOptions-' + commentId);
    editOptions.style.display = 'none';
}

function cancelEditComment(commentId) {
    var commentContentSpan = document.getElementById('commentContent-' + commentId);
    var editCommentInput = document.getElementById('editCommentInput-' + commentId);
    var editCommentControls = document.getElementById('editCommentControls-' + commentId);
    var editOptionsDiv = document.getElementById('editOptionsDiv-' + commentId);

    // Toggle display of content and input
    commentContentSpan.style.display = 'inline-block';
    editCommentInput.style.display = 'none';
    editOptionsDiv.style.display = 'block';

    // Hide edit controls
    editCommentControls.style.display = 'none';
}

function hideEditControls(commentId) {
    var editCommentControls = document.getElementById('editCommentControls-' + commentId);
    editCommentControls.style.display = 'none';

    var editCommentInput = document.getElementById('editCommentInput-' + commentId);
    editCommentInput.style.display = 'none';

    // Display the comment content again
    var commentContentSpan = document.getElementById('commentContent-' + commentId);
    commentContentSpan.style.display = 'inline-block';
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
        slideIndex = 1;
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




// Bookmark toggling                     
//------------------------------------------------------------------------------
function toggleBookmark() {
    var blogId = document.getElementById("BlogId").value;
    var xhr = new XMLHttpRequest();
    var serviceType; // Declare serviceType variable

    xhr.open('POST', 'BookmarkController?blogId=' + blogId, true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Check and set serviceType based on current state
    var bookmarkButtonIcon = document.getElementById('bookmarkButtonIcon');
    var bookmarkButtonText = document.getElementById('bookmarkButtonText');

    if (bookmarkButtonIcon.classList.contains('fas')) {
        // Currently bookmarked, change to unbookmarked
        bookmarkButtonIcon.classList.remove('fas');
        bookmarkButtonIcon.classList.add('far');
        bookmarkButtonText.innerText = 'Bookmark';
        serviceType = "removeBookmark";
    } else {
        // Currently unbookmarked, change to bookmarked
        bookmarkButtonIcon.classList.remove('far');
        bookmarkButtonIcon.classList.add('fas');
        bookmarkButtonText.innerText = 'Unbookmark';
        serviceType = "addBookmark";
    }

    // Send AJAX request with serviceType
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                console.log('Bookmark operation successful');
                // Optionally update UI or handle success
            } else {
                console.error("Bookmark toggle failed. Status: " + xhr.status);
                // Optionally handle error case
            }
        }
    };
    xhr.send('service=' + serviceType);
}
//------------------------------------------------------------------------------      