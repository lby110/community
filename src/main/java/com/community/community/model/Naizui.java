package com.community.community.model;

import lombok.Data;

@Data
public class Naizui {
    private int id;
    private String parent;
    private String productId;
    private int starRating;
    private int sumHelpfulVotes;
    private int sumTotalVotes;


    @Override
    public String toString() {
        return "Naizui{" +
                "parent='" + parent + '\'' +
                '}';
    }
}
