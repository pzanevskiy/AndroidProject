package com.example.rentalcar.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.rentalcar.R;
import com.example.rentalcar.adapter.UserWithOrdersAdapter;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.db.relations.UserWithOrders;
import com.example.rentalcar.ui.dummy.DummyContent;
import com.example.rentalcar.viewmodel.OrderViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class OrderFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";
    private String userId;

    public OrderFragment() {
    }

    public static OrderFragment newInstance(String userId) {
        OrderFragment fragment = new OrderFragment();
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
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Order order=(Order)l.getItemAtPosition(position);
        Intent intent=new Intent(getActivity(),CurrentOrderActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("order",order);
        startActivity(intent);
        super.onListItemClick(l, v, position, id);
    }

    private UserWithOrdersAdapter userWithOrdersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        OrderViewModel orderViewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getOrders("current",Integer.parseInt(userId)).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                userWithOrdersAdapter=new UserWithOrdersAdapter(getActivity(),orders);
                setListAdapter(userWithOrdersAdapter);
                userWithOrdersAdapter.notifyDataSetChanged();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}