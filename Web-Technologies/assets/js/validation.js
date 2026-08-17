// Javascript Document
var form = document.getElementById('contactForm');

var firstName = document.getElementById('firstName');
var firstNameFeedback = document.getElementById('firstNameFeedback');
var firstNameGroup = document.getElementById('firstNameGroup');

var lastName = document.getElementById('lastName');
var lastNameFeedback = document.getElementById('lastNameFeedback');
var lastNameGroup = document.getElementById('lastNameGroup');

var email = document.getElementById('email');
var emailFeedback = document.getElementById('emailFeedback');
var emailGroup = document.getElementById('emailGroup');

var phone = document.getElementById('phone');
var phoneFeedback = document.getElementById('phoneFeedback');
var phoneGroup = document.getElementById('phoneGroup');

var username = document.getElementById('username');
var usernameFeedback = document.getElementById('usernameFeedback');
var usernameGroup = document.getElementById('usernameGroup');

var password = document.getElementById('password');
var passwordFeedback = document.getElementById('passwordFeedback');
var passwordGroup = document.getElementById('passwordGroup');

var comments = document.getElementById('comments');
var commentsFeedback = document.getElementById('commentsFeedback');
var commentsGroup = document.getElementById('commentsGroup');


// name validation (Alphabetical, hyphens, apostrophes, min 2 chars, cant be empty)
function validateName(inputEl, feedbackEl, groupEl, fieldName) {
    var val = inputEl.value.trim();
    var nameRegex = /^[A-Za-z'-]{2,}$/;

    if (val === '') {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = fieldName + ' is required.';
        return false;
    } else if (!nameRegex.test(val)) {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = fieldName + ' must be at least 2 letters, hyphens, or apostrophes (no numbers).';
        return false;
    } else {
        groupEl.classList.remove('has-error');
        groupEl.classList.add('has-success');
        feedbackEl.innerHTML = 'Valid input';
        return true;
    }
}
// email validation using regex
function validateEmail(inputEl, feedbackEl, groupEl) {
    var val = inputEl.value.trim();
    var validRegex = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (val === '') {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = 'Email is required.';
        return false;
    } else if (!validRegex.test(val)) {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML ='Invalid email format';
        return false;
    } else {
        groupEl.classList.remove('has-error');
        groupEl.classList.add('has-success');
        feedbackEl.innerHTML = 'Valid input';
        return true;
    }
}

// phone validation: nums only, exactly 10 digits, no hyphens, no parenthesis, cant be empty
function validatePhone(inputEl, feedbackEl, groupEl) {
    var val = inputEl.value.trim();
    var phoneRegex = /^[0-9]{10}$/;

    if (val === '') {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = 'Phone is required.';
        return false;
    } else if (!phoneRegex.test(val)) {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = 'Phone must be exactly 10 numbers (no spaces, dashes, or parentheses';
        return false;
    } else {
        groupEl.classList.remove('has-error');
        groupEl.classList.add('has-success');
        feedbackEl.innerHTML = 'Valid input';
        return true;
    }
}

// validate credentials: minLength and cant be empty
function validateCredentials(inputEl, feedbackEl, groupEl, minLength, fieldName) {
    var val = inputEl.value;
    if (fieldName === 'Username') {
        val = val.trim();
    }

    if (val === '') {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = fieldName + ' is required.';
        return false;
    } else if (val.length < minLength) {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = fieldName + ' must be at least ' + minLength + ' characters.';
        return false;
    } else {
        groupEl.classList.remove('has-error');
        groupEl.classList.add('has-success');
        feedbackEl.innerHTML = 'Valid input';
        return true;
    }
}

// comments validation: can contain any char, cant be empty.
function validateComments(inputEl, feedbackEl, groupEl) {
    var val = inputEl.value.trim();
    
    if (val === '') {
        groupEl.classList.remove('has-success');
        groupEl.classList.add('has-error');
        feedbackEl.innerHTML = 'Comments are required.';
        return false;
    } else {
        groupEl.classList.remove('has-error');
        groupEl.classList.add('has-success');
        feedbackEl.innerHTML = 'Valid input';
        return true;
    }
}


// blurr event listeners
firstName.addEventListener('blur', function() {
    validateName(firstName, firstNameFeedback, firstNameGroup, 'First Name');
}, false);

lastName.addEventListener('blur', function() {
    validateName(lastName, lastNameFeedback, lastNameGroup, 'Last Name');
}, false);

email.addEventListener('blur', function() {
    validateEmail(email, emailFeedback, emailGroup);
}, false);

phone.addEventListener('blur', function() {
    validatePhone(phone, phoneFeedback, phoneGroup);
}, false);

username.addEventListener('blur', function() {
    validateCredentials(username, usernameFeedback, usernameGroup, 6, 'Username');
}, false);

password.addEventListener('blur', function() {
    validateCredentials(password, passwordFeedback, passwordGroup, 8, 'Password');
}, false);

comments.addEventListener('blur', function() {
    validateComments(comments, commentsFeedback, commentsGroup);
}, false);

// submit listener: does all the checks and prevents submission if a field is invalid
form.addEventListener('submit', function(event) {
    var isValid = true;
    
    if (!validateName(firstName, firstNameFeedback, firstNameGroup, 'First Name')) { isValid = false; }
    if (!validateName(lastName, lastNameFeedback, lastNameGroup, 'Last Name')) { isValid = false; }
    if (!validateEmail(email, emailFeedback, emailGroup)) { isValid = false; }
    if (!validatePhone(phone, phoneFeedback, phoneGroup)) { isValid = false; }
    if (!validateCredentials(username, usernameFeedback, usernameGroup, 6, 'Username')) { isValid = false; }
    if (!validateCredentials(password, passwordFeedback, passwordGroup, 8, 'Password')) { isValid = false; }
    if (!validateComments(comments, commentsFeedback, commentsGroup)) { isValid = false; }
    
    if (!isValid) {
        event.preventDefault(); // stop the submission
    }
}, false);
