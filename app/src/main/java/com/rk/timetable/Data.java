package com.rk.timetable;

import com.rk.timtable.domain.Course;
import com.rk.timtable.domain.Department;
import com.rk.timtable.domain.Instructor;
import com.rk.timtable.domain.MeetingTime;
import com.rk.timtable.domain.Room;

import java.util.ArrayList;
import java.util.Arrays;

class Data
{
    private ArrayList<Room> rooms;
    private ArrayList<Instructor> instructors;
    private ArrayList<Course> course;
    private ArrayList<Department> departments;
    private ArrayList<MeetingTime> meetingTimes;
    private int numberOfClasses = 0;

    void initialize()
    {
        //rooms
        Room room1=new Room("cse-a",50);
        Room room2=new Room("cse-b",50);
        rooms=new ArrayList<Room>(Arrays.asList(room1,room2));

        //meeting times
        MeetingTime meetingTime1=new MeetingTime("MT1","MWF 09:00 - 10:00");
        MeetingTime meetingTime2=new MeetingTime("MT2","MWF 10:00 - 11:00");
        MeetingTime meetingTime3=new MeetingTime("MT3","MWF 11:10 - 12:45");
        meetingTimes=new ArrayList<MeetingTime>(Arrays.asList(meetingTime1,meetingTime2,meetingTime3));

        //instructors
        Instructor instructor1=new Instructor("I1","Srikanth");
        Instructor instructor2=new Instructor("I2","Kusuma");
        Instructor instructor3=new Instructor("I3","Abishek");
        Instructor instructor4=new Instructor("I4","Krishna raj");
        instructors=new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4));

        //course
        Course course1 =new Course("HCI","1k",new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4)),50);
        Course course2 =new Course("SNSW","2k",new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4)),50);
        Course course3 =new Course("MS","2k",new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4)),50);
        Course course4 =new Course("DS","2k",new ArrayList<>(Arrays.asList(instructor1,instructor2,instructor3,instructor4)),50);

        course =new ArrayList<>(Arrays.asList(course1, course2, course3, course4));

        //Departments
        Department department1=new Department("CSE",new ArrayList<>(Arrays.asList(course1, course2,course3, course4)));
        departments=new ArrayList<>(Arrays.asList(department1));
        departments.forEach(x->numberOfClasses+=x.getCours().size());
    }

    ArrayList<Room> getRooms()
    {
        return rooms;
    }

    ArrayList<Instructor> getInstructors()
    {
        return instructors;
    }

    ArrayList<Course> getCourse()
    {
        return course;
    }

    ArrayList<Department> getDepartments()
    {
        return departments;
    }

    ArrayList<MeetingTime> getMeetingTimes()
    {
        return meetingTimes;
    }

    int getNumberOfClasses()
    {
        return numberOfClasses;
    }
}
