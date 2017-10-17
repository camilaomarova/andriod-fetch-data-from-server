package camcompany.camnews;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import camcompany.camnews.News;
import com.orm.SugarRecord;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import camcompany.camnews.DetailsActivity;


public class FragmentOne extends Fragment{

    private String TAG = MainActivity.class.getSimpleName();

    ArrayList<HashMap<String, String>> jsonNews;

    List<News> camNews;// = new ArrayList<>();



    public FragmentOne() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        jsonNews = new ArrayList<>();
        camNews = new ArrayList<News>();
        new GetNews().execute();
        //News nw = new News("Facebook reports jumps in profit", "lorem ipsum doler ammet", "23 August, 2017");
        //nw.save();
        //System.out.println(nw.getTitle());
        System.out.println(jsonNews);
        System.out.println("HEEEEEEEEEEEEEEEEEEEEELOOOOOOOOOOOOOOOOOOOOOOOOO");
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //News mainNews = new News(1, "Facebook reports jumps in profit", "lorem ipsum doler ammet", "23 August, 2017");

        View view = inflater.inflate(R.layout.fragment_one, container, false);
        FloatingActionButton plus = (FloatingActionButton) view.findViewById(R.id.plus);
        RecyclerView vNews = (RecyclerView) view.findViewById(R.id.rvNews);
        //news = News.createNewsList();
        NewsAdapter camAdapter = new NewsAdapter(getActivity(), camNews);
        vNews.setAdapter(camAdapter);
        vNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;

    }

    private class GetNews extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String url = "https://jsonblob.com/api/jsonBlob/81e7b872-b303-11e7-9640-5376d47c14b8";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    JSONArray newss = jsonObj.getJSONArray("news");

                    // looping through All News
                    for (int i = 0; i < newss.length(); i++) {
                        JSONObject c = newss.getJSONObject(i);

                        String news_title = c.getString("news_title");
                        String news_date = c.getString("news_date");
                        String news_text = c.getString("news_text");


                        HashMap<String, String> news = new HashMap<>();
                        // adding each child node to HashMap key => value
                        news.put("news_title", news_title);
                        news.put("news_date", news_date);
                        news.put("news_text", news_text);
                        System.out.println(news_text);
                        camNews.add(new News(news_title, news_date, news_text));
                        System.out.println(camNews);
                        // adding contact to contact list
                        jsonNews.add(news);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }

            return null;
        }

    }

}
