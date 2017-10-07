package f1.com.example.rohit.homework2;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements mainfragment.OnFragmentInteractionListener,
        Addfragment.IOnAdded,Editfragment.IEdit,MovieYearfragment.Iyear,MovieRatingfragment.Irate {


    public static final int ADD_REQ=110;
    public static final int EDIT_REQ=111;
    public static final String EDIT_KEY="Editkey";
    public static final String MOVIE_LISTR="Showmovielist";
    public static final String MOVIE_LIST="showmovielist";
    Button btnAdd,btnEdit,btnDelete,btnShowy,btnShowr;
    ArrayList<Movie> movieArrayList=new ArrayList<Movie>();

    CharSequence movienames[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().addToBackStack(null)
                .add(R.id.mainframe,new mainfragment(),"Main_tag").commit();

    }

    @Override
    public void toaddmovie() {

        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainframe,new Addfragment(),"Add_tag").commit();
    }

    @Override
    public void toeditmovie() {

        if(movieArrayList.size()>0) {
            movienames = new CharSequence[movieArrayList.size()];
            int i = 0;

            for (Movie m : movieArrayList) {
                if (m != null) {
                    movienames[i] = m.name;
                    i++;
                }
            }

            final AlertDialog.Builder editalertbuilder = new AlertDialog.Builder(this);
            editalertbuilder.setTitle("Pick a Movie");
            editalertbuilder.setItems(movienames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Movie m = movieArrayList.get(which);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("efeditmovie",m );
                    bundle.putString("position",String.valueOf(which));
                    Editfragment ef = new Editfragment();
                    ef.setArguments(bundle);

                    getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainframe,ef,"edit_tag").commit();


                    //   Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    //  intent.putExtra(EDIT_KEY, m);
                    // movieArrayList.remove(which);
                    //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    // startActivityForResult(intent, EDIT_REQ);

                }
            }).show();

        }
        else
        {
            Toast.makeText(getApplicationContext(),"Movie List is empty",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void toratingmovie() {


        if (movieArrayList.size() > 0) {
            //Intent i = new Intent(this,MovieYearActivity.class);
            //Intent i = new Intent("android.intent.action.VIEW_YEAR");
            ArrayList<Movie> Templist=new ArrayList<Movie>();
            Templist=movieArrayList;
            Collections.sort(Templist, Movie.getMovieByRating());
            // i.putExtra(MOVIE_LIST, Templist);
            //startActivity(i);

            Bundle bundle = new Bundle();
            bundle.putSerializable("orderbyrate",Templist );
            MovieRatingfragment mrf = new MovieRatingfragment();
            mrf.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainframe,mrf,"rate_tag").commit();

        } else {

            Toast.makeText(getApplicationContext(),"Movie List is empty",Toast.LENGTH_SHORT).show();
        }


      }

    @Override
    public void todeletemovie() {


        if(movieArrayList.size()>0) {
            movienames = new CharSequence[movieArrayList.size()];
            int i = 0;

            for (Movie m : movieArrayList) {
                if (m != null) {
                    movienames[i] = m.name;
                    i++;
                }
            }

            final AlertDialog.Builder editalertbuilder = new AlertDialog.Builder(this);
            editalertbuilder.setTitle("Pick a Movie");
            editalertbuilder.setItems(movienames, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    String deletedmname = movieArrayList.get(which).name;
                    movieArrayList.remove(which);

                    Toast.makeText(getApplicationContext(), deletedmname + " - is deleted", Toast.LENGTH_SHORT).show();
                    // Intent intent = new Intent(MainActivity.this, EditActivity.class);
                    // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    //startActivity(intent);
                }
            }).show();

        }
        else {

            Toast.makeText(getApplicationContext(),"Movie List is empty",Toast.LENGTH_SHORT).show();
        }

       // getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainframe,new Dfragment(),"delete_tag").commit();
    }

    @Override
    public void toyearmovie() {

        if (movieArrayList.size() > 0) {
            //Intent i = new Intent(this,MovieYearActivity.class);
            //Intent i = new Intent("android.intent.action.VIEW_YEAR");
            ArrayList<Movie> Templist=new ArrayList<Movie>();
            Templist=movieArrayList;
            Collections.sort(Templist, Movie.getMovieByYear());
           // i.putExtra(MOVIE_LIST, Templist);
            //startActivity(i);

            Bundle bundle = new Bundle();
            bundle.putSerializable("orderbyyear",Templist );
            MovieYearfragment myf = new MovieYearfragment();
            myf.setArguments(bundle);

            getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.mainframe,myf,"year_tag").commit();

        } else {

            Toast.makeText(getApplicationContext(),"Movie List is empty",Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public void saddmovie(Movie m) {
movieArrayList.add(m);
        Toast.makeText(getApplicationContext(),m.getName().toString()+" is added",Toast.LENGTH_SHORT).show();
       // Toast.makeText(getApplicationContext(),String.valueOf(movieArrayList.size()),Toast.LENGTH_SHORT).show();

        getSupportFragmentManager().popBackStack();
    }



    @Override
    public void sendeditedmovie(Movie m,int p) {
        movieArrayList.set(p,m);
getSupportFragmentManager().popBackStack();

        //Toast.makeText(this,m.toString(),Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onfinishclick() {
getSupportFragmentManager().popBackStack();
    }

}
