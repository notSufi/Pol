package com.example.sufiy_000.pol.classes;

/**
 * Created by Thomas on 30/07/2015.
 */
public class Candidate {
    private String Name;
    private String Party;

    public Candidate(String name, String party) {
        Name = name;
        Party = party;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getParty() {
        return Party;
    }

    public void setParty(String party) {
        Party = party;
    }
}
