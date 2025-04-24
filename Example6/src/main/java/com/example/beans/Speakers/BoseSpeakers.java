package com.example.beans.Speakers;

import com.example.interfaces.Speaker;

public class BoseSpeakers implements Speaker {
    @Override
    public String getSpeakerBrand() {
        return "Bose";
    }

}
