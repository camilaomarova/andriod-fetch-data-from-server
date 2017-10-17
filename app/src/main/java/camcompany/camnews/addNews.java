package camcompany.camnews;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addNews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);

        Button btn = (Button) findViewById(R.id.addNews);
        Button delete = (Button) findViewById(R.id.delete);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText titleEdit = (EditText) findViewById(R.id.addTitle);
                EditText textEdit = (EditText) findViewById(R.id.addText);
                EditText dateText = (EditText) findViewById(R.id.addDate);

                News newNews = new News(titleEdit.getText().toString(), textEdit.getText().toString(), dateText.getText().toString());
                newNews.save();

                //Intent back = new Intent(addNews.class, MainActivity.class);
                //startActivity(back);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                News.deleteAll(News.class);
            }
        });
    }

}
