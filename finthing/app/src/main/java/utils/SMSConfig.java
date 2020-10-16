package utils;

/**
 * Created by namra on 24/06/18.
 */

public class SMSConfig {

    public static final Double SIMILARITY_THRESHOLD = 0.90;
    public static final int JARO_WINKLER_MAX_LENGTH = 180;
    public static final String CASH_NARRATION = "CASH WITHDRAWL";

    //POSSIBLE SMS VARIBALES;
    public static final String SMS_PAYMENT_MODE_ID = "mode_id";
    public static final String SMS_BANK_ID = "bank_id";
    public static final String SMS_LOCATION = "location";
    public static final String SMS_SERVICE = "service";
    public static final String SMS_TIMESTAMP = "timestamp";
    public static final String SMS_BALANCE = "current_balance";
    public static final String SMS_SPEND = "spend";
    public static final String SMS_TEMP_ID = "temp_id";

    //POSSIBLE TEMPLATE VARIBALES
    public static final String TEMP_BANK_ID = "bank_id";
    public static final String TEMP_BALANCE = "balance";

    //API_CALLS
    public static final String BACKEND_SERVER = "http://192.168.0.104:8000/";
    public static final String API_BANK_TEMPLATE = BACKEND_SERVER + "bankTemplates.json";
    public static final String API_TAGS_DATA = BACKEND_SERVER + "tagsData.json";

}
