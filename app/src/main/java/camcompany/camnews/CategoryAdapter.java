package camcompany.camnews;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Camila on 10/6/17.
 */

public class CategoryAdapter  extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView ctitleTextView;


        public ViewHolder(View itemView) {
            super(itemView);
            ctitleTextView = (TextView) itemView.findViewById(R.id.cTitle);
        }
    }
    private List<Category> camCategory;
    private Context camContext;

    public CategoryAdapter(Context context, List<Category> category) {
        camCategory = category;
        camContext = context;
    }
    private Context getContext() {
        return camContext;
    }

    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View categoryView = inflater.inflate(R.layout.category, parent, false);

        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder viewHolder, int position) {
        Category category = camCategory.get(position);

        TextView textView = viewHolder.ctitleTextView;
        textView.setText(category.getcTitle());

    }

    @Override
    public int getItemCount() {
        return camCategory.size();
    }
}
