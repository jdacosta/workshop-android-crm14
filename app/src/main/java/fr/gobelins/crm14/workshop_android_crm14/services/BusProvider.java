package fr.gobelins.crm14.workshop_android_crm14.services;

import com.squareup.otto.Bus;

/**
 * Created by risq on 10/14/15.
 */
public final class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.
    }
}