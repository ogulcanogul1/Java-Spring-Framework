package com.example.beans.tyres;


import com.example.interfaces.Tyre;

public class BridgeStoneTyres implements Tyre {
    @Override
    public String getTyreBrand() {
        return "Bridgestone";
    }

    @Override
    public String getTyreProperty() {
        return "";
    }
}
