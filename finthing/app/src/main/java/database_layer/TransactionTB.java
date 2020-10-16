package database_layer;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = DBConfig.TRANSACTION_TB)
public class TransactionTB {

    @PrimaryKey
    private Integer id;

    @ColumnInfo(name = "trans_ts")
    private Long transTS;

    @ColumnInfo(name = "narration")
    private String narration;

    @ColumnInfo(name = "amount")
    private Double amount;

    @ColumnInfo(name = "refer_id")
    private String referID;

    @ColumnInfo(name = "balance")
    private Double balance;

    @ColumnInfo(name = "tag_id")
    private Integer tagID;

    @ColumnInfo(name = "bank_id")
    private String bankID;

    @ColumnInfo(name = "sms_ts")
    private Long smsTS;

    @ColumnInfo(name = "location")
    private String location;

    @ColumnInfo(name = "mode")
    private String mode;

    @ColumnInfo(name = "temp_id")
    private String tempID;

    public TransactionTB(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTransTS() {
        return transTS;
    }

    public void setTransTS(Long transTS) {
        this.transTS = transTS;
    }

    public String getNarration() {
        return narration;
    }

    public void setNarration(String narration) {
        this.narration = narration;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getReferID() {
        return referID;
    }

    public void setReferID(String referID) {
        this.referID = referID;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getTagID() {
        return tagID;
    }

    public void setTagID(Integer tagID) {
        this.tagID = tagID;
    }

    public String getBankID() {
        return bankID;
    }

    public void setBankID(String bankID) {
        this.bankID = bankID;
    }

    public Long getSmsTS() {
        return smsTS;
    }

    public void setSmsTS(Long smsTS) {
        this.smsTS = smsTS;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getTempID() {
        return tempID;
    }

    public void setTempID(String tempID) {
        this.tempID = tempID;
    }
}
