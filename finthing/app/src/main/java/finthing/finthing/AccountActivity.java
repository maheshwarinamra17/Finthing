package finthing.finthing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import database_layer.FinthingDB;
import database_layer.TransactionTB;

public class AccountActivity extends AppCompatActivity {

    private RecyclerView mTransactionRecyclerListView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private List<TransactionTB> transactionList;
    private FinthingDB dbConn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        dbConn = FinthingDB.createDBInstance(getApplicationContext());
        prepareView();
        prepareMonthlyTransactionData();
    }x

    private void prepareView(){
        transactionList = new ArrayList<>();
        mTransactionRecyclerListView = (RecyclerView) findViewById(R.id.transaction_list);
        mTransactionRecyclerListView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(this);
        mTransactionRecyclerListView.setLayoutManager(mLayoutManager);
        mAdapter = new TransactionAdapter(transactionList, getApplicationContext());
        mTransactionRecyclerListView.setAdapter(mAdapter);
    }

    private void prepareMonthlyTransactionData(){
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        Calendar calendar = Calendar.getInstance();
                        long currentTime = calendar.getTimeInMillis();
                        calendar.set(Calendar.DAY_OF_MONTH, 1);
                        calendar.set(Calendar.HOUR_OF_DAY, 0);
                        calendar.set(Calendar.MINUTE,0);
                        calendar.set(Calendar.SECOND,0);
                        long firstDayOfMonth = calendar.getTimeInMillis();
                        transactionList.addAll(dbConn.transactionTBDao().getTransactions(firstDayOfMonth, currentTime));
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
        ).start();
    }
}
