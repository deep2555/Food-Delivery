document.addEventListener('DOMContentLoaded', function() {
    console.log("js is loaded open");
	
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
	
	    loadRestaurants();
	
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

async function loadRestaurants() {
    try {
        // Show loading state
		console.log('inside the loadrestauran method');
        const container = document.getElementById('restaurantsGrid');
        container.innerHTML = '<div class = "Loading">Loading Restaurants</div>';

        // fetch the restaurant from the api
        const fetchRestaurantResponse = await fetch(`/api/users/restaurants/restaurantsDetail`);
        //console.log(fetchRestaurantResponse);
        if(!fetchRestaurantResponse.ok){
            throw new Error("not able to fetch the restaurant details");
    
        }
        const fetchRestRespDataJ = await fetchRestaurantResponse.json();

        // now clear the loading state
        container.innerHTML = '';

        // create and append the restaurant cards
        fetchRestRespDataJ.forEach(restaurants => {
            const restaurantCard = createRestaurantCard(restaurants);
            container.appendChild(restaurantCard);
                });
        
    } catch (error) {
        console.error('Error loading restaurants:', error);
        showError('Failed to load restaurants. Please try again.');
    }
}


function createRestaurantCard(restaurant) {
    // Create card element
   
    const restCard = document.createElement('div');
    restCard.className = 'restaurant-card';
    
    // create the on click listener afterwards so when user click it will display
	restCard.addEventListener('click', ()=> {
		window.location.href = 	`/restaurantsDetails?id=${restaurant.id}`;
	})
    // create the card html structure

    restCard.innerHTML = `
    <div class = "restaurant-image"> 
       <img src = "" alt = "not have yet"></img>
      <!-- Delivery time removed since not in available data -->
      <!-- Rating badge removed since not in available data -->
    </div>
    <div class = "restaurant-info">
    <!-- Restaurant Name -->
    <h3>${restaurant.restaurantName}</h3>
        
    <!-- Cuisine Type (e.g., "Italian", "Indian") -->
    <p class="cuisine-type">${restaurant.restaurantCuisineType}</p>
    
    <!-- Address with map icon -->
    <p class="restaurant-address">
        <i class="fas fa-map-marker-alt"></i> ${restaurant.restaurantAddress}
    </p>
    
    <!-- Description (optional) -->
    <p class="restaurant-description">${restaurant.restaurantDescription}</p>
    
    <!-- Contact number with phone icon -->
    <p class="restaurant-contact">
        <i class="fas fa-phone"></i> ${restaurant.restaurantContactInfo}
    </p>
    <div class="restaurant-footer">
            <!-- Opening Hours with clock icon -->
            <span class="opening-hours">
                <i class="fas fa-clock"></i> ${restaurant.restaurantOpeningHours}
            </span>
        </div>
    
    </div>
    
    `;
    return restCard;
    
}

function showError(message) {
    const errorElement = document.createElement('div');
    errorElement.className = 'error-message';
    errorElement.textContent = message;
    
    // Style and position the error message
    errorElement.style.textAlign = 'center';
    errorElement.style.padding = '15px';
    errorElement.style.backgroundColor = '#ffebee';
    errorElement.style.color = '#c62828';
    errorElement.style.margin = '20px 0';
    errorElement.style.borderRadius = '4px';
    
    const container = document.getElementById('restaurantsGrid');
    container.innerHTML = '';
    container.appendChild(errorElement);
}