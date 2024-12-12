function validateSignInForm() {
    const email = document.getElementById('email')?.value?.trim();
    const password = document.getElementById('password')?.value?.trim();
    if (!email || !password) {
        alert('Заполните все поля');
        return false;
    }
    return true;
}
