// dom element variablkes
var addButton = document.getElementById('addToList');
var shoppingList = document.getElementById('shoppingList');
var itemInput = document.getElementById('itemName');

// updating total count of shopping items
function updateCount() {
    var items = shoppingList.getElementsByClassName('alert');
    var count = items.length;
    var countSpan = document.getElementById('itemCount');
    countSpan.textContent = count;
}

// validating input, create and append a new list item
function addItem() {
    var newEl, newElText, inputText;
    inputText = itemInput.value;
    // returning early if input is empty
    if (inputText === null || inputText === undefined || inputText.trim() === "") {
        console.warn("Item name is null or empty. Action aborted.");
        return;
    }
    // creating list item container
    newEl = document.createElement('div');
    newElText = document.createTextNode(inputText.trim());
    newEl.appendChild(newElText);
    newEl.classList.add('alert');
    newEl.classList.add('alert-info');
    newEl.setAttribute('role', 'alert');
    shoppingList.appendChild(newEl);
    
    // clearing the input field and updatint the counter
    itemInput.value = '';
    updateCount();
}

// adding click listener to add button
addButton.addEventListener('click', addItem, false);

// removing list item on click using event delegation
shoppingList.addEventListener('click', function(event) {
    var target = event.target;
    // handling the text nodesand the compatibility
    if (target && target.nodeType === 3) {
        target = target.parentNode;
    }
    
    // find nearest alert 
    var alertItem = null;
    var current = target;
    while (current && current !== shoppingList) {
        if (current.classList && current.classList.contains('alert')) {
            alertItem = current;
            break;
        }
        current = current.parentNode;
    }
    
    // removing the item and updatig the count
    if (alertItem) {
        shoppingList.removeChild(alertItem);
        updateCount();
    }
}, false);

// runing the first count check when the page loadss
updateCount();