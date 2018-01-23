function editHandle(fowl){
    document.getElementById('editId').value = fowl.id;
    document.getElementById('editNumber').value = fowl.number;
    document.getElementById('editDescription').value = fowl.description;
    document.getElementById('editName').value = fowl.name;
}

function Fowl(id, name, number, descr){
    this.id = id;
    this.name = name;
    this.number = number;
    this.description = descr;
}

function cancelEditModalHandle() {
    document.getElementById('editId').value = '';
    document.getElementById('editNumber').value = '';
    document.getElementById('editDescription').value = '';
    document.getElementById('editName').value = '';
}