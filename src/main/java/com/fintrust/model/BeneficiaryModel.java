package com.fintrust.model;



public class BeneficiaryModel {
    private String name;
    private String accountNumber;
    private String bankName;
    private String ifscCode;
    private String email;
    private String mobile;

    public BeneficiaryModel() {}

    public BeneficiaryModel(String name, String accountNumber, String bankName, String ifscCode, String email, String mobile) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.email = email;
        this.mobile = mobile;
    }

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAccountNumber() { return accountNumber; }
    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public String getBankName() { return bankName; }
    public void setBankName(String bankName) { this.bankName = bankName; }

    public String getIfscCode() { return ifscCode; }
    public void setIfscCode(String ifscCode) { this.ifscCode = ifscCode; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}
