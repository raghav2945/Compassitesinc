package com.example.raghwendra.findmyrestaurant;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by raghawendra.kumar on 12-04-2016.
 */
public class FragmentTab extends Fragment {
    public ParcelListAdapter mAdapter;
    final Fragment selfRef = this;
    RecyclerView rvCars;
    RecyclerView.LayoutManager mLayoutManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_layout, container, false);
        rvCars=(RecyclerView)v.findViewById(R.id.lvGenericList);
        rvCars.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        rvCars.setLayoutManager(mLayoutManager);
        bindData();
        return v;
    }

    public void bindData() {
        System.out.println("Start request" + Calendar.getInstance().getTime().toString());
        String dataURL = "http://lit-sea-66228.herokuapp.com/tests/c2Utc3Nl";
        Ion.with(this)
                .load(dataURL)
                .asJsonArray()
                .setCallback(new FutureCallback<JsonArray>() {
                    @Override
                    public void onCompleted(Exception e, JsonArray result) {

                        if (result == null) {
                            //preventing from crash in case of failure
                            return;
                        }

                        ObjectMapper mapper = new ObjectMapper();
                        ArrayList<ParcelModel> matchingParcels = new ArrayList<>();

                        for (Object tempObj : result) {
                            ParcelModel tempParcel = null;

                            try {
                                tempParcel = mapper.readValue(tempObj.toString(), ParcelModel.class);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                            if (tempParcel != null) {
                                matchingParcels.add(tempParcel);
                            }


                        }
                        mAdapter = new ParcelListAdapter(getActivity(), matchingParcels, selfRef);
                        rvCars.setAdapter(mAdapter);

                    }
                });
    }
}

