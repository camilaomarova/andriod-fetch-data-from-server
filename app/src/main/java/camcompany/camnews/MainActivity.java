package camcompany.camnews;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import camcompany.camnews.FragmentOne;
import camcompany.camnews.FragmentTwo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private String TAG = MainActivity.class.getSimpleName();

    private static String url = "https://jsonblob.com/api/jsonBlob/81e7b872-b303-11e7-9640-5376d47c14b8";

    ArrayList<HashMap<String, String>> jsonNews;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.drawable.menu);


        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new FragmentOne(), "RECENT NEWS");
        adapter.addFragment(new FragmentTwo(), "CATEGORY");
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        System.out.println("MAIN");


        jsonNews = new ArrayList<>();
        System.out.println(jsonNews);
    }



    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    //Attempt to invoke virtual method 'void android.support.v7.widget.RecyclerView.setAdapter(android.support.v7.widget.RecyclerView$Adapter)' on a null object reference

    public void showDetails(View view) {
        Intent intent = new Intent(this, DetailsActivity.class);
        TextView titleDetail = (TextView) view.findViewById(R.id.news_title);
        intent.putExtra("title_detail", titleDetail.getText().toString());
        startActivity(intent);
    }

    public void addNews(View view) {
        Intent addIntent = new Intent(this, addNews.class);
        startActivity(addIntent);
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

                        // adding contact to contact list
                        jsonNews.add(news);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

    }


}
