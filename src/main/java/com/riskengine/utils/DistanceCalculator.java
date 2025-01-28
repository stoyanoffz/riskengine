package com.riskengine.utils;

public final class DistanceCalculator {

    private static final Integer EARTH_RADIUS = 6371;

    private DistanceCalculator() {
    }

    public static double calculateDistance(double firstLat, double firstLong, double secondLat, double secondLong) {
        double latDistance = Math.toRadians(secondLat - firstLat);
        double lonDistance = Math.toRadians(secondLong - firstLong);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(firstLat)) * Math.cos(Math.toRadians(secondLat))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c;
    }

}
