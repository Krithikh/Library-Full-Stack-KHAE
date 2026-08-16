package self.learning.backend.lib.mgmt.contract;

/** Presenter-owned codes frozen with docs/contracts/application-contract-v1.json. */
public final class ApplicationCodes {
    private ApplicationCodes() { }
    public static final String RESPONSE_SUCCESS = "00";
    public static final String RESPONSE_INVALID_INPUT = "01";
    public static final String RESPONSE_NOT_FOUND = "02";
    public static final String RESPONSE_DUPLICATE = "03";
    public static final String RESPONSE_INVALID_STATE = "04";
    public static final String RESPONSE_DEPENDENCY_EXISTS = "05";
    public static final String T01_CREATE_DEPARTMENT = "01";
    public static final String T02_READ_DEPARTMENT = "02";
    public static final String T03_UPDATE_DEPARTMENT = "03";
    public static final String T04_DEACTIVATE_DEPARTMENT = "04";
    public static final String T05_SEARCH_DEPARTMENT = "05";
    public static final String P01_LIST_DEPARTMENTS = "P01";
}
