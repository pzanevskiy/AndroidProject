package com.example.rentalcar.ui;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rentalcar.R;
import com.example.rentalcar.adapter.UserWithCompletedOrdersAdapter;
import com.example.rentalcar.db.entity.Order;
import com.example.rentalcar.viewmodel.OrderViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class CompletedOrderFragment extends ListFragment {

    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String userId;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CompletedOrderFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CompletedOrderFragment newInstance(String userId) {
        CompletedOrderFragment fragment = new CompletedOrderFragment();
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

    private UserWithCompletedOrdersAdapter userWithCompletedOrdersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        OrderViewModel orderViewModel=new ViewModelProvider(this).get(OrderViewModel.class);
        orderViewModel.getOrders("completed",Integer.parseInt(userId)).observe(getViewLifecycleOwner(), new Observer<List<Order>>() {
            @Override
            public void onChanged(List<Order> orders) {
                userWithCompletedOrdersAdapter =new UserWithCompletedOrdersAdapter(getActivity(),orders);
                setListAdapter(userWithCompletedOrdersAdapter);
                userWithCompletedOrdersAdapter.notifyDataSetChanged();
            }
        });

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}