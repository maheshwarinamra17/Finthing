package models;

public enum PaymentMode {
    DEBIT_CARD("111"), NETBANKING("222"), CASH("333"), UPI("444"), TRANSFERS("555") ;

    private String mode;

    PaymentMode(String cash) {
        mode = cash;
    }

    public String getMode() {
        return mode;
    }

    public static PaymentMode fromString(String text) {
        for (PaymentMode b : PaymentMode.values()) {
            if (b.mode.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
