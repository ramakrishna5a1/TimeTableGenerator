package scheduler;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import rk.com.users.ShowTimeTable;

//Chromosome represents array of genes as complete timetable (looks like gene[0]gene[1]gene[2]...)
public class Chromosome implements Comparable<Chromosome>, Serializable
{
    static double crossoverrate = InputData.crossoverrate;
    static double mutationrate = InputData.mutationrate;
    public static int hours = InputData.hoursperday, days = InputData.daysperweek;
    public static int nostgrp = InputData.nostudentgroup;
    double fitness;

    Gene[] gene;

    Chromosome()
    {
        gene = new Gene[nostgrp];

        for (int i = 0; i < nostgrp; i++)
        {
            gene[i] = new Gene(i);
            //System.out.println("");
        }

        fitness = getFitness();
    }

    Chromosome deepClone()
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);

            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return (Chromosome) ois.readObject();
        } catch (IOException e)
        {
            return null;
        } catch (ClassNotFoundException e)
        {
            return null;
        }
    }

    double getFitness()
    {
        int point = 0;
        for (int i = 0; i < hours * days; i++)
        {

            List<Integer> teacherList = new ArrayList<>();

            for (int j = 0; j < nostgrp; j++)
            {
                Slot slot;
                //System.out.println("i="+i+" j="+j);
                if (TimeTable.slot[gene[j].slotno[i]] != null)
                    slot = TimeTable.slot[gene[j].slotno[i]];
                else slot = null;

                if (slot != null)
                {
                    if (teacherList.contains(slot.teacherid)) point++;
                    else teacherList.add(slot.teacherid);
                }
            }
        }

        //System.out.println(point);
        fitness = 1 - (point / ((nostgrp - 1.0) * hours * days));
        return fitness;
    }


    //printing final timetable
    void printTimeTable()
    {
        //for each student group separate time table
        for (int i = 0; i < nostgrp; i++)
        {
            //status used to get name of student grp because in case first class is free it will throw error
            boolean status = false;
            int l = 0;

            while (!status)
            {
                //printing name of batch
                if (TimeTable.slot[gene[i].slotno[l]] != null)
                {
                    System.out.println("Batch " + TimeTable.slot[gene[i].slotno[l]].studentgroup.name + " Timetable-");
                    status = true;
                }
                l++;
            }

            //looping for each day
            for (int j = 0; j < days; j++)
            {
                //looping for each hour of the day
                for (int k = 0; k < hours; k++)
                {
                    //checking if this slot is free otherwise printing it
                    if (TimeTable.slot[gene[i].slotno[k + j * hours]] != null)
                    {
                        ShowTimeTable.timeTableData[j][k] = TimeTable.slot[gene[i].slotno[k + j * hours]].subject + " ";
                        System.out.print(TimeTable.slot[gene[i].slotno[k + j * hours]].subject + " ");
                    } else
                    {
                        ShowTimeTable.timeTableData[j][k] = "*FREE* ";
                        System.out.print("*FREE* ");
                    }
                }
                System.out.println("");
            }
            System.out.println("\n");
        }
    }


    void printChromosome()
    {
        for (int i = 0; i < nostgrp; i++)
        {
            for (int j = 0; j < hours * days; j++)
                System.out.print(gene[i].slotno[j] + " ");

            System.out.println("");
        }
    }

    public int compareTo(Chromosome c)
    {
        return Double.compare(c.fitness, fitness);
    }
}

