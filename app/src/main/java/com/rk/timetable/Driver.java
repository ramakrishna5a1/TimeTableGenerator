package com.rk.timetable;

public class Driver
{
    private static final int POPULATION_SIZE = 5;
    static final double MUTATION_RATE = 0.1;
    static final double CROSSOVER_RATE = 0.9;
    static final int TOURNAMENT_SELECTION_SIZE = 3;
    static final int NUMB_OF_ELITE_SCHEDULES = 1;

    private Data data;
    private int scheduleNumb = 0;

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
        System.out.print("--------------------------------------------------------------");
        System.out.print("--------------------------------------------------------------\n");
        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(driver.data);
        Population population = new Population(Driver.POPULATION_SIZE, driver.data);

        population.getSchedules().forEach(schedule -> System.out.println("      " + driver.scheduleNumb++ + "|" + schedule + "|" + String.format("%.5f", schedule.getFitness()) + "|" + schedule.getNumberOfConflicts()));
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
