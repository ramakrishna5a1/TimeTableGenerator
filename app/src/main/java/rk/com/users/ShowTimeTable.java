package rk.com.users;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import scheduler.Chromosome;

public class ShowTimeTable extends AppCompatActivity
{


    int ids[][] = {{R.id.cell_11, R.id.cell_12, R.id.cell_13, R.id.cell_14, R.id.cell_15, R.id.cell_16, R.id.cell_17},
            {R.id.cell_21, R.id.cell_22, R.id.cell_23, R.id.cell_24, R.id.cell_25, R.id.cell_26, R.id.cell_27},
            {R.id.cell_31, R.id.cell_32, R.id.cell_33, R.id.cell_34, R.id.cell_35, R.id.cell_36, R.id.cell_37},
            {R.id.cell_41, R.id.cell_42, R.id.cell_43, R.id.cell_44, R.id.cell_45, R.id.cell_46, R.id.cell_47},
            {R.id.cell_51, R.id.cell_52, R.id.cell_53, R.id.cell_54, R.id.cell_55, R.id.cell_56, R.id.cell_57},
            {R.id.cell_61, R.id.cell_62, R.id.cell_63, R.id.cell_64, R.id.cell_65, R.id.cell_66, R.id.cell_67},};
            
    /*
        
    */

    TextView tableCellViews[][] = new TextView[6][7];

    public static String[][] timeTableData = {{"0", "0", "0", "0", "0", "0", "0"}, {"0", "0", "0", "0", "0", "0", "0"}, {"0", "0", "0", "0", "0", "0", "0"}, {"0", "0", "0", "0", "0", "0", "0"}, {"0", "0", "0", "0", "0", "0", "0"}, {"0", "0", "0", "0", "0", "0", "0"}};

    //public static String[][] timeTableData=new String[7][8];
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_time_table);
        
        int i, j;    
        
        /*
            for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++){
                ids[i][j]=R.id.cell_11;
            }
            
            for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++){
               timeTableData[i][j]="0";
            }
            
        */

        if(timeTableData[0][0].equals("0")){
            Toast.makeText(this, "No Timetable published", Toast.LENGTH_SHORT).show();
            finish();
        }
        
        /*
           for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++)
                tableCellViews[i][j] = findViewById(ids[i][j]/* String to Hexa*/); 
         */
        
        //finding the each cell of the table
        for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++)
                tableCellViews[i][j] = findViewById(ids[i][j]);

        //setting text to each cell
        for (i = 0; i < 6; i++)
            for (j = 0; j < 7; j++)
                tableCellViews[i][j].setText(timeTableData[i][j]);
    }
}
