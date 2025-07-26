package com.deepanshu.fooddelapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;
import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.repo.ItemsRepo;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {

	@Autowired
	private ItemsRepo itemsRepo;

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Override
	public MenuItemsDTO createMenuItems(MenuItemsRequestHandlerModel itemsRequestHandlerModel) {
		System.out.println("here inside the menuitems create menu items");
		System.out.println(itemsRequestHandlerModel.toString());
		/*
		 * here we receive the request body in request handler and than add into
		 * original Dto model class
		 */
		RestaurantDTO fetchMenu = restaurantRepo.findById(itemsRequestHandlerModel.getRestaurantDetailId())
				.orElseThrow(() -> new RuntimeException("no list found"));
		// initiate the real dto
		MenuItemsDTO menuItemsDTO = new MenuItemsDTO();
		menuItemsDTO.setItemName(itemsRequestHandlerModel.getItemName());
		menuItemsDTO.setItemPrice(itemsRequestHandlerModel.getItemPrice());
		menuItemsDTO.setItemDescription(itemsRequestHandlerModel.getItemDescription());
		menuItemsDTO.setItemPrice(itemsRequestHandlerModel.getItemPrice());
		menuItemsDTO.setCategory(itemsRequestHandlerModel.getCategory());
		menuItemsDTO.setRestaurant(fetchMenu);

		return itemsRepo.save(menuItemsDTO);
	}

	@Override
	public List<Map<String, Object>> fetchMenuItemById(int restaurantId) {
		System.out.println("inside the fetch menu by id");
		System.out.println("restaurant id is " + restaurantId);
		Optional<MenuItemsDTO> fetchedResponse = itemsRepo.findById(restaurantId);
		fetchedResponse.stream().forEach(System.out::println);

		/*
		 * to render the ui like user able to see the items based on the categories here
		 * transform the response with group by the categories
		 */
		List<Map<String, Object>> renderResponse = updateResponseByCategoeries(fetchedResponse);
		return renderResponse;
	}

	/*
	 * this method update the response to group by categories the ui will get the
	 * response like categories id , categories name , categories contain items
	 */

	private List<Map<String, Object>> updateResponseByCategoeries(Optional<MenuItemsDTO> fetchedResponse) {
		// TODO Auto-generated method stub
		System.out.println("inside the updateResponseByCategoeries method");
		System.out.println("fetched response: " + fetchedResponse);

		/*
		 * 1) group by the response in categories and print the group by response like k
		 * -> categories name v -> items belong to that categories :: is the method
		 * references
		 */

		Map<String, List<MenuItemsDTO>> groupedElement = fetchedResponse.stream()
				.collect(Collectors.groupingBy(MenuItemsDTO::getCategory));
		groupedElement.forEach((k, v) -> {
			System.out.println(k + " -> " + v.size() + " items");
		});

		/* structure create for the response */
		List<Map<String, Object>> customResponse = new ArrayList<>(); // for the response to send

		/*
		 * iterate over each entry inside the map make a different map to ad id and name
		 * of the categories so the response we sent like categories id, categories name
		 */

		for (Map.Entry<String, List<MenuItemsDTO>> items : groupedElement.entrySet()) {
			Map<String, Object> categoriesElem = new HashMap<>();

			categoriesElem.put("id", items.getKey().toLowerCase().replace("\\s+", "-"));
			categoriesElem.put("categoryName", items.getKey());

			System.out.println("the id is" + categoriesElem.get("id"));
			System.out.println("the Category is :" + categoriesElem.get("name"));

			/*
			 * now create the new list which containing all the map and create the map to
			 * collect all the items so that we can add all items as per specific categories
			 */

			List<Map<String, Object>> itemsContain = items.getValue().stream().map(mapItem -> {
				Map<String, Object> finalItemAdded = new HashMap<>();
				finalItemAdded.put("id", mapItem.getId());
				finalItemAdded.put("itemName", mapItem.getItemName());
				finalItemAdded.put("itemDescription", mapItem.getItemDescription());
				finalItemAdded.put("itemPrice", mapItem.getItemPrice());

				return finalItemAdded;

			}).collect(Collectors.toList());

			categoriesElem.put("items", itemsContain);
			customResponse.add(categoriesElem);
		}
		System.out.println("the response we get is: " + customResponse);

		return customResponse;
	}

}
