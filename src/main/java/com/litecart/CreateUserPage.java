package com.litecart;

public class CreateUserPage {
    public static final String PARENT = "//*[@id='page']";
    public static final String CUSTOMER_FORM = PARENT + "//*[@name='customer_form']";
    public static final String INPUT_TEMPLATE = "//label[contains(text(),'%s')]/..//input";
    public static final String SELECT_TEMPLATE = "//div[contains(@class,'form-group')]//label[contains(text(),'%s')]/..//select";
    public static final String INPUT_I_LIKE_TO_BE_NOTIFIED = CUSTOMER_FORM + "//input[@type='checkbox' and @name='newsletter']";
    public static final String INPUT_I_VE_READ_PRIVACY_POLICY = CUSTOMER_FORM + "//input[@type='checkbox' and @name='terms_agreed']";
    public static final String BUTTON_CREATE_ACCOUNT = CUSTOMER_FORM + "//button[@name='create_account']";

}
