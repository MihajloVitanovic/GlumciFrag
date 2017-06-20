package com.mvit.mihajlo.glumcifrag.fragments;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.mvit.mihajlo.glumcifrag.R;
import com.mvit.mihajlo.glumcifrag.activities.MainActivity;
import com.mvit.mihajlo.glumcifrag.db.model.Category;
import com.mvit.mihajlo.glumcifrag.db.model.DatabaseHelper;
import com.mvit.mihajlo.glumcifrag.db.model.Product;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihajlo on 17-Jun-17.
 */


public class DetailFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private static int NOTIFICATION_ID = 1;
    private DatabaseHelper databaseHelper;
    private Product product = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        try {
//            if (product == null) { product = ((MainActivity)getActivity()).getDatabaseHelper().getProductDao().queryForId(0); }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null) {
            product = new Product();
            product.setmId(savedInstanceState.getInt("id"));
            product.setmName(savedInstanceState.getString("name"));
            product.setmLastName(savedInstanceState.getString("lastname"));
            product.setmYearOfBirth(savedInstanceState.getString("yearofbirth"));
            product.setDescription(savedInstanceState.getString("description"));
            product.setRating(savedInstanceState.getFloat("rating"));
            product.setImage(savedInstanceState.getString("image"));
            product.setMovieList(savedInstanceState.getString("movielist"));

            product.setMovieName(savedInstanceState.getString("moviename"));
            product.setMovieGenre(savedInstanceState.getString("moviegenre"));
            product.setMovieReleaseDate(savedInstanceState.getString("moviereleasedate"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            savedInstanceState.putInt("id", product.getmId());
            savedInstanceState.putString("name", product.getmName());
            savedInstanceState.putString("lastname", product.getmLastName());
            savedInstanceState.putString("yearofbirth", product.getmYearOfBirth());
            savedInstanceState.putString("description", product.getDescription());
            savedInstanceState.putFloat("rating", product.getRating());
            savedInstanceState.putString("image", product.getImage());
            savedInstanceState.putString("movielist", product.getMovieList());

            savedInstanceState.putString("moviename", product.getMovieName());
            savedInstanceState.putString("moviegenre", product.getMovieGenre());
            savedInstanceState.putString("moviereleasedate", product.getMovieReleaseDate());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.v("DetailFragment", "onCreateView()");

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.detail_fragment, container, false);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(product.getmName());

        TextView lastName = (TextView) view.findViewById(R.id.lastName);
        lastName.setText(product.getmLastName());

        TextView yearOfBirth = (TextView) view.findViewById(R.id.yearOfBirth);
        yearOfBirth.setText(product.getmYearOfBirth());

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(product.getDescription());

        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.rating);
        ratingBar.setRating(product.getRating());

        /**
         *
         * sd
         *
         *
         *
         *
         *
         *
         *
         *
         *
         **/

        /*ListView movieListView = (ListView) view.findViewById(R.id.movieList);
        //final EditText editText = (EditText) dialog.findViewById(R.id.product_movieList);
        List<String> stringsList = new ArrayList<>();
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stringsList);
        movieListView.setAdapter(adapter);*/

        ImageView imageView = (ImageView) view.findViewById(R.id.image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(product.getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Spinner spinner = (Spinner) view.findViewById(R.id.category);
        try {
            List<Category> list = ((MainActivity) getActivity()).getDatabaseHelper().getCategoryDao().queryForAll();
            ArrayAdapter<Category> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
            spinner.setAdapter(dataAdapter);

            for (int i=0;i<list.size();i++){
                if (list.get(i).getId() == product.getCategory().getId()){
                    spinner.setSelection(i);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return view;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // You can retrieve the selected item using
        //product.setCategory(CategoryProvider.getCategoryById((int)id));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //product.setCategory(null);
    }

    /**
     * Kada dodajemo novi element u toolbar potrebno je da obrisemo prethodne elmente
     * zato pozivamo menu.clear() i dodajemo nove toolbar elemente
     * */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.detail_fragment_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    //da bi dodali podatak u bazu, potrebno je da napravimo objekat klase
    //koji reprezentuje tabelu i popunimo podacima
    private void doAddItem() throws SQLException {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_detail);

        /*final ListView listView = (ListView) dialog.findViewById(R.id.movieList);
        final EditText editText = (EditText) dialog.findViewById(R.id.product_movieList);
        final List<String> strings = new ArrayList<>();
        final ArrayAdapter<String> adapter =  new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(adapter);
        listView.setSelection(0);*/

        final EditText movieName = (EditText) dialog.findViewById(R.id.movie_name);
        final EditText movieGenre = (EditText) dialog.findViewById(R.id.movie_genre);
        final EditText movieReleaseDate = (EditText) dialog.findViewById(R.id.movie_releasedate);

        Button ok = (Button) dialog.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = movieName.getText().toString();
                String genre = movieGenre.getText().toString();
                String releaseDate = movieReleaseDate.getText().toString();


                /*String movietext = editText.getText().toString();
                // Now add it to the list
                strings.add(movietext);
                // And finally, update the list
                adapter.notifyDataSetChanged();*/


                Product movies = new Product();
                movies.setMovieName(name);
                movies.setMovieGenre(genre);
                movies.setMovieReleaseDate(releaseDate);
                //product.setMovieList(text);


                ListView movieListView = (ListView) getView().findViewById(R.id.movieList);
                //final EditText editText = (EditText) dialog.findViewById(R.id.product_movieList);
                //List<String> stringsList = new ArrayList<>();
                String[] stringsList = {
                        "Name: "+name+
                        " Genre: "+genre+
                        " Release Date: "+releaseDate
                };
                ArrayAdapter<String> adapter =  new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, stringsList);
                movieListView.setAdapter(adapter);


                try {
                    getDatabaseHelper().getProductDao().create(movies);
                    //refresh();
                    Toast.makeText(getActivity(), "Movie inserted", Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button cancel = (Button) dialog.findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void doEditElement() {
        Toast.makeText(getActivity(), "Edit Executed", Toast.LENGTH_SHORT).show();
    }

    public void updateProduct(Product product) {
        this.product = product;

        EditText name = (EditText) getActivity().findViewById(R.id.name);
        name.setText(product.getmName());

        EditText lastName = (EditText) getActivity().findViewById(R.id.lastName);
        lastName.setText(product.getmLastName());

        TextView yearOfBirth = (TextView) getActivity().findViewById(R.id.yearOfBirth);
        yearOfBirth.setText(product.getmYearOfBirth());

        EditText description = (EditText) getActivity().findViewById(R.id.description);
        description.setText(product.getDescription());

        RatingBar ratingBar = (RatingBar) getActivity().findViewById(R.id.rating);
        ratingBar.setRating(product.getRating());

        ListView movieList = (ListView) getActivity().findViewById(R.id.movieList);
        String mlts = movieList.toString();
        product.setMovieList(mlts);

        ImageView imageView = (ImageView) getActivity().findViewById(R.id.image);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(product.getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doUpdateElement(){
        if (product != null){
            EditText name = (EditText) getActivity().findViewById(R.id.name);
            product.setmName(name.getText().toString());

            EditText lastName = (EditText) getActivity().findViewById(R.id.lastName);
            product.setmLastName(lastName.getText().toString());

            TextView yearOfBirth = (TextView) getActivity().findViewById(R.id.yearOfBirth);
            product.setmYearOfBirth(yearOfBirth.getText().toString());

            EditText description = (EditText) getActivity().findViewById(R.id.description);
            product.setDescription(description.getText().toString());

            RatingBar ratingBar = (RatingBar) getActivity().findViewById(R.id.rating);
            product.setRating(ratingBar.getRating());

            Spinner category = (Spinner) getActivity().findViewById(R.id.category);
            Category c = (Category)category.getSelectedItem();
            product.setCategory(c);

            ListView movieList = (ListView) getActivity().findViewById(R.id.movieList);
            String mlts = movieList.toString();
            product.setMovieList(mlts);

            try {
                ((MainActivity) getActivity()).getDatabaseHelper().getProductDao().update(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getActivity().onBackPressed();
        }
    }

    private void doRemoveElement(){
        if (product != null) {
            try {
                ((MainActivity) getActivity()).getDatabaseHelper().getProductDao().delete(product);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            getActivity().onBackPressed();
        }
    }

    /**
     * Na fragment dodajemo element za brisanje elementa i za izmenu podataka
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                try {
                    doAddItem();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.edit:
                doEditElement();
                break;
            case R.id.update:
                doUpdateElement();
                break;
            case R.id.remove:
                doRemoveElement();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    // refresh() prikazuje novi sadrzaj.Povucemo nov sadrzaj iz baze i popunimo listu
    private void refresh() {
        ListView listview = (ListView) getView().findViewById(R.id.products);

        if (listview != null){
            ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) listview.getAdapter();

            if(adapter!= null)
            {
                try {
                    adapter.clear();
                    List<Product> list = getDatabaseHelper().getProductDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //Metoda koja komunicira sa bazom podataka
    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(getActivity(), DatabaseHelper.class);
        }
        return databaseHelper;
    }


    /*@Override
    public void onDestroy() {
        super.onDestroy();

        // nakon rada sa bazo podataka potrebno je obavezno
        //osloboditi resurse!
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }*/

}
