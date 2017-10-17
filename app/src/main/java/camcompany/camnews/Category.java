package camcompany.camnews;

import java.util.ArrayList;

/**
 * Created by Camila on 10/6/17.
 */

public class Category {
    String cTitle;

    public Category(){

    }

    public Category(String cTitle) {
        this.cTitle = cTitle;
    }

    public String getcTitle() {
        return this.cTitle;
    }


    public static ArrayList<Category> createCategoryList() {
        ArrayList<Category> category = new ArrayList<Category>();

        category.add(new Category("Sport"));
        category.add(new Category("Business"));
        category.add(new Category("Math"));
        category.add(new Category("Android"));
        category.add(new Category("iOS"));
        category.add(new Category("WEB"));
        return category;
    }

}
