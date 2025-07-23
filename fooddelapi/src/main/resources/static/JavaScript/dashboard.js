// here we have to write the code to set the user profile in dashboard when login 
// logout functionality
//prevent back 
// here collect the dom elements document obejct model 
// build the model in tree like shape that javascript can read, update and delete

// this tell the browser wait till the html page is fullly loaded after loaded than run this sript
// first go wih the registration 
//  this line of code basically used when we write the script under head section not in body section 

document.addEventListener('DOMContentLoaded', function(){
    // check if the user is loged in 
    // if the user is loged in than procees further otherwise move to the login html page
	
	// Added this at the start of your DOMContentLoaded callback
	console.log("DOM fully loaded and parsed");
	console.log("Current cookies:", document.cookie);
	console.log("Session storage:", sessionStorage);
	console.log("Local storage:", localStorage);

	
    const userEmail = sessionStorage.getItem('userEmail') || localStorage.getItem('userEmail');
	// Add this in fetchUserProfile before the fetch call
	console.log(`Making request to: /api/users/profile?email=${encodeURIComponent(userEmail)}`);
	console.log(userEmail);
	
    if(!userEmail){
		console.log("helolo: not working");
        window.location.href  = '/login'; // change here when implement theamleaf
        return;
    }
        // Fetch and display user profile data
    fetchUserProfile(userEmail);

        // Set up logout functionality
    document.getElementById('logoutBtn').addEventListener('click', logoutUser);
	
	// function for the prevent back 
	preventBackNavigation();
	
});

async function fetchUserProfile(userEmail){
    try{
		console.log('user email sending',userEmail);
         document.getElementById('username-display').textContent = 'Loading...';
		 document.getElementById('current-address').textContent = 'Loading...';
        const response = await fetch(`/api/users/profile?email=${encodeURIComponent(userEmail)}`);
        console.log(response);
        // check response
        if (!response.ok) {
            throw new Error('Failed to fetch user profile');
        }

         const user = await response.json();
         // Update profile information in the UI
         console.log(user);
         updateProfileUI(user);
    }  
    catch (error) {
        console.error('Error fetching user profile:', error);
        showError('Failed to load profile data. Please try again.');
    }
}

// this mehtod or function prevent the user goes to back when he click the back button
function preventBackNavigation() {
    history.pushState(null, null, location.href);
    window.onpopstate = function() {
        history.go(1);
    };
}

// after creating the user profile the profile will update in the frontend 
// picture will add later
function updateProfileUI(user){

	console.log(user.userName);
    document.getElementById('username-display').textContent = user.userName; // attached the user full name here check the full name at last
   	document.getElementById('current-address').textContent = user.userAddress; // attached the address
	
	 // Update profile picture
    const profilePic = document.querySelector('.profile-pic');
    // here the profile image is not impleted yet so later see
    if (user.profileImage) {
        profilePic.src = user.profileImage;
    } else {
        // Generate avatar from initials if no profile image
        const initials = (user.userName || 'UU').split(' ').map(n => n[0]).join('');
        profilePic.src = `https://ui-avatars.com/api/?name=${initials}&background=ff6b6b&color=fff`;
    }

    // Update welcome message
    // check the name of the user from the backend
    if (document.getElementById('user-greeting')) {
        const firstName = user.userName ? user.userName.split(' ')[0] : user.userEmail;
        document.getElementById('user-greeting').textContent = firstName;
    }
    // Update delivery address if available
     if (user.userAddress && document.getElementById('current-address')) {
         document.getElementById('current-address').textContent = user.userAddress;
     }

}

function logoutUser() {
    // Clear user session
    sessionStorage.removeItem('userEmail');
    localStorage.removeItem('userEmail');
    
    // Redirect to login page
    window.location.href = '/login';
}

function showError(message) {
    // Create error message element
    const errorElement = document.createElement('div');
    errorElement.className = 'error-message';
    errorElement.textContent = message;
    
    // Style the error message
    errorElement.style.color = '#ff4757';
    errorElement.style.padding = '10px';
    errorElement.style.margin = '10px 0';
    errorElement.style.borderRadius = '5px';
    errorElement.style.backgroundColor = '#ffecec';
    errorElement.style.textAlign = 'center';
    
    // Insert error message
    const header = document.querySelector('header');
    if (header) {
        header.insertAdjacentElement('afterend', errorElement);
    }
    
    // Remove error after 5 seconds
    setTimeout(() => {
        errorElement.remove();
    }, 5000);
}
