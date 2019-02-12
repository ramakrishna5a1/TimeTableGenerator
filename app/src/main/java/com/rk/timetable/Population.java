package com.rk.timetable;

import java.util.ArrayList;
import java.util.stream.IntStream;

class Population
{

    private ArrayList<Schedule> schedules;

    Population(int size, Data data)
    {
        schedules = new ArrayList<>(size);
        IntStream.range(0, size).forEach(x -> schedules.add(new Schedule(data).initialize()));
    }

    ArrayList<Schedule> getSchedules()
    {
        return this.schedules;
    }

    Population sortByFitness()
    {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;

            if (schedule1.getFitness() > schedule2.getFitness()) returnValue = -1;
            if (schedule1.getFitness() > schedule2.getFitness()) returnValue = 1;

            return returnValue;
        });

        return this;
    }
}
