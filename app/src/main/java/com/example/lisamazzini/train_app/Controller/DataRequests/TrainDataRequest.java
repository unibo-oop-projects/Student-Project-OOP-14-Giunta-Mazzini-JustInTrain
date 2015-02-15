package com.example.lisamazzini.train_app.Controller.DataRequests;

import com.example.lisamazzini.train_app.Exceptions.InvalidTrainNumberException;
import com.example.lisamazzini.train_app.Model.Constants;
import com.example.lisamazzini.train_app.Model.Treno.ListWrapper;
import com.example.lisamazzini.train_app.Utilities;
import com.squareup.okhttp.internal.Util;

import java.net.*;
import java.util.List;


public class TrainDataRequest extends AbstractDataRequest {

    private final String trainNumber;

    public TrainDataRequest(String searchQuery){
        super(ListWrapper.class);
        this.trainNumber = searchQuery;
    }

    @Override
    protected URL generateURL() throws MalformedURLException {
        return Utilities.generateTrainAutocompleteURL(this.trainNumber);
    }

    @Override
    protected void check(List result) throws InvalidTrainNumberException {
        if(result.size()==0) {
            throw new InvalidTrainNumberException();
        }
    }
}