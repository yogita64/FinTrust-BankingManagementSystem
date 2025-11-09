package com.fintrust.controller;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import com.fintrust.model.BeneficiaryModel;

public class AddBeneficiaryController extends SelectorComposer<Component> {

    @Wire private Textbox accBox;
    @Wire private Textbox ifscBox;
    @Wire private Textbox nameBox;
    @Wire private Textbox bankBox;
    @Wire private Textbox emailBox;
    @Wire private Textbox mobileBox;
    @Wire private Listbox beneficiaryList;

    
    private List<BeneficiaryModel> dummyDB = new ArrayList<>();

    
    private List<BeneficiaryModel> beneficiaryListData = new ArrayList<>();

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);

        // Dummy data for lookup only
        dummyDB.add(new BeneficiaryModel("Rohan Mehta", "9876543210", "HDFC Bank", "HDFC0001234", "rohan@gmail.com", "9876543210"));
        dummyDB.add(new BeneficiaryModel("Sneha Sharma", "1234567890", "ICICI Bank", "ICIC0005678", "sneha@gmail.com", "8765432109"));
        dummyDB.add(new BeneficiaryModel("Amit Patel", "5555555555", "SBI Bank", "SBIN0008888", "amit@gmail.com", "9988776655"));
        dummyDB.add(new BeneficiaryModel("Rommi Mehta", "8976548910", "PNB Bank", "PNB0081233", "rommi@gmail.com", "9876345610"));

        
        loadBeneficiaryList(); // loads empty
    }

    // Auto-fill on pressing Enter in IFSC field
    @Listen("onOK = #ifscBox")
    public void autofillOnEnter() {
        String acc = accBox.getValue().trim();
        String ifsc = ifscBox.getValue().trim();

        if (acc.isEmpty() || ifsc.isEmpty()) {
            Messagebox.show("Please enter both Account Number and IFSC Code!");
            return;
        }

        BeneficiaryModel match = findBeneficiaryInDummy(acc, ifsc);

        if (match != null) {
            nameBox.setValue(match.getName());
            bankBox.setValue(match.getBankName());
            emailBox.setValue(match.getEmail());
            mobileBox.setValue(match.getMobile());
            Messagebox.show(" Details auto-filled for " + match.getName());
        } else {
            Messagebox.show(" No match found. You can fill manually and click Add.");
            nameBox.setValue("");
            bankBox.setValue("");
            emailBox.setValue("");
            mobileBox.setValue("");
        }
    }

    // When Add Beneficiary button is clicked
    @Listen("onClick = #addBtn")
    public void addBeneficiary() {
        String acc = accBox.getValue().trim();
        String ifsc = ifscBox.getValue().trim();
        String name = nameBox.getValue().trim();
        String bank = bankBox.getValue().trim();
        String email = emailBox.getValue().trim();
        String mobile = mobileBox.getValue().trim();

        if (acc.isEmpty() || ifsc.isEmpty() || name.isEmpty()) {
            Messagebox.show("Please enter Account No, IFSC Code, and Name!");
            return;
        }

        BeneficiaryModel newBen = new BeneficiaryModel(name, acc, bank, ifsc, email, mobile);
        beneficiaryListData.add(newBen);

        loadBeneficiaryList();
        Messagebox.show(" Beneficiary added successfully!");

        // Clear fields
        accBox.setValue("");
        ifscBox.setValue("");
        nameBox.setValue("");
        bankBox.setValue("");
        emailBox.setValue("");
        mobileBox.setValue("");
    }

    // Populate Listbox
    private void loadBeneficiaryList() {
        beneficiaryList.getItems().clear();
        for (BeneficiaryModel b : beneficiaryListData) {
            Listitem item = new Listitem();
            item.appendChild(new Listcell(b.getName()));
            item.appendChild(new Listcell(b.getAccountNumber()));
            item.appendChild(new Listcell(b.getBankName()));
            item.appendChild(new Listcell(b.getIfscCode()));
            item.appendChild(new Listcell(b.getEmail()));
            item.appendChild(new Listcell(b.getMobile()));
            beneficiaryList.appendChild(item);
        }
    }

    // Find match in dummy data
    private BeneficiaryModel findBeneficiaryInDummy(String acc, String ifsc) {
        for (BeneficiaryModel b : dummyDB) {
            if (b.getAccountNumber().equals(acc) && b.getIfscCode().equalsIgnoreCase(ifsc)) {
                return b;
            }
        }
        return null;
    }
}
