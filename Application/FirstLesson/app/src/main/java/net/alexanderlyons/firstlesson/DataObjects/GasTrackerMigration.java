package net.alexanderlyons.firstlesson.DataObjects;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.RealmSchema;
import io.realm.internal.Table;

/**
 * Created by PyroticBlaziken on 10/26/2015.
 */
public class GasTrackerMigration implements RealmMigration {

    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.get("Car")
                    .addRealmListField("trips", schema.get("Trip"))
                    .addField("baseMileage", double.class);
        }

    }
}
