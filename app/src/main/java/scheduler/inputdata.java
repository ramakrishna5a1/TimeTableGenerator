package scheduler;

import android.util.Log;

import java.util.Scanner;
import java.util.StringTokenizer;

class InputData
{
    static StudentGroup[] studentgroup;
    static Teacher[] teacher;
    static double crossoverrate = 1.0, mutationrate = 0.1;
    static int nostudentgroup, noteacher;
    static int hoursperday, daysperweek;

    InputData(int hoursOfDay)
    {
        studentgroup = new StudentGroup[10];
        teacher = new Teacher[100];
        InputData.hoursperday =hoursOfDay;
    }

    boolean classFormat(String l)
    {
        StringTokenizer st = new StringTokenizer(l, " ");
        return st.countTokens() == 3;
    }

    void takeInput(String input)
    {
        daysperweek = 6;

        try
        {
            Scanner scanner = new Scanner("studentgroups\n" + input + "end");

            Log.i("Input data", "" + "studentgroups\n" + input);
            while (scanner.hasNextLine())
            {
                String line = scanner.nextLine();

                // input student groups
                if (line.equalsIgnoreCase("studentgroups"))
                {
                    int i = 0, j;

                    while (!(line = scanner.nextLine()).equalsIgnoreCase("teachers"))
                    {
                        studentgroup[i] = new StudentGroup();
                        StringTokenizer st = new StringTokenizer(line, "#");

                        studentgroup[i].id = i;
                        studentgroup[i].name = st.nextToken();
                        studentgroup[i].noSubject = 0;

                        j = 0;
                        while (st.hasMoreTokens())
                        {
                            studentgroup[i].subject[j] = st.nextToken();
                            studentgroup[i].hours[j++] = Integer.parseInt(st.nextToken());
                            studentgroup[i].noSubject++;
                        }
                        i++;
                    }

                    nostudentgroup = i;
                }

                //input teachers
                if (line.equalsIgnoreCase("teachers"))
                {
                    int i = 0;
                    while (!(line = scanner.nextLine()).equalsIgnoreCase("end"))
                    {
                        teacher[i] = new Teacher();
                        StringTokenizer st = new StringTokenizer(line, "#");
                        teacher[i].id = i;
                        teacher[i].name = st.nextToken();
                        teacher[i].subject = st.nextToken();

                        //Log.i("Teachers",""+ teacher[i].toString());
                        i++;
                    }
                    noteacher = i;
                }
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        assignTeacher();
    }

    // assigning a teacher for each subject for every studentgroup
    private void assignTeacher()
    {
        // looping through every studentgroup
        for (int i = 0; i < nostudentgroup; i++)
        {

            // looping through every subject of a student group
            for (int j = 0; j < studentgroup[i].noSubject; j++)
            {
                int teacherid = -1;
                int assignedmin = -1;

                String subject = studentgroup[i].subject[j];

                // looping through every teacher to find which teacher teaches the current subject
                for (int k = 0; k < noteacher; k++)
                {

                    // if such teacher found,checking if he should be assigned the subject or some other teacher based on prior assignments
                    if (teacher[k].subject.equalsIgnoreCase(subject))
                    {
                        // if first teacher found for this subject
                        if (assignedmin == -1)
                        {
                            assignedmin = teacher[k].assigned;
                            teacherid = k;
                        }
                        // if teacher found has less no of pre assignments than the teacher assigned for this subject
                        else if (assignedmin > teacher[k].assigned)
                        {
                            assignedmin = teacher[k].assigned;
                            teacherid = k;
                        }
                    }
                }

                // 'assigned' variable for selected teacher incremented
                teacher[teacherid].assigned++;
                studentgroup[i].teacherId[j] = teacherid;
            }
        }
    }
}

