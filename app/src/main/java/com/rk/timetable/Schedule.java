package com.rk.timetable;

import com.rk.timtable.domain.Class;
import com.rk.timtable.domain.Course;
import com.rk.timtable.domain.Department;

import java.util.ArrayList;

public class Schedule
{
    private ArrayList<Department> dept;
    private ArrayList<Course> course;
    private ArrayList<Class> classes;
    private Data data;
    private int classNumb = 0;
    private int numberOfConflicts = 0;

    //fitness
    private boolean isFitnessChanged = true;
    private double fitness = -1;


    Schedule(Data data)
    {
        this.data = data;
        classes = new ArrayList<Class>(data.getNumberOfClasses());
    }

    public Data getData()
    {
        return data;
    }

    Schedule initialize()
    {
        new ArrayList<>(data.getDepartments()).forEach(dept -> {
            dept.getCours().forEach(course -> {
                Class newClass = new Class(classNumb++, dept, course);
                newClass.setMeetingTime(data.getMeetingTimes().get((int) (data.getMeetingTimes().size() * Math.random())));
                newClass.setRoom(data.getRooms().get((int) (data.getRooms().size() * Math.random())));
                newClass.setInstructor(data.getInstructors().get((int) (data.getInstructors().size() * Math.random())));

                classes.add(newClass);
            });
        });

        return this;
    }

    ArrayList<Class> getClasses()
    {
        isFitnessChanged = true;
        return classes;
    }

    int getNumberOfConflicts()
    {
        return numberOfConflicts;
    }

    double getFitness()
    {
        if (isFitnessChanged) fitness = calculateFitness();

        isFitnessChanged = false;
        return fitness;
    }



    private double calculateFitness()
    {

        numberOfConflicts = 0;
        classes.forEach(x -> {
            if (x.getRoom().getSeatingCapacity() < x.getCourse().getMaxNumberOfStudents())
                numberOfConflicts++;

            classes.stream().filter(y -> classes.indexOf(y) >= classes.indexOf(x)).forEach(y -> {
                if (x.getMeetingTime() == y.getMeetingTime() && x.getId() != y.getId())
                {
                    if (x.getRoom() == y.getRoom()) numberOfConflicts++;
                    if (x.getInstructor() == y.getInstructor()) numberOfConflicts++;
                }
            });
        });

        return 1 / (double) (numberOfConflicts + 1);
    }


    public String toString()
    {
        StringBuilder returnValue = new StringBuilder();

        for (int x = 0; x < classes.size() - 1; x++)
        {
            returnValue.append(classes.get(x)).append(",");
        }

        returnValue.append(classes.get(classes.size() - 1)).append(",");
        return returnValue.toString();
    }
}
