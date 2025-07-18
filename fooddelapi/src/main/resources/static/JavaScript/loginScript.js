// here i have added script related to the login form
// here collect the dom elements document obejct model 
// build the model in tree like shape that javascript can read, update and delete

// this tell the browser wait till the html page is fullly loaded after loaded than run this sript

const loginForm = document.getElementById('loginForm');
if(loginForm){
    loginForm.addEventListener('submit', async function(e){
        e.preventDefault();

        // get the values from the form 
        // variables name should be same as backend name

        const userEmail = document.getElementById('email').value;
        const userPassword = document.getElementById('password').value;

        // to print the value in the console
        console.log(userEmail);
        console.log(userPassword);
		
		// Create user object
		           const userDetail = {
		               userEmail,
		               userPassword
		              // phone,
		              // address
		           };

        // when clicked show loading data
        try{
            const submitBtn = loginForm.querySelector('button[type="submit"]');
            submitBtn.disabled = true;
            submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Logging in...';

              // Send login request
              // method created below
              const response = await loginUser(userDetail);
			  console.log("Sending data:", JSON.stringify(userDetail));

              if (response.ok) {
                // Registration successful
                // method defination at last
                showSuccess('Login successful! Redirecting to Home...');
				
				// here i am adding the code to save the email in the session storage
				console.log('setting the seession')
				sessionStorage.setItem('userEmail', userEmail);
                
                // Redirect to login page after 2 seconds
                setTimeout(() => {
                    window.location.href = '/dashboard';
                }, 2000);
            } else {
                // Handle server-side errors
				let errorMessage = 'Login failed';
				    try {
				        const errorData = await response.json();
				        errorMessage = errorData.message || errorMessage;
				    } catch (e) {
				        console.warn("No JSON response from server.");
				    }
				    showError(errorMessage);
            }
        } catch (error) {
            showError('An error occurred. Please try again.');
            console.error('Login error:', error);
        } finally {
            // Reset button state
            const submitBtn = loginForm.querySelector('button[type="submit"]');
            submitBtn.disabled = false;
            submitBtn.textContent = 'Login to Account';
        }

    });


}

// Function to login user via API
async function loginUser(userDetail) {
    const response = await fetch('http://localhost:8080/api/users/loginUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
		
        body: JSON.stringify(userDetail)
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
    const submitBtn = loginForm.querySelector('button[type="submit"]');
    loginForm.insertBefore(errorElement, submitBtn);
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
    const submitBtn = loginForm.querySelector('button[type="submit"]');
    loginForm.insertBefore(successElement, submitBtn);
}