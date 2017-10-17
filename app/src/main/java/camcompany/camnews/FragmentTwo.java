package camcompany.camnews;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Camila on 10/2/17.
 */

public class FragmentTwo extends Fragment {

    ArrayList<Category> category;
    public FragmentTwo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        RecyclerView vCat = (RecyclerView) view.findViewById(R.id.vCat);
        category = Category.createCategoryList();
        CategoryAdapter camAdapter = new CategoryAdapter(getActivity(), category);
        vCat.setAdapter(camAdapter);
        vCat.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }
}
