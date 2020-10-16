package models;

import android.database.Cursor;

/**
 * Created by namra on 24/06/18.
 */

public class SMS {

    public Integer id;
    private String address;
    private String person;
    private String date;
    private String date_sent;
    private String protocol;
    private String read;
    private String status;
    private String type;
    private String subject;
    private String body;
    private String service_center;
    private String error_code;
    private String creator;
    private String seen;

    public SMS(Cursor cursor) {
        this.id = cursor.getInt(0);
        this.address = cursor.getString(1);
        this.person = cursor.getString(2);
        this.date = cursor.getString(3);
        this.date_sent = cursor.getString(4);
        this.protocol = cursor.getString(5);
        this.read = cursor.getString(6);
        this.status = cursor.getString(7);
        this.type = cursor.getString(8);
        this.subject = cursor.getString(9);
        this.body = cursor.getString(10);
        this.service_center = cursor.getString(11);
        this.error_code = cursor.getString(12);
        this.creator = cursor.getString(13);
        this.seen = cursor.getString(14);
    }

    public static String[] getAllFieldNames(){
        return new String[]{"_id","address","person","date",
                "date_sent","protocol","read","status","type",
                "subject","body","service_center","error_code",
                "creator","seen"};
    }

    public Integer getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getPerson() {
        return person;
    }

    public String getDate() {
        return date;
    }

    public String getDate_sent() {
        return date_sent;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getRead() {
        return read;
    }

    public String getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public String getService_center() {
        return service_center;
    }

    public String getError_code() {
        return error_code;
    }

    public String getCreator() {
        return creator;
    }

    public String getSeen() {
        return seen;
    }
}
