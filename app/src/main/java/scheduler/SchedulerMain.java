package scheduler;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SchedulerMain
{

    /*
     * Time Table scheduling is an np-hard problem which can best be solved
     * using Genetic Algorithms (of Artificial Intelligence).
     * Concepts used here are Permutation encoding, elitism, roulette wheel selection,
     * single pt crossover,swap mutation
     */

    private List<Chromosome> firstList;
    private double firstListFitness;
    private int populationSize = 1000;

    public int geneticOperations(Context c){

        InputData i = new InputData();
        i.takeInput();

        //printing input data (on console for testing)
        //Utility.printInputData();

        //generating slots
        new TimeTable();

        //printing slots (testing purpose only)
        //Utility.printSlots();

        //initialising first generation of chromosomes and puting in first arraylist
        initialisePopulation();

        //generating newer generation of chromosomes using crossovers and mutation
        createNewGenerations();

//        Toast.makeText(c,"Generated...",Toast.LENGTH_LONG).show();


        return 1;
    }

    public SchedulerMain()
    {

        InputData i = new InputData();
        i.takeInput();

        //printing input data (on console for testing)
        //Utility.printInputData();

        //generating slots
        new TimeTable();

        //printing slots (testing purpose only)
        //Utility.printSlots();

        //initialising first generation of chromosomes and puting in first arraylist
        initialisePopulation();

        //generating newer generation of chromosomes using crossovers and mutation
        createNewGenerations();

    }

    //Creating new Generations using crossovers and mutations
    private void createNewGenerations()
    {

        Chromosome father = null;
        Chromosome mother = null;
        Chromosome son = null;

        int nogenerations = 0;

        //looping max no of generations times or until suitable chromosome found
        int maxGenerations = 100;
        while (nogenerations < maxGenerations)
        {

            List<Chromosome> newList = new ArrayList<Chromosome>();
            double newListFitness = 0;
            int i = 0;

            //first 1/10 chromosomes added as it is- Elitism
            for (i = 0; i < populationSize / 10; i++)
            {
                newList.add(firstList.get(i).deepClone());
                newListFitness += firstList.get(i).getFitness();
            }

            //adding other members after performing crossover and mutation
            while (i < populationSize)
            {
                father = selectParentRoulette(); //selectParentBest(newList);
                mother = selectParentRoulette(); //selectParentBest(newList);


                //crossover
                if (new Random().nextDouble() < InputData.crossoverrate)
                    son = crossover(father, mother);
                else son = father;

                //mutation customMutation()
                customMutation(son);


                if (son.fitness == 1)
                {
                    System.out.println("Selected Chromosome is:-");
                    son.printChromosome();
                    break;
                }

                newList.add(son);
                newListFitness += son.getFitness();
                i++;
            }

            //if chromosome with fitness 1 found
            if (i < populationSize)
            {
                System.out.println("****************************************************************************************");
                System.out.println("\n\nSuitable Timetable has been generated in the " + i + "th Chromosome of " + (nogenerations + 2) + " generation with fitness 1.");
                System.out.println("\nGenerated Timetable is:");
                son.printTimeTable();
                Chromosome finalSon = son;
                break;
            }

            //if chromosome with required fitness not found in this generation
            firstList = newList;
            Collections.sort(newList);
            Collections.sort(firstList);
            System.out.println("**************************     Generation" + (nogenerations + 2) + "     ********************************************\n");
            printGeneration(newList);
            nogenerations++;
        }
    }

    //selecting using Roulette Wheel Selection only from the best 10% chromosomes
    private Chromosome selectParentRoulette()
    {
        firstListFitness /= 10;
        double randomdouble = new Random().nextDouble() * firstListFitness;
        double currentsum = 0;
        int i = 0;

        while (currentsum <= randomdouble)
            currentsum += firstList.get(i++).getFitness();

        return firstList.get(--i).deepClone();
    }

    //custom mutation
    private void customMutation(Chromosome c)
    {

        double newfitness = 0, oldfitness = c.getFitness();
        int geneno = new Random().nextInt(InputData.nostudentgroup);

        int i = 0;
        while (newfitness < oldfitness)
        {
            //c.printChromosome();
            //System.out.println("getf="+c.getFitness()+" fit= "+c.fitness);

            c.gene[geneno] = new Gene(geneno);
            newfitness = c.getFitness();

            //c.printChromosome();
            //System.out.println("getf="+c.getFitness()+" fit= "+c.fitness);
            i++;
            if (i >= 500000) break;
        }

    }

    //Two point crossover
    private Chromosome crossover(Chromosome father, Chromosome mother)
    {
        int randomInt = new Random().nextInt(InputData.nostudentgroup);
        Gene temp = father.gene[randomInt].deepClone();
        father.gene[randomInt] = mother.gene[randomInt].deepClone();
        mother.gene[randomInt] = temp;
        if (father.getFitness() > mother.getFitness()) return father;
        else return mother;
    }

    //initialising first generation of population
    private void initialisePopulation()
    {
        //generating first generation of chromosomes and keeping them in an arraylist
        firstList = new ArrayList<>();
        firstListFitness = 0;

        for (int i = 0; i < populationSize; i++)
        {
            Chromosome c;
            firstList.add(c = new Chromosome());
            firstListFitness += c.fitness;
        }
        Collections.sort(firstList);
        System.out.println("----------Initial Generation-----------\n");
        printGeneration(firstList);
    }


    //printing important details of a generation
    private void printGeneration(List<Chromosome> list)
    {
        System.out.println("Fetching details from this generation...\n");

        //to print only initial 4 chromosomes of sorted list
        for (int i = 0; i < 4; i++)
        {
            System.out.println("Chromosome no." + i + ": " + list.get(i).getFitness());
            list.get(i).printChromosome();
            System.out.println();
        }

        System.out.println("Chromosome no. " + (populationSize / 10 + 1) + " :" + list.get(populationSize / 10 + 1).getFitness() + "\n");
        System.out.println("Chromosome no. " + (populationSize / 5 + 1) + " :" + list.get(populationSize / 5 + 1).getFitness() + "\n");
        System.out.println("Most fit chromosome from this generation has fitness = " + list.get(0).getFitness() + "\n");
    }


    //selecting from best chromosomes only(alternate to roulette wheel selection)
    public Chromosome selectParentBest(List<Chromosome> list)
    {
        Random r = new Random();
        int randomInt = r.nextInt(100);
        return list.get(randomInt).deepClone();
    }

    //simple Mutation operation
    private void mutation(Chromosome c)
    {
        int geneNo = new Random().nextInt(InputData.nostudentgroup);
        int temp = c.gene[geneNo].slotno[0];
        if (InputData.daysperweek * InputData.hoursperday - 1 >= 0)
            System.arraycopy(c.gene[geneNo].slotno, 1, c.gene[geneNo].slotno, 0, InputData.daysperweek * InputData.hoursperday - 1);
        c.gene[geneNo].slotno[InputData.daysperweek * InputData.hoursperday - 1] = temp;
    }

    //swap mutation
    public void swapMutation(Chromosome c)
    {
        int geneNo = new Random().nextInt(InputData.nostudentgroup);
        int slotNo1 = new Random().nextInt(InputData.hoursperday * InputData.daysperweek);
        int slotNo2 = new Random().nextInt(InputData.hoursperday * InputData.daysperweek);

        int temp = c.gene[geneNo].slotno[slotNo1];
        c.gene[geneNo].slotno[slotNo1] = c.gene[geneNo].slotno[slotNo2];
        c.gene[geneNo].slotno[slotNo2] = temp;
    }

    public static void main(String[] args)
    {
            new SchedulerMain();
    }
}