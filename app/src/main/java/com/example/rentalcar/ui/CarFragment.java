package com.example.rentalcar.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rentalcar.R;
import com.example.rentalcar.adapter.CarsAdapter;
import com.example.rentalcar.db.entity.Car;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.ui.dummy.DummyContent;
import com.example.rentalcar.viewmodel.CarViewModel;
import com.example.rentalcar.viewmodel.UserViewModel;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class CarFragment extends androidx.fragment.app.ListFragment {

    private static final String ARG_PARAM1 = "param1";

    private String userId;
    CarViewModel carViewModel;
    UserViewModel userViewModel;
    public CarFragment() {
    }


    public static CarFragment newInstance(String userId) {
        CarFragment fragment = new CarFragment();
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
        carViewModel=new ViewModelProvider(this).get(CarViewModel.class);
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
    }

    @Override
    public void onListItemClick(@NonNull ListView l, @NonNull View v, int position, long id) {
        Car car = (Car)l.getItemAtPosition(position);

        Intent intent=new Intent(getActivity(),RentCarActivity.class);
        intent.putExtra("userId",userId);
        intent.putExtra("car",car);
        startActivity(intent);
        super.onListItemClick(l, v, position, id);
    }
    private CarsAdapter carsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=getView();
        carViewModel.getCars().observe(getViewLifecycleOwner(), new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> cars) {
                carsAdapter=new CarsAdapter(getActivity(), cars);
                setListAdapter(carsAdapter);
                carsAdapter.notifyDataSetChanged();
            }
        });
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}