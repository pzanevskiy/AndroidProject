package com.example.rentalcar.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rentalcar.R;
import com.example.rentalcar.adapter.InvoiceAdapter;
import com.example.rentalcar.db.entity.Invoice;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.ui.dummy.DummyContent;
import com.example.rentalcar.viewmodel.InvoiceViewModel;
import com.example.rentalcar.viewmodel.UserViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class InvoiceFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";

    private String userId;
    public InvoiceFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static InvoiceFragment newInstance(String userId) {
        InvoiceFragment fragment = new InvoiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, userId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            userId = getArguments().getString(ARG_PARAM1);

        }
        invoiceViewModel=new ViewModelProvider(this).get(InvoiceViewModel.class);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Invoice invoice=(Invoice) l.getItemAtPosition(position);
        User user=userViewModel.getUser(Integer.parseInt(userId));
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("Pay dialog");
        builder.setMessage("Pay "+String.format("%.2f",invoice.getPrice())+'$');
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(user.getMoney()-invoice.getPrice()<0){
                    Toast t = Toast.makeText(getActivity(),"Not enough money!\nThe penalty is not paid.",Toast.LENGTH_SHORT);
                    t.show();
                    dialog.cancel();

                } else {
                    invoice.setStatus("paid");
                    user.setMoney(user.getMoney()-invoice.getPrice());
                    userViewModel.updateUser(user);
                    invoiceViewModel.updateInvoice(invoice);
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog dialog=builder.create();
        dialog.show();
    }
    InvoiceViewModel invoiceViewModel;
    InvoiceAdapter invoiceAdapter;
    UserViewModel userViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_list, container, false);
        invoiceViewModel.getInvoices("not_paid",Integer.parseInt(userId)).observe(getViewLifecycleOwner(), new Observer<List<Invoice>>() {
            @Override
            public void onChanged(List<Invoice> invoices) {
                invoiceAdapter=new InvoiceAdapter(getContext(),invoices);
                setListAdapter(invoiceAdapter);
                invoiceAdapter.notifyDataSetChanged();
            }
        });
        // Set the adapter

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
