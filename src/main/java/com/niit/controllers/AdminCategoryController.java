package com.niit.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.niit.validator.AdminCategoryValidator;
import com.portal.models.Category;


@Controller
public class AdminCategoryController extends BasicController {
	@Autowired
	AdminCategoryValidator adminCategoryValidator;
	
	/**
	 * This method will add or update categories. It would first list all the
	 * categories and then allow user to add or update the categories. 
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/admin_add_category", method = RequestMethod.GET)
	public ModelAndView add_category(@ModelAttribute("command") Category category) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("category",get_category_id(category));
		model.put("currentUser", get_current_user());
		model.put("categories",  categoryDAOImpl.getCategories());
		return new ModelAndView("add_category", model);
	}

	
	/**
	 * This method will be used to save the category. This controller method
	 * would be invoked to save the admin category and then the add method would
	 * be called.
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_save_category", method = RequestMethod.POST)
	public ModelAndView save_category(@ModelAttribute("command") Category category,BindingResult result, SessionStatus status) {
		  adminCategoryValidator.validate(category,result);
			if(result.hasErrors())
			{   		
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("category",get_category_id(category));
			model.put("currentUser", get_current_user());
			model.put("categories", categoryDAOImpl.getCategories());
			return new ModelAndView("add_category", model);
			}
	        
		categoryDAOImpl.addCategory(category);
		return new ModelAndView("redirect:/admin_add_category");
	}

	
	/**
	 * This method will be used to edit the category. This controller method
	 * would be invoked to save the admin category and then the add method would
	 * be called.
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_edit_category", method = RequestMethod.GET)
	public ModelAndView edit_category(@ModelAttribute("command") Category category) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("category", categoryDAOImpl.getCategoryById(category.getId()));
		model.put("categories", categoryDAOImpl.getCategories());
		return new ModelAndView("add_category", model);
	}

	
	/**
	 * This method will be used to delete the category. This controller method
	 * would be invoked to save the admin category and then the add page method
	 * would be called.
	 * 
	 * @param Category
	 *          -- The category value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_delete_category", method = RequestMethod.GET)
	public ModelAndView delete_category(@ModelAttribute("command") Category category) {
		categoryDAOImpl.RemoveCategory(category);
		return new ModelAndView("redirect:/admin_add_category");
	}

	/**
	 * This method would get the array index that has to be updated in the web site.
	 * @param category
	 *        -- Category model
	 * @return
	 *        -- Category with id assigned
	 */
	public Category get_category_id(Category category){
		List<Category> category_list = categoryDAOImpl.getCategories();
		int[] indexes = new int[category_list.size()];
		int index = 0;
		System.out.println(category.getId());
		if(category.getId() == null || category.getId().isEmpty()){
			for(Category searchIndex : category_list){
				System.out.println(searchIndex.getId());
				indexes[index] = Integer.parseInt(searchIndex.getId());
				index = index + 1;
			}
			index = 0;
			Arrays.sort(indexes);
			for(int i : indexes){
				if(index==i){
					index = index +1;
					continue;
				}
				else{
					break;
				}
				
			}
			category.setId(String.valueOf(index));
		}
		
		
		return category;
	}
	
	
}// End of the class
