package com.example.dylan.alcock_subbook;

import android.os.Parcelable;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.Attributes;

/**
 * Created by Dylan on 2018-01-27.
 */

public class Subscription implements Serializable{

    private String name;
    private String date;
    private double m_charge;

    private String comment;
    //ArrayList<Mood> allmoods = new ArrayList<Mood>();


    public Subscription(String name, String date, double m_charge, String comment){
        this.name = name;
        this.date = date;
        this.m_charge = m_charge;
        this.comment = comment;
    }

    public Subscription(String name, String date, double m_charge){
        this.name = name;
        this.date = date;
        this.m_charge = m_charge;
    }

    public String getName(){
        return name;
    }

    public String getDate(){
        return date;
    }

    public double getCharge(){
        return m_charge;
    }

    public String getComment(){
        return comment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setM_charge(double m_charge) {
        this.m_charge = m_charge;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void addSub(String name, Date date, double m_charge)
            throws NameTooLong,NegativeMonthlyCharge{
        if(name.length() > 20){
            // throw an error
            throw new NameTooLong();
        }

        if(m_charge < 0){
            // throw an error
            throw new NegativeMonthlyCharge();
        }
        String simple_date = new SimpleDateFormat("YYYY-MM-DD").format(date);

        this.name = name;
        this.date = simple_date;
        this.m_charge = m_charge;
    }

    public void addSub(String name, Date date, int m_charge, String comment)
            throws NameTooLong,CommentTooLong,NegativeMonthlyCharge{
        if(name.length() > 20){
            // throw an error
            throw new NameTooLong();
        }

        if(comment.length() > 30){
            // throw an error
            throw new CommentTooLong();
        }

        if(m_charge < 0){
            // throw an error
            throw new NegativeMonthlyCharge();
        }

        String simple_date = new SimpleDateFormat("YYYY-MM-DD").format(date);

        this.name = name;
        this.date = simple_date;
        this.m_charge = m_charge;
        this.comment = comment;
    }

}
