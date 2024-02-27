function validateForm() {
    var username = document.getElementById('username').value;
    var useremail = document.getElementById('useremail').value;
    var password = document.getElementById('userpassword').value;
    var confirmpassword = document.getElementById('confirmpassword').value;

    if (username.trim() === '' || password.trim() === '' || confirmpassword.trim() === '' || useremail.trim() === '') {
        username.ClassList.add('validation');
        useremail.ClassList.add('validation');
        password.ClassList.add('validation');
        confirmpassword.ClassList.add("validation");
        return false;
    }

    if (password.length < 8) {
        alert('Password must be at least 8 characters long!');
        return false;
    }
    return true;
}