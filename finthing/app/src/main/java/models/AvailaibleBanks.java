package models;

public enum AvailaibleBanks {

    HDFC("hdfc");

    private String bankId;

    AvailaibleBanks(String bankId) {
        this.bankId = bankId;
    }

    public String getBankId() {
        return bankId;
    }

    public static AvailaibleBanks fromString(String text) {
        for (AvailaibleBanks b : AvailaibleBanks.values()) {
            if (b.bankId.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
