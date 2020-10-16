package finthing.finthing;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

import database_layer.TransactionTB;
import utils.Utils;

public class TransactionAdapter  extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {

    private List<TransactionTB> transactionList;
    private Context mCtx;

    public TransactionAdapter(List<TransactionTB> transactionTBList, Context ctx){
        transactionList = transactionTBList;
        mCtx = ctx;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView narration, sub_narration, amount;
        public ImageView cat_icon;
        public ViewHolder(View view) {
            super(view);
            cat_icon = (ImageView) view.findViewById(R.id.trans_cat_icon);
            narration = (TextView) view.findViewById(R.id.trans_narration);
            sub_narration = (TextView) view.findViewById(R.id.trans_sub_narration);
            amount = (TextView) view.findViewById(R.id.trans_amount);

        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.transaction_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TransactionTB transaction = transactionList.get(position);
        holder.narration.setText(transaction.getNarration());
        holder.sub_narration.setText(formatDate(transaction.getTransTS()));
        holder.amount.setText(transaction.getAmount().toString());
        holder.cat_icon.setImageDrawable(decideCategoryIcon(0));
    }

    @Override
    public int getItemCount() {
        return Utils.isNotEmpty(transactionList) ? transactionList.size() : 0;
    }

    private Drawable decideCategoryIcon(int tagId){
        Integer[] catArray = {R.drawable.img_dropdown, R.drawable.img_menu};
        return mCtx.getDrawable(catArray[new Random().nextInt(catArray.length)]);
    }

    private String formatDate(long transTS){
        SimpleDateFormat tsFormat = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
        Date longToDate = new Date(transTS);
        return tsFormat.format(longToDate);
    }
}
