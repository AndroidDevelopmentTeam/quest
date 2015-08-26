package com.android.androiddevteam.quest.structure;

import android.app.Application;
import android.content.Context;

import com.android.androiddevteam.quest.database.DataAdapter;

/**
 * Quest
 * Created by: Oleksandr Priadko
 * 26.08.15
 */
public class App extends Application{

    private static App app;

    private static DataAdapter dataAdapter;
    private Context appContext;

    private App() {

    }

    public static App getInstance(){
        if (app == null){
            app = new App();
        }
        return app;
    }

    public void setAppContext(Context appContext){
        this.appContext = appContext;
    }

    public DataAdapter getDataAdapter() {
        if (dataAdapter == null){
            dataAdapter = new DataAdapter(appContext);
        }

        dataAdapter.open();
        return dataAdapter;
    }
}
