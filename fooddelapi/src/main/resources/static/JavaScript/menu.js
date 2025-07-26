// here the code to show mwnu list dynamically on page
// use DOMContentLoaded so it handle or call the js after fully load the html

document.addEventListener('DOMContentLoaded', function () {
    console.log('inside the menu js');

    // ftech the detail like when user click particular restaurant 
    // fetch the id of particular restaurant by urlSearchparam

    const urlParam = new URLSearchParams(window.location.search);
    const restaurantId = urlParam.get('id');

    console.log('url param is :', urlParam);
    console.log('restaurant id is :', restaurantId);

    if (restaurantId) {
        // if we fetch the id than load the detail by passing that id
      loadMenuDetailsById(restaurantId);
		
    } else {
        throw new Error('not able to fetch the id');
    }

    

})

async function loadMenuDetailsById(restaurantId) {
    // to load the details
    console.log('inside the load menu dtail method');
    try {
        const response = await fetch(`/api/user/restaurant/menu/${restaurantId}`);
        console.log(response);

        if (!response.ok) {
            throw new console.error('unable to fetch the details');
            //showError('unable to fetch the details');
        }

        const jsonPharseResponse = await response.json();

		updateMenuDynamicCard(jsonPharseResponse);
      
        
    }
    catch (error) {
        console.log('error loading menu:', error);
       /// showError('unable to load details');
    }

}

function updateMenuDynamicCard(jsonPharseResponse) {

    const cardContainer = document.getElementById('category-tabs');
    const menuContainer = document.getElementById('menu-categories');

    // clearing the exicting content
    cardContainer.innerHTML = '';
    menuContainer.innerHTML = '';

    // create the categories tab and section
    // general prespective in each categories we can check the details of the item 
    // sweets , main course, etc

    jsonPharseResponse.forEach((category, index) => {
        // creating the tab inside each categories
        const tab = document.createElement('div');
        tab.className = 'category-tab' + (index === 0 ? 'active' : '');
        tab.textContent = category.categoryName;
        tab.dataset.categoryID = category.id; // we get the id of the each categories

        // create categories section
        const categorySection = document.createElement('div');
        categorySection.className = 'menu-category' + (index === 0 ? ' active' : '');
        categorySection.id = `category-${category.id}`;

        // Add items to category
        const itemsHtml = category.items.map(item => `
        <div class="menu-item" data-item-id="${item.id}">
                <div class="item-header">
                    <h3 class="item-name">${item.itemName}</h3>
                    <span class="item-price">$${item.itemPrice.toFixed(2)}</span>
                </div>
                <p class="item-description">${item.itemDescription}</p>
                <div class="item-footer">
                    <button class="btn-add-to-cart" data-item-id="${item.id}">
                       Add to Cart
                    </button>
                </div>
        </div>       
         `).join('');

         categorySection.innerHTML = `
            <h3>${category.categoryName}</h3>
            <div class="menu-items">
                ${itemsHtml}
            </div>
        `;
        menuContainer.appendChild(categorySection);


    });

}
