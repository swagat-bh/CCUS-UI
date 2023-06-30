package com.bh.at.resuepojos;

import java.util.Arrays;
import java.util.Objects;

public class MarkerNode {
    private String assetId;
    private String name;
    private int[] geoLoc;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MarkerNode node = (MarkerNode) o;
        return assetId.equals(node.assetId) && Arrays.equals(geoLoc, node.geoLoc);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(assetId);
        result = 31 * result + Arrays.hashCode(geoLoc);
        return result;
    }

    public MarkerNode(String assetId, int[] geoLoc) {
        this.assetId = assetId;
        this.geoLoc = geoLoc;
    }

    public MarkerNode(String name) {
        this.name = name;
    }
    public MarkerNode(String name, String assetId) {
        this.name = name;
        this.assetId = assetId;
    }

    public int[] getGeoLoc() {
        return geoLoc;
    }

    public String getAssetId() {
        return assetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
