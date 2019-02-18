package com.rk.timetable;

import com.rk.timtable.domain.Class;

import java.util.ArrayList;

public class Driver
{
    private static final int POPULATION_SIZE = 10;
    static final double MUTATION_RATE = 0.1;
    static final double CROSSOVER_RATE = 0.9;
    static final int TOURNAMENT_SELECTION_SIZE = 3;
    static final int NUMB_OF_ELITE_SCHEDULES = 1;

    private Data data;
    private int scheduleNumb = 0;
    private int classNumb = 1;

    public static void main(String[] args)
    {
        Driver driver = new Driver();
        driver.data = new Data();
        int generationNumber = 0;
        driver.data.initialize();

        driver.printAvailableData();
        System.out.println(">Generation #" + generationNumber);
        System.out.println("schedule #|");
        System.out.println("Classes[dept,class,room,instructor,meeting-time] | Fitness | Conflicts\n");
        System.out.print("--------------------------------------------------------------\n");

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data).sortByFitness();
        population.getSchedules().forEach(schedule -> System.out.println("      " + driver.scheduleNumb++ + "|" + "" + schedule + "|" + String.format("%.5f", schedule.getFitness()) + "|" + schedule.getNumberOfConflicts()));
        driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);

        while (population.getSchedules().get(0).getFitness() != 1.0)
        {
            System.out.println(">Generation #" + ++generationNumber);
            System.out.println("schedule #|");
            System.out.println("Classes[dept,class,room,instructor,meeting-time] | Fitness | Conflicts\n");
            System.out.print("--------------------------------------------------------------\n");
            population=geneticAlgorithm.evolve(population).sortByFitness();

            population.getSchedules().forEach(schedule -> System.out.println("      " + driver.scheduleNumb++ + "|" + "" + schedule + "|" + String.format("%.5f", schedule.getFitness()) + "|" + schedule.getNumberOfConflicts()));
            driver.printScheduleAsTable(population.getSchedules().get(0), generationNumber);

            driver.classNumb=1;
        }
    }

    private void printScheduleAsTable(Schedule schedule, int generation)
    {
        ArrayList<Class> classes = schedule.getClasses();
        System.out.println();
        System.out.println("class # | Dept |Course(number,max students) | Room(capacity) | Instructor(id) | Meeting Time(id)");

        System.out.print("--------------------------------------------------------------\n");

        classes.forEach(x -> {
            int majorIndex = data.getDepartments().indexOf(x.getDept());
            int courseIndex = data.getCourse().indexOf(x.getCourse());
            int roomsIndex = data.getRooms().indexOf(x.getRoom());
            int instructorIndex = data.getInstructors().indexOf(x.getInstructor());
            int meetingIndex = data.getMeetingTimes().indexOf(x.getMeetingTime());

            System.out.print(String.format("%1$02d", classNumb) + "     |");
            System.out.print("\t");
            System.out.print(String.format("%s", data.getDepartments().get(majorIndex).getName()) + " | ");

            System.out.print(String.format("%1$21s", data.getCourse().get(courseIndex).getName() + "," + data.getCourse().get(courseIndex).getNumber() + "," + data.getCourse().get(courseIndex).getMaxNumberOfStudents()) + " | ");

            System.out.print(String.format("%1$10s", data.getRooms().get(roomsIndex).getNumber() + " (" + data.getRooms().get(roomsIndex).getSeatingCapacity()) + ") | ");

            System.out.print(String.format("%1$15s", data.getInstructors().get(instructorIndex).getName() + "," + data.getInstructors().get(instructorIndex).getId()) + ") | ");

            System.out.println(data.getMeetingTimes().get(meetingIndex).getTime() + "," + data.getMeetingTimes().get(meetingIndex).getId() + ") | ");

            classNumb++;
        });

        if (schedule.getFitness() == 1)
        {
            System.out.println("solution  found in " + (generation + 1) + " generation");
        }
    }


    private void printAvailableData()
    {
        System.out.println("available departments==>");
        data.getDepartments().forEach(x -> System.out.println("name:" + x.getName() + "courses" + x.getCours()));
        System.out.println("\nAvailable courses==>");
        data.getCourse().forEach(x -> System.out.println("course #:" + x.getNumber() + ",name:" + x.getName() + ",max num of students:" + x.getMaxNumberOfStudents() + ",instructors:" + x.getInstructors()));
        System.out.println("\nAvailable rooms==>");
        data.getRooms().forEach(x -> System.out.println("room #:" + x.getNumber() + "max seating capacity" + x.getSeatingCapacity()));
        System.out.println("\nAvailable instructors==>");
        data.getInstructors().forEach(x -> System.out.println("id:" + x.getId() + "name:" + x.getName()));
        System.out.println("\nAvailable Meeting Times==>");
        data.getMeetingTimes().forEach(x -> System.out.println("id:" + x.getId() + "Meeting Time:" + x.getTime()));
    }
}
