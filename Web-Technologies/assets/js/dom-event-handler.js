function checkUsername() 
{
	var elMsg = document.getElementById('feedback');
	var elUsername = document.getElementById('username');

    if (elUsername.value.length < 5)
    {
		elMsg.innerHTML = 'Username must be 5 characters or more';
    }
    else
    {
		elMsg.innerHTML = '';
	}
}

var el = document.getElementById('username');
el.onblur = checkUsername;
