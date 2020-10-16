package utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import finthing.finthing.R;


/**
 * Created by namra on 24/06/18.
 */

public class Utils {

    public static boolean isNotEmpty(Object text){
        return text != null;
    }

    public static long stringTimeToEpoch(String format, String ts){
        long timestamp = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            Date dt = sdf.parse(ts);
            timestamp = dt.getTime();
        } catch(ParseException e) {
            timestamp =  -1;
        }
        return timestamp;
    }

    public static String md5Hash(String target) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(target.getBytes());
            StringBuffer sb = new StringBuffer();
            for (byte b : md5.digest())
                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sanatizeNumberString(String text){
        return text.replaceAll("Rs(\\.*)", "").replaceAll(",","").replaceAll("INR","");
    }

    public static String sanatizeNarrationString(String text){
        return text.replaceAll("[^A-Za-z0-9 ]", "");
    }

    public static int getColor(Context mCtx, int id){
        return mCtx.getResources().getColor(id);
    }

    public static Typeface fontNormal(Context mCtx){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return mCtx.getResources().getFont(R.font.noto_sans_regular);
        }else{
            return Typeface.createFromAsset(mCtx.getAssets(), "font/noto_sans_regular.ttf");
        }
    }
    public static Typeface fontBold(Context mCtx){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return mCtx.getResources().getFont(R.font.noto_sans_bold);
        }else{
            return Typeface.createFromAsset(mCtx.getAssets(), "font/noto_sans_bold.ttf");
        }
    }
}
