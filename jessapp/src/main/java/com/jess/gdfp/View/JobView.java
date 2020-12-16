
/**
 * @Autor Sakhr Aljendi
 * @Version 1.0
 * 12.03.2020
 */
package com.jess.gdfp.View;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jess.gdfp.DatenBank.JobContract;
import com.jess.gdfp.R;


public class JobView extends AppCompatActivity {
    int index[] = new int[113];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_view);
        String[] jobs = new String[100];
        for (int i = 0; i < 100; i++) {
            jobs[i] = "JOB" + i;
        }

        ListView mylistview = (ListView) findViewById(R.id.listjob);
        ArrayAdapter<String> itemAdpater = new ArrayAdapter<String>(this, R.layout.joblist, R.id.joblistid, jobs);
        mylistview.setAdapter(itemAdpater);

        final String projections[] = new JobsDetails().jobdetails;

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

                Uri uri;
                uri = JobContract.jobEntry.CONTENT_URI;

                  uri.withAppendedPath(uri,"12");

                Cursor cursor = getContentResolver().query(uri, projections, null, null, null);
                System.out.println(cursor);

                try {
                    for (int i = 0; i < 113; i++) {
                        index[i] = cursor.getColumnIndex(projections[i]);
                    }
                    while (cursor.moveToNext()) {

                int currentID = index[0];

                    }

                } finally {
                    cursor.close();
                }
                String meg = String.valueOf(position);
                newActivity(meg);
            }
        });
    }

    public void ExitJobs(View view) {
        JobView.this.finish();
    }

    public void newActivity(String data) {

        Intent intent = new Intent(this, JobsDetails.class);
        intent.putExtra("jobs", data);
        startActivity(intent);
    }
}
