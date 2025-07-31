package com.deepanshu.fooddelapi.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepanshu.fooddelapi.model.MenuItemsDTO;
import com.deepanshu.fooddelapi.model.MenuItemsRequestHandlerModel;
import com.deepanshu.fooddelapi.model.RestaurantDTO;
import com.deepanshu.fooddelapi.repo.ItemsRepo;
import com.deepanshu.fooddelapi.repo.RestaurantRepo;

@Service
public class MenuItemsServiceImpl implements MenuItemsService {

	public static final Logger customLogger = LoggerFactory.getLogger(MenuItemsServiceImpl.class);

	@Autowired
	private ItemsRepo itemsRepo;

	@Autowired
	private RestaurantRepo restaurantRepo;

	@Override
	public MenuItemsDTO createMenuItems(MenuItemsRequestHandlerModel itemsRequestHandlerModel) {
		customLogger.debug("inside the menuitems create menu items with items: {}", itemsRequestHandlerModel);
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
		customLogger.debug("inside the fetch menu and restaurant id is :{}", restaurantId);
		Optional<MenuItemsDTO> fetchedResponse = itemsRepo.findById(restaurantId);
		List<MenuItemsDTO> listOfMenuByRestaurantId = itemsRepo.findByRestaurantId(restaurantId);
		fetchedResponse.stream().forEach(System.out::println);
		listOfMenuByRestaurantId.stream().forEach(System.out::println);

		/*
		 * to render the ui like user able to see the items based on the categories here
		 * transform the response with group by the categories
		 */
		List<Map<String, Object>> renderResponse = updateResponseByCategoeries(listOfMenuByRestaurantId);
		return renderResponse;
	}

	/*
	 * this method update the response to group by categories the ui will get the
	 * response like categories id , categories name , categories contain items
	 */

	private List<Map<String, Object>> updateResponseByCategoeries(List<MenuItemsDTO> listOfMenuByRestaurantId) {
		// TODO Auto-generated method stub
		customLogger.debug("inside the updateResponseByCategoeries method, fetched response:{}",
				listOfMenuByRestaurantId);

		/*
		 * 1) group by the response in categories and print the group by response like k
		 * -> categories name v -> items belong to that categories :: is the method
		 * references
		 */

		Map<String, List<MenuItemsDTO>> groupedElement = listOfMenuByRestaurantId.stream()
				.collect(Collectors.groupingBy(MenuItemsDTO::getCategory));
		groupedElement.forEach((k, v) -> {
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

			customLogger.debug("the Category is :" + categoriesElem.get("name"));

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
		return customResponse;
	}

}
