// Javascript Document
function checkUsername()      // Declare function
{ 
    var elMsg = document.getElementById('feedback');  // Get feedback element
    var elUsername = document.getElementById('username');  // Get username input
    if (elUsername.value.length < 5)
    {           // If username too short
        elMsg.innerHTML = 'Username must be 5 characters or more';  // Set msg
    }
    else
    {  // Otherwise
        elMsg.innerHTML = '';  // Clear message
    }
}