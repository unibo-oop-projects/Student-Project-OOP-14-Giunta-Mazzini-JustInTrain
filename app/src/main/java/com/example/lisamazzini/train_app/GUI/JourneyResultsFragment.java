package com.example.lisamazzini.train_app.GUI;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lisamazzini.train_app.Controller.JourneyDataRequest;
import com.example.lisamazzini.train_app.Controller.JourneyRequest;
import com.example.lisamazzini.train_app.Controller.JourneyResultsController2;
import com.example.lisamazzini.train_app.Controller.JourneyTrainRequest;
import com.example.lisamazzini.train_app.Exceptions.InvalidStationException;
import com.example.lisamazzini.train_app.GUI.Adapter.JourneyResultsAdapter;
import com.example.lisamazzini.train_app.Model.Tragitto.PlainSolution;
import com.example.lisamazzini.train_app.Model.Tragitto.PlainSolutionWrapper;
import com.example.lisamazzini.train_app.Model.Tragitto.Tragitto;
import com.example.lisamazzini.train_app.R;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.UncachedSpiceService;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.LinkedList;
import java.util.List;

public class JourneyResultsFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager manager;
    private JourneyResultsAdapter journeyResultsAdapter;
    JourneyResultsController2 controller;
    private SpiceManager spiceManager = new SpiceManager(UncachedSpiceService.class);
    private String departureStation;
    private String departureID;
    private String arrivalStation;
    private String arrivalID;


    public static JourneyResultsFragment newInstance() {
        return new JourneyResultsFragment();
    }

    public JourneyResultsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View layoutInflater = inflater.inflate(R.layout.fragment_journey_results, container, false);
        recyclerView = (RecyclerView)layoutInflater.findViewById(R.id.cardListFragment);

        this.manager = new LinearLayoutManager(getActivity());
//            this.journeyResultsAdapter = new JourneyResultsAdapter(this.flatJourneyTrainsList);
        this.journeyResultsAdapter = new JourneyResultsAdapter(new LinkedList<PlainSolution>());

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(journeyResultsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        controller = new JourneyResultsController2("pesaro", "pesaro", "asdf", "adsf");

        return layoutInflater;
    }

    public void makeRequestsWithStations(String departureStation, String arrivalStation) {
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
        spiceManager.execute(new JourneyDataRequest(this.departureStation), new DepartureDataRequestListenter());
    }

    public void makeRequestsWithIDs(String departureID, String arrivalID) {
        this.departureID = departureID;
        this.arrivalID = arrivalID;
        spiceManager.execute(new JourneyRequest(departureID, arrivalID), new JourneyRequestListener());
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    private class DepartureDataRequestListenter implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof InvalidStationException) {
                Log.d("cazzi", "sbagliata partenza");
            }
        }

        @Override
        public void onRequestSuccess(String s) {
            departureID = s.split("\\|S")[1];
            spiceManager.execute(new JourneyDataRequest(arrivalStation), new ArrivalDataRequestListener());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    private class ArrivalDataRequestListener implements RequestListener<String> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            if (spiceException.getCause() instanceof InvalidStationException) {
                Log.d("cazzi", "sbagliata arrivo");
            }
        }

        @Override
        public void onRequestSuccess(String s) {
            arrivalID = s.split("\\|S")[1];
            spiceManager.execute(new JourneyRequest(departureID, arrivalID), new JourneyRequestListener());
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////

    private class JourneyRequestListener implements RequestListener<Tragitto> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Log.d("cazzi", "altro errore");
        }

        @Override
        public void onRequestSuccess(Tragitto tragitto) {
            controller.buildPlainSolutions(tragitto);
            List<PlainSolution> plainSolutions = controller.getPlainSolutions();
            spiceManager.execute(new JourneyTrainRequest(plainSolutions), new JourneyTrainRequestListener());
//            journeyResultsAdapter = new JourneyResultsAdapter(controller.getPlainSolutions());
//            recyclerView.setAdapter(journeyResultsAdapter);
//            journeyResultsAdapter.notifyDataSetChanged();
        }
    }

    private class JourneyTrainRequestListener implements RequestListener<PlainSolutionWrapper> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

        }

        @Override
        public void onRequestSuccess(PlainSolutionWrapper plainSolutions) {
            journeyResultsAdapter = new JourneyResultsAdapter(plainSolutions.getList());
            recyclerView.setAdapter(journeyResultsAdapter);
            journeyResultsAdapter.notifyDataSetChanged();
        }
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onStart() {
        spiceManager.start(getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }
}
