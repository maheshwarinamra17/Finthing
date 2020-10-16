package finthing.finthing;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import database_layer.FinthingDB;
import services.AssignTagsToTransactionsService;
import services.InsertTagsInLocalIntentService;
import services.ExtractTransactionsIntentService;

public class MainActivity extends AppCompatActivity {

    Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mActivity = this;
        requestPermissions(new String[]{Manifest.permission.READ_SMS}, 200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 200) {
            for (int i = 0; i < permissions.length; i++) {
                String permission = permissions[i];
                int grantResult = grantResults[i];
                if (permission.equals(Manifest.permission.READ_SMS)) {
                    if (grantResult == PackageManager.PERMISSION_GRANTED) {
                        Intent extractTransactionsService = new Intent(this, ExtractTransactionsIntentService.class);
                        startService(extractTransactionsService);

                        Intent insertTagInLocalService = new Intent(this, InsertTagsInLocalIntentService.class);
                        startService(insertTagInLocalService);

                        Intent assignTagsToTransactionsService = new Intent(this, AssignTagsToTransactionsService.class);
                        startService(assignTagsToTransactionsService);
                    } else {
                        requestPermissions(new String[]{Manifest.permission.READ_SMS}, 200);
                    }
                }
            }
        }
    }
}
