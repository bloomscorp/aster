package com.bloomscorp.aster.tenant.address.contract;

public class AddressContract {

    public final static String TABLE = "address";
    public final static String TENANT_ID = "tenant_id";
    public final static String NAME = "name";
    public final static String PRIMARY_PHONE = "primary_contact_number";
    public final static String ALTERNATE_PHONE = "alternate_contact_number";
    public final static String ADDRESS_LINE_ONE = "address_line_1";
    public final static String ADDRESS_LINE_TWO = "address_line_2";
    public final static String LANDMARK = "landmark";
    public final static String POSTAL_CODE = "postal_code";
    public final static String CITY = "city";
    public final static String STATE = "state";
    public final static String COUNTRY = "country";
    public final static String ADDRESS_TYPE = "address_type";
    public final static String DEFAULT_ADDRESS = "default_address";

    private AddressContract() {
    }
}
