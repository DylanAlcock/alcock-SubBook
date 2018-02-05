/*
 * Subscription
 *
 * February 5, 2018
 *
 * Copyright (c) 2018 Dylan Alcock, CMPUT301, University of Alberta - All Rights Reserved.
 *  You may use, distribute, or modify this code under terms and conditions of the Code of Student Behaviour at University of Alberta.
 *  You can find a copy of the license on this project, Otherwise please contact alcock@ualberta.ca
 */

package com.example.dylan.alcock_subbook;

import java.io.Serializable;


/**
 * Purpose:
 * Represents a subscription object.
 *
 * Design Rationale:
 * Needed to represent a subscription specified in the outline
 *
 * @author Dylan
 * @version 1.5
 * @see MainActivity
 * @see SubscriptionListAdapter
 */
public class Subscription implements Serializable{

    private String name;
    private String date;
    private double m_charge;
    private String comment;

    /**
     * Purpose:
     * Constructor for the subscription
     *
     * @param name
     * @param date
     * @param m_charge
     * @param comment
     */
    public Subscription(String name, String date, double m_charge, String comment){
        this.name = name;
        this.date = date;
        this.m_charge = m_charge;
        this.comment = comment;
    }

    /**
     * Purpose:
     * Constructor for the subscription without a comment field
     *
     * @param name
     * @param date
     * @param m_charge
     */
    public Subscription(String name, String date, double m_charge){
        this.name = name;
        this.date = date;
        this.m_charge = m_charge;
    }

    /**
     * Purpose:
     * Gets the name of the subscription
     *
     * @return string of subscription name
     */
    public String getName(){
        return name;
    }

    /**
     * Purpose:
     * Gets the date of the subscription
     *
     * @return string of the subscription date
     */
    public String getDate(){
        return date;
    }

    /**
     * Purpose:
     * Gets the charge of the subscription
     *
     * @return doube of the subscription charge
     */
    public double getCharge(){
        return m_charge;
    }

    /**
     * Purpose:
     * Gets the comment of the subscription
     *
     * @return string of the subscription comment
     */
    public String getComment(){
        return comment;
    }

    /**
     * Purpose:
     * Sets the name of the subsciption
     * @param name string of the subscription name
     */
    public void setName(String name) {
        this.name = name;
    }

}
