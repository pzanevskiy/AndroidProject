package com.example.rentalcar.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rentalcar.R;
import com.example.rentalcar.db.entity.User;
import com.example.rentalcar.viewmodel.UserViewModel;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3= "param3";
    private static final String ARG_PARAM4 = "param4";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String mParam3;
    private String mParam4;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1/*, String param2,String param3,String param4*/) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        args.putString(ARG_PARAM3, param3);
//        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//            mParam3 = getArguments().getString(ARG_PARAM3);
//            mParam4 = getArguments().getString(ARG_PARAM4);
        }
    }
    UserViewModel userViewModel;
    @Override
    public void onStart() {
        super.onStart();
        View view=getView();
        userViewModel=new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUserById(Integer.parseInt(mParam1)).observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                TextView textView=(TextView)view.findViewById(R.id.homeTextView);
                TextView email=(TextView)view.findViewById(R.id.homeUserEmail);
                TextView money=(TextView)view.findViewById(R.id.homeUserMoney);
                int userId=Integer.parseInt(mParam1);
                textView.setText(user.getName());
                email.setText(user.getEmail());
                money.setText(String.format("%.2f",user.getMoney())+'$');
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);

        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}