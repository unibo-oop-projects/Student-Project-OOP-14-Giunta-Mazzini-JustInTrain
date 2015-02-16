package com.example.lisamazzini.train_app.Network;



import com.example.lisamazzini.train_app.Model.Treno.Treno;

import retrofit.http.*;


/**
 * Created by lisamazzini on 02/02/15.
 */
public interface TrainAPI {

    @GET("/andamentoTreno/{codice}/{numero}")
    Treno getTrain(@Path("numero") String numero, @Path("codice") String codice);

}
