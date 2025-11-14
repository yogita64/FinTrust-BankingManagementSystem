package com.fintrust.controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class ContactController extends SelectorComposer<Component>{
	@Wire
	private Textbox fullName,mobile,email,customerId,subject,description;
	
	@Wire
	Combobox category,subcategory,preferredContactMethod,preferredTime;
	
	@Wire
	Radiogroup priority;
	
	
	
}
