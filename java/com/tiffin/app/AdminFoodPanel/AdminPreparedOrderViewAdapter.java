package com.tiffin.app.AdminFoodPanel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tiffin.app.R;
import java.util.List;

public class AdminPreparedOrderViewAdapter extends RecyclerView.Adapter<AdminPreparedOrderViewAdapter.ViewHolder> {

    private Context mcontext;
    private List<AdminFinalOrders> adminFinalOrderslist;

    public AdminPreparedOrderViewAdapter(Context context, List<AdminFinalOrders> adminFinalOrderslist) {
        this.adminFinalOrderslist = adminFinalOrderslist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.admin_preparedorderview, parent, false);
        return new AdminPreparedOrderViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final AdminFinalOrders adminFinalOrders = adminFinalOrderslist.get(position);
        holder.dishname.setText(adminFinalOrders.getDishName());
        holder.price.setText("Price: ₹ " + adminFinalOrders.getDishPrice());
        holder.quantity.setText("× " + adminFinalOrders.getDishQuantity());
        holder.totalprice.setText("Total: ₹ " + adminFinalOrders.getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return adminFinalOrderslist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView dishname, price, totalprice, quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dishname = itemView.findViewById(R.id.Cdishname);
            price = itemView.findViewById(R.id.Cdishprice);
            totalprice = itemView.findViewById(R.id.Ctotalprice);
            quantity = itemView.findViewById(R.id.Cdishqty);
        }
    }
}
