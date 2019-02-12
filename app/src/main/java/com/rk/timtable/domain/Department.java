package com.rk.timtable.domain;

import java.util.ArrayList;

public class Department
{
    private String name;
    private ArrayList<Course> cours;

    public Department(String name, ArrayList<Course> cours)
    {
        this.name = name;
        this.cours = cours;
    }

    public String getName()
    {
        return name;
    }

    public ArrayList<Course> getCours()
    {
        return cours;
    }
}
