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

import com.niit.validator.AdminSupplierValidator;
import com.portal.models.Product;
import com.portal.models.Supplier;

@Controller
public class AdminSupplierController extends BasicController {
	@Autowired
	AdminSupplierValidator adminSupplierValidator;
	
	/**
	 * This method will add or update suppliers. It would first list all the
	 * suppliers and then allow user to add or update the suppliers. 
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object  
	 */
	@RequestMapping(value = "/admin_add_supplier", method = RequestMethod.GET)
	public ModelAndView add_supplier(@ModelAttribute("command") Supplier supplier) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("supplier", get_supplier_with_id(supplier));
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		return new ModelAndView("add_supplier", model);
	}

	
	/**
	 * This method will be used to save the supplier. This controller method
	 * would be invoked to save the admin supplier and then the add method would
	 * be called.
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_save_supplier", method = RequestMethod.POST)
	public ModelAndView save_supplier(@ModelAttribute("command") Supplier supplier,BindingResult result, SessionStatus status) {
		adminSupplierValidator.validate(supplier,result);
		if(result.hasErrors())
		{   	
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("supplier", get_supplier_with_id(supplier));
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		return new ModelAndView("add_supplier", model);
		}
		supplierDAOImpl.addSupplier(supplier);
		 
		return new ModelAndView("redirect:/admin_add_supplier");
	}

	
	/**
	 * This method will be used to edit the supplier. This controller method
	 * would be invoked to save the admin supplier and then the add method would
	 * be called.
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_edit_supplier", method = RequestMethod.GET)
	public ModelAndView edit_supplier(@ModelAttribute("command") Supplier supplier) {
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("currentUser", get_current_user());
		model.put("supplier", supplierDAOImpl.getSupplierById(supplier.getId()));
		model.put("suppliers", supplierDAOImpl.getSuppliers());
		return new ModelAndView("add_supplier", model);
	}

	
	/**
	 * This method will be used to delete the supplier. This controller method
	 * would be invoked to save the admin supplier and then the add page method
	 * would be called.
	 * 
	 * @param Supplier
	 *          -- The supplier value that is tagged to the model. The model form data object is passed to this method.
	 * @return 
	 *          -- Model and view data object 
	 */
	@RequestMapping(value = "/admin_delete_supplier", method = RequestMethod.GET)
	public ModelAndView delete_supplier(@ModelAttribute("command") Supplier supplier) {
		supplierDAOImpl.RemoveSupplier(supplier);
		return new ModelAndView("redirect:/admin_add_supplier");
	}

	
	
	/**
	 * This method would set the supplier id for the web page.
	 * @param supplier
	 *           -- The model
	 * @return
	 *           -- The model with id set,
	 */
	public Supplier get_supplier_with_id(Supplier supplier){
		List<Supplier> supplier_list = supplierDAOImpl.getSuppliers();
		int[] indexes = new int[supplier_list.size()];
		int index = 0;
		System.out.println(supplier.getId());
		if(supplier.getId() == null || supplier.getId().isEmpty()){
			for(Supplier searchIndex : supplier_list){
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
			supplier.setId(String.valueOf(index));
		
		}
		return supplier;
	}
	
	
}// End of the class
