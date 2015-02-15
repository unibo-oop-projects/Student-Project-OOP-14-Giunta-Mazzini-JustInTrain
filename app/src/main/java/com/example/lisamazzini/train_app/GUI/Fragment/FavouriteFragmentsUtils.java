package com.example.lisamazzini.train_app.GUI.Fragment;

import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lisamazzini.train_app.Controller.Favourites.FavouriteTrainController;
import com.example.lisamazzini.train_app.Controller.Favourites.IFavouriteController;
import com.example.lisamazzini.train_app.Exceptions.FavouriteException;
import com.example.lisamazzini.train_app.R;


public class FavouriteFragmentsUtils {

    private final IFavouriteController favouriteController;
    private Menu menu;
    private Context context;


    public FavouriteFragmentsUtils(IFavouriteController controller) {
        this.favouriteController = controller;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setContext(Context context){
        this.context = context;
    }

    public Menu toggleFavouriteIcon(String reqData1, String reqData2) {
        if (favouriteController.isFavourite(reqData1, reqData2)) {
            setAsFavouriteIcon(true);
        } else {
            setAsFavouriteIcon(false);
        }
        return this.menu;
    }

    public void setAsFavouriteIcon(boolean b) {
        menu.getItem(0).setVisible(!b);
        menu.getItem(1).setVisible(b);
    }

    public void setAllEnabled(boolean b) {
        menu.getItem(0).setVisible(b);
        menu.getItem(1).setVisible(b);
    }

    public Menu onOptionsItemSelected(MenuItem item, String[] details, Activity activity) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            activity.finish();
        }
        if (id == R.id.action_prefere) {
            try {
                Toast.makeText(context, "Aggiunto ai preferiti", Toast.LENGTH_SHORT).show();
                addFavourite(details);
                setAsFavouriteIcon(true);
            } catch (FavouriteException e) {
                e.printStackTrace();
            }
        } else if (id == R.id.action_deprefere) {
            Toast.makeText(context, "Rimosso dai preferiti", Toast.LENGTH_SHORT).show();
            removeFavourite(details);
            setAsFavouriteIcon(false);
        }
        return this.menu;
    }

    private void removeFavourite(String[] details) {
        favouriteController.removeFavourite(details[0], details[1]);
    }

    private void addFavourite(String[] details) throws FavouriteException {
        if (details.length == 2) {
            favouriteController.addFavourite(details[0], details[1]);
        } else if (details.length == 4) {
            favouriteController.addFavourite(details[0], details[1], details[2], details[3]);
        }
    }
}