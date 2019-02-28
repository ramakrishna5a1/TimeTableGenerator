package scheduler;

class Utility
{

    static void printInputData()
    {
        System.out.println("Nostgrp=" + InputData.nostudentgroup + " Noteachers=" + InputData.noteacher + " daysperweek=" + InputData.daysperweek + " hoursperday=" + InputData.hoursperday);

        for (int i = 0; i < InputData.nostudentgroup; i++)
        {

            System.out.println(InputData.studentgroup[i].id + " " + InputData.studentgroup[i].name);

            for (int j = 0; j < InputData.studentgroup[i].noSubject; j++)
            {
                System.out.println(InputData.studentgroup[i].subject[j] + " " + InputData.studentgroup[i].hours[j] + " hrs " + InputData.studentgroup[i].teacherId[j]);
            }
            System.out.println();
        }

        for (int i = 0; i < InputData.noteacher; i++)
        {
            System.out.println(InputData.teacher[i].id + " " + InputData.teacher[i].name + " " + InputData.teacher[i].subject + " " + InputData.teacher[i].assigned);
        }
    }


    static void printSlots()
    {

        int days = InputData.daysperweek;
        int hours = InputData.hoursperday;
        int nostgrp = InputData.nostudentgroup;
        System.out.println("----Slots----");
        for (int i = 0; i < days * hours * nostgrp; i++)
        {
            if (TimeTable.slot[i] != null)
                System.out.println(i + "- " + TimeTable.slot[i].studentgroup.name + " " + TimeTable.slot[i].subject + " " + TimeTable.slot[i].teacherid);
            else System.out.println("Free Period");
            if ((i + 1) % (hours * days) == 0) System.out.println("******************************");
        }

    }
}
