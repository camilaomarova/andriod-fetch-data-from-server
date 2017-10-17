package camcompany.camnews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView dateTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.news_title);
            dateTextView = (TextView) itemView.findViewById(R.id.news_date);
        }
    }
    private List<News> camNews;
    private Context camContext;

    public NewsAdapter(Context context, List<News> news) {
        camNews = news;
        camContext = context;
    }
    private Context getContext() {
        return camContext;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View newsView = inflater.inflate(R.layout.news, parent, false);

        ViewHolder viewHolder = new ViewHolder(newsView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder viewHolder, int position) {
        News news = camNews.get(position);

        TextView textView = viewHolder.titleTextView;
        textView.setText(news.getTitle());

        TextView textView1 = viewHolder.dateTextView;
        textView1.setText(news.getDate());

    }

    @Override
    public int getItemCount() {
        return camNews.size();
    }
}
