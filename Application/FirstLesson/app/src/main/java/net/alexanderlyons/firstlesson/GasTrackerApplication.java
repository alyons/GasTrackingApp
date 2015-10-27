package net.alexanderlyons.firstlesson;

import android.app.Application;

import net.alexanderlyons.firstlesson.DataObjects.GasTrackerMigration;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by PyroticBlaziken on 10/26/2015.
 */
public class GasTrackerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        setDefaultRealmConfig();
    }

    void setDefaultRealmConfig() {
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("net.alexanderlyons.firstLesson.realm")
                .schemaVersion(0)
                .migration(new GasTrackerMigration())
                .build();

        Realm.setDefaultConfiguration(config);
    }
}
