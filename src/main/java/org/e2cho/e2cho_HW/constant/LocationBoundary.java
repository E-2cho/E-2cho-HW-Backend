package org.e2cho.e2cho_HW.constant;

import lombok.Getter;

@Getter
public enum LocationBoundary {

    EXTREME_EAST(131.87222222),
    EXTREME_WEST(125.06666667),
    EXTREME_NORTH(38.45000000),
    EXTREME_SOUTH(33.10000000);

    private final double coordinate;

    LocationBoundary(double coordinate) {
        this.coordinate = coordinate;
    }
}
