package camcompany.camnews;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title_detail");
        //findWithQuery(ExerciseDB.class, "Select * from Ex_Records where ex_recordId = ?", "ex0001");
        System.out.println(title);
        List<News> nDetail = News.findWithQuery(News.class, "SELECT * from NEWS where newstitle = ?", title);

        TextView titleText = (TextView) findViewById(R.id.detail_title);
        titleText.setText(nDetail.get(0).getTitle());
        TextView dateText = (TextView) findViewById(R.id.detail_date);
        dateText.setText(nDetail.get(0).getDate());
        TextView textText = (TextView) findViewById(R.id.detail_text);
        textText.setText(nDetail.get(0).getText());

        FloatingActionButton like = (FloatingActionButton) findViewById(R.id.like);
    }


}
