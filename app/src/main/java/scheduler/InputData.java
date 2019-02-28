package scheduler;

import java.util.Scanner;
import java.util.StringTokenizer;

class InputData
{
    static StudentGroup[] studentgroup;
    static Teacher[] teacher;
    static double crossoverrate = 1.0, mutationrate = 0.1;
    static int nostudentgroup, noteacher;
    static int hoursperday, daysperweek;

    InputData()
    {
        studentgroup = new StudentGroup[100];
        teacher = new Teacher[100];
    }

    boolean classformat(String l)
    {
        StringTokenizer st = new StringTokenizer(l, " ");
        return st.countTokens() == 3;
    }

    void takeInput()// takes input from file input.txt
    {
        //this method of taking input through file is only for development purpose so hours and days are hard coded
        hoursperday = 7;
        daysperweek = 6;

        try
        {
            //File file = new File("D:\\ANDROID\\TimeTableGenerator\\app\\src\\main\\input.txt");
            Scanner scanner = new Scanner("studentgroups\n" + "CSE(Ist-Year) AC-1101 5 ME-1111 7 EVS-1101 7 AM-1101 3 PC-1101 5\n" +
                    "ECE(Ist-Year) AP-1101 7 ME-1111 4 EVS-1101 3 AM-1101 8 PC-1101 6\n" +
                    "teachers\n" + "O.N.Singh AP-1101\n" + "K.P.Sinha AP-1101\n" + "KumarUtkarsh AM-1101\n" + "A.Gupta AM-1101\n" + "AKT AC-1101\n" + "MAJ AC-1101\n" + "Panda PC-1101\n" + "d PC-1101\n" + "Tyagi EVS-1101\n" + "e EVS-1101\n" + "SKS ME-1111\n" + "f ME-1111\n" + "end");

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
                        StringTokenizer st = new StringTokenizer(line, " ");

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
                        StringTokenizer st = new StringTokenizer(line, " ");
                        teacher[i].id = i;
                        teacher[i].name = st.nextToken();
                        teacher[i].subject = st.nextToken();

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

