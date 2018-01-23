var deleteSubmitBtn = document.getElementById('deleteSubmitBtn');
var deleteFowlForm = document.getElementById('deleteFowlForm');
var deleteButtonBtn = document.getElementById('deleteButtonBtn');

function deleteHandle(checkboxes){
    for(var i = 0; i<checkboxes.length; i++){
        var checkbox = checkboxes[i];
        if(checkbox.checked){
            var input = document.createElement('input');
            input.setAttribute('type', 'hidden');
            input.setAttribute('name', 'deleteId');
            input.setAttribute('value', checkbox.value);
            deleteFowlForm.appendChild(input);
        }
    }
    deleteButtonBtn.setAttribute("disabled", "true");
    deleteSubmitBtn.click();
}

function deleteCancelHandle() {
    var inputs = document.getElementsByName('deleteId');
    var len = inputs.length;
    for(var i = 0; i<len; i++){
        var input = inputs[i];
        input.parentNode.removeChild(input);
    }
}
