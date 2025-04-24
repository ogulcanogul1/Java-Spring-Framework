package com.example.beans.tyres;

import com.example.interfaces.Tyre;

public class MichelinTyres implements Tyre {

    @Override
    public String getTyreBrand() {
        return "Michelin";
    }

    @Override
    public String getTyreProperty() {
        return "";
    }
}
