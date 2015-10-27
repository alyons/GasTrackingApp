package net.alexanderlyons.firstlesson.DataObjects;

import io.realm.Realm;
import io.realm.RealmMigration;
import io.realm.internal.ColumnType;
import io.realm.internal.Table;

/**
 * Created by PyroticBlaziken on 10/26/2015.
 */
public class GasTrackerMigration implements RealmMigration {

    public long execute(Realm realm, long schemaVersion) {
        schemaVersion = version0ToVersion1(realm, schemaVersion);

        return schemaVersion;
    }

    long version0ToVersion1(Realm realm, long schemaVersion) {

        /*
            // Version 0
            class Car
                add RealmList<Trip> trips;
                add double baseMileage;
         */

        if (schemaVersion == 0) {
            Table carTable = realm.getTable(Car.class);
            carTable.addColumn(ColumnType.LINK_LIST, "trips");
            carTable.addColumn(ColumnType.DOUBLE, "baseMileage");

            schemaVersion++;
        }

        return schemaVersion;
    }
}
