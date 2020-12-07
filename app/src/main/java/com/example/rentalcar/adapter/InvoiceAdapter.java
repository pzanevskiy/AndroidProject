package com.example.rentalcar.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.Invoice;

import java.util.List;

public class InvoiceAdapter extends BaseAdapter {
    Context ctx;
    LayoutInflater lInflater;
    List<Invoice> invoiceList;

    public InvoiceAdapter(Context context, List<Invoice> invoiceList){
        ctx=context;
        this.invoiceList =invoiceList;
        lInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return invoiceList.size();
    }

    @Override
    public Object getItem(int position) {
        return invoiceList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.fragment_invoice, parent, false);
        }

        TextView orderId=(TextView)view.findViewById(R.id.invoiceOrderId);
        TextView invoicePrice=(TextView)view.findViewById(R.id.invoiceOrderPrice);
        TextView msg=(TextView)view.findViewById(R.id.invoiceMsg);

        Invoice invoice= invoiceList.get(position);

        orderId.setText(String.valueOf(invoice.getOrderId()));
        invoicePrice.setText(String.format("%.2f",invoice.getPrice())+'$');
        msg.setText(invoice.getMessage());

        return view;
    }
}
