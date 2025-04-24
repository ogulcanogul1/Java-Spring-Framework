package com.example.beans.Speakers;

import com.example.interfaces.Speaker;

public class SonySpeakers implements Speaker {
    @Override
    public String getSpeakerBrand() {
        return "Sony";
    }
}
