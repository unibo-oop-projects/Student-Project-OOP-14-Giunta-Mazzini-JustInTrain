package com.example.lisamazzini.train_app.Model;

/**
 * Contains constants for the whole project.
 *
 * @author Alberto Giunta
 */

public class Constants {

    public final static String ROOT = "http://193.138.161.135/viaggiatrenomobile/resteasy/viaggiatreno";
    public final static String STATION_AUTOCOMPLETE = "/autocompletaStazione/";
    public final static String TRAIN_AUTOCOMPLETE = "/cercaNumeroTrenoTrenoAutocomplete/";


    public final static String SEPARATOR = "%";

    public final static String WITH_IDS = "withIDs";
    public final static String WITH_STATIONS = "withStations";

    public final static String JOURNEY_PREF_FILE = "JourneyPref";
    public final static String TRAIN_PREF_FILE = "TrainPref";

    public final static String SDF= "yyyy-MM-dd'T'HH:mm:ss";
    public final static String SDF_NO_SECS = "yyyy-MM-dd'T'HH:mm:00";

    public final static String DELAY_ACH = "Hai accumulato 60 minuti di ritardo!";
    public final static String PIN_ACH = "Hai pinnato 10 treni!!";

    public final static String TRAIN_N_EXTRA = "trainNumber";
    public final static String ID_ORIGIN_EXTRA = "idOrigine";
    public final static String DEPARTURE_TIME_EXTRA = "oraPartenza";

    public final static String DEPARTURE_STAT_EXTRA = "departureStation";
    public final static String ARRIVAL_STAT_EXTRA = "arrivalStation";
    public final static String REQUESTED_TIME_EXTRA = "requestedTime";
    public final static String IS_CUSTOM_TIME_EXTRA = "isCustomTime";
    public final static String ACTION_REFRESH ="Aggiorna";
    public final static String ACTION_DELETE = "Elimina";

    public final static String ACH_STORE_FILE = "ACHIEVEMENT_STORE";
    public final static String ACH_DATA_FILE = "ACHIEVEMENT_DATA";


    public final static String WRONG_TRAIN_TITLE= "Numero treno non valido!";
    public final static String WRONG_TRAIN= "Il numero inserito non corrisponde a nessun treno";
    public final static String WRONG_STATION_TITLE = "Stazione inesistente!";
    public final static String WRONG_STATION = "Il nome inserito non corrisponde a nessun risultato";
    public final static String CONNECTION_ERROR_TITLE = "Problemi di connessione!";
    public final static String CONNECTION_ERROR = "Controllare la propria connessione internet";
    public final static String NO_AVAILABLE_SOLUTION_TITLE = "Nessuna soluzione trovata!";
    public final static String NO_AVAILABLE_SOLUTION = "Nessuna soluzione disponibile per la tratta selezionata";
    public final static String SERVICE_NOT_AVAILABLE_TITLE = "Servizio non disponibile!";
    public final static String SERVICE_NOT_AVAILABLE = "Il servizio Viaggiatreno.com è offline o in manuetenzione";

    public final static String OK_MSG = "Ok";




}
