document.addEventListener('DOMContentLoaded', function() {
    console.log("js is loaded open");
	
	// fetch or update the profile
	// Added this at the start of your DOMContentLoaded callback
			console.log("DOM fully loaded and parsed");
			console.log("Current cookies:", document.cookie);
			console.log("Session storage:", sessionStorage);
			console.log("Local storage:", localStorage);
			
			const userEmail = sessionStorage.getItem('userEmail') || localStorage.getItem('userEmail');
			// Add this in fetchUserProfile before the fetch call
			console.log(`Making request to: /api/users/profile?email=${encodeURIComponent(userEmail)}`);
			console.log(userEmail);
			// Fetch and display user profile data
			fetchUserProfile(userEmail);
	

	//fetch with id filter
		    // to fetch the id from the url
		    // URLSearchParams to implement the crud operation in url
		    const paramId = new URLSearchParams(window.location.search);
		    const id = paramId.get('id');

		    // validation
		    if(!id){
		        showError('no result found with this');
		    }
		    loadRestaurantsById(id);
	
});

async function fetchUserProfile(userEmail){
	try{
		console.log(userEmail);
        document.getElementById('username-display').textContent = 'Loading...';
        const response = await fetch(`/api/users/profile?email=${encodeURIComponent(userEmail)}`);
        console.log(response);

        if(!response.ok){
            throw new Error('not able to fetch the user');
            showError("user Not found");
        }
        const user = await response.json();
        updateUiProfile(user);
	}
    catch (error) {
        console.error('Error fetching user profile:', error);
        showError('Failed to load profile data. Please try again.');
    }
}


function updateUiProfile(user){
     // inside we update the frontend
     console.log('inside update ui profile method line 43');
     console.log(user.userName);
     document.getElementById('username-display').textContent = user.userName; 

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

    

}

// here is the function to fetch the restaurant detail as perid
async function loadRestaurantsById(id){
    console.log('inside the load restaurant byid');
    const responseFetchById = await fetch(`/api/users/restaurants/${id}`);
    console.log(responseFetchById);
    if(!responseFetchById.ok){
        throw new Error("not able to fetch the restaurant details");
    }
    const jsonResponse = await responseFetchById.json();
    // inject the data in fronend

	updateRestaurantProfile(jsonResponse);


}

function updateRestaurantProfile(jsonResponse){
	
	document.getElementById('restaurant-name').textContent = jsonResponse.restaurantName;
	document.getElementById('restaurant-cuisine').textContent = jsonResponse.restaurantCuisineType;
	document.getElementById('restaurant-address').textContent = jsonResponse.restaurantAddress;
	document.getElementById('restaurant-description').textContent = jsonResponse.restaurantDescription;
	document.getElementById('contact-info').textContent = jsonResponse.restaurantContactInfo;
	document.getElementById('opening-hours').textContent = jsonResponse.restaurantOpeningHours;
	document.getElementById('delivery-time').textContent = jsonResponse.delieveryTime;
	document.getElementById('price-range').textContent = jsonResponse.priceRange;
}