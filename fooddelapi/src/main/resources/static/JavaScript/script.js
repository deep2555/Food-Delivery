// here collect the dom elements document obejct model 
// build the model in tree like shape that javascript can read, update and delete

// this tell the browser wait till the html page is fullly loaded after loaded than run this sript
// first go wih the registration 
document.addEventListener('DOMContentLoaded', function () {
    // this code is to hide or unhide the functionality of the password

    const togglePasswordBtn = document.querySelectorAll('.toggle-password');  // fetch the button
    togglePasswordBtn.forEach(btn => {
        btn.addEventListener('click', function () {
            const input = this.previousElementSibling;
            const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
            input.setAttribute('type', type);
            this.classList.toggle('fa-eye-slash');
        });
    });

    // now the above code is for the password length management
    const passwordInput = document.getElementById('password');
    if (passwordInput) {
        passwordInput.addEventListener('input', function () {
            const strengthBars = document.querySelectorAll('.strength-bar');
            const password = this.value;
            let strength = 0;

            // Reset bars
            strengthBars.forEach(bar => {
                bar.style.backgroundColor = '#e0e0e0';
            });

            // Check password strength
            if (password.length > 0) strength++;
            if (password.length >= 8) strength++;
            if (/[A-Z]/.test(password) && /[0-9]/.test(password)) strength++;

            // Update bars
            for (let i = 0; i < strength; i++) {
                let color;
                if (strength === 1) color = '#ff4757';
                else if (strength === 2) color = '#ffa502';
                else color = '#2ed573';

                strengthBars[i].style.backgroundColor = color;
            }
        })
    }

    // here is the javascript code for submission the form and request send to the backend 
    //and get the response deom the backemd

    const registerForm = document.getElementById('registerForm');
    if (registerForm) {
        registerForm.addEventListener('submit', async function (e) {
            e.preventDefault();
            // Get form values
            const userName = document.getElementById('fullname').value;
            const userMail = document.getElementById('email').value;
            const userPassword = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirm-password').value;
           // const phone = document.getElementById('phone').value;
            //const address = document.getElementById('address').value;

            // Client-side validation
            if (userPassword !== confirmPassword) {
                showError('Passwords do not match!');
                return;
            }

            if (!document.getElementById('terms').checked) {
                showError('You must agree to the terms and conditions');
                return;
            }

            // Create user object
            const user = {
                userName,
                userMail,
                userPassword,
               // phone,
               // address
            };


            try {
                // Show loading state
                const submitBtn = registerForm.querySelector('button[type="submit"]');
                submitBtn.disabled = true;
                submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Registering...';


                // Send registration request
                // main method defination at last
                const response = await registerUser(user);

                if (response.ok) {
                    // Registration successful
                    // method defination at last
                    showSuccess('Registration successful! Redirecting to login...');
                    
                    // Redirect to login page after 2 seconds
                    setTimeout(() => {
                        window.location.href = '/login';
                    }, 2000);
                } else {
                    // Handle server-side errors
                    const errorData = await response.json();
                    // method defination at last
                    showError(errorData.message || 'Registration failed');
                }
            } catch (error) {
                showError('An error occurred. Please try again.');
                console.error('Registration error:', error);
            } finally {
                // Reset button state
                const submitBtn = registerForm.querySelector('button[type="submit"]');
                submitBtn.disabled = false;
                submitBtn.textContent = 'Create Account';
            }

        });
    }


    // here is the method of function to send request to the backed or spring app
    // Function to register user via API
    async function registerUser(userData) {
        const response = await fetch('http://localhost:8080/api/users/createUser', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(userData)
        });
        
        return response;
    }


    // Helper function to show error messages
    function showError(message) {
        // Remove any existing error messages
        const existingError = document.querySelector('.error-message');
        if (existingError) {
            existingError.remove();
        }
        
        // Create error message element
        const errorElement = document.createElement('div');
        errorElement.className = 'error-message';
        errorElement.style.color = 'var(--primary-dark)';
        errorElement.style.marginBottom = '15px';
        errorElement.style.textAlign = 'center';
        errorElement.textContent = message;
        
        // Insert error message before the submit button
        const submitBtn = registerForm.querySelector('button[type="submit"]');
        registerForm.insertBefore(errorElement, submitBtn);
    }
    
    // Helper function to show success messages
    function showSuccess(message) {
        // Remove any existing messages
        const existingMessage = document.querySelector('.success-message');
        if (existingMessage) {
            existingMessage.remove();
        }
        
        // Create success message element
        const successElement = document.createElement('div');
        successElement.className = 'success-message';
        successElement.style.color = 'var(--success-color)';
        successElement.style.marginBottom = '15px';
        successElement.style.textAlign = 'center';
        successElement.textContent = message;
        
        // Insert success message before the submit button
        const submitBtn = registerForm.querySelector('button[type="submit"]');
        registerForm.insertBefore(successElement, submitBtn);
    }


});