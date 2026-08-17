function checkInput(minLength,inputDiv,fbDiv,gpDiv)
{
	var elMsg = document.getElementById(fbDiv);
    var elUsername = document.getElementById(inputDiv);
    var elGroup = document.getElementById(gpDiv);
    if (elUsername.value.length < minLength)
    {
        elGroup.classList.add('has-error');
        elMsg.innerHTML = inputDiv + ' must be ' + minLength + ' characters or more';
    }
    else
    {
        elGroup.classList.remove('has-error');
        elGroup.classList.add('has-success');
		elMsg.innerHTML = '';
	}
}

var el = document.getElementById('username');
var pwEl = document.getElementById('password');
el.addEventListener('blur', function () {
	checkInput(4,'username','unFeedback','unGroup');
}, false);
pwEl.addEventListener('blur', function() {
    checkInput(8,'password','pwFeedback','pwGroup');
}, false);