package f1.com.example.rohit.homework2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Addfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Addfragment extends Fragment {

    private IOnAdded mListener;
    SeekBar sk;
    Spinner sp;

    View v1;
    public Addfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View v= inflater.inflate(R.layout.fragment_addfragment, container, false);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final View view =getView();

        sk = (SeekBar) view.findViewById(R.id.Sbrating);
        int rating = sk.getProgress();
        ((TextView) view.findViewById(R.id.ratingval)).setText(String.valueOf(rating));
        sp = (Spinner) view.findViewById(R.id.spgenre);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.movie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ((TextView)view.findViewById(R.id.ratingval)).setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });


        view.findViewById(R.id.btnaddmovie).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v1=getView();

                String name = ((EditText) v1.findViewById(R.id.edtxtname)).getText().toString().trim();
                String Desc = ((EditText) v1.findViewById(R.id.edtxdesc)).getText().toString().trim();
                int year = Integer.valueOf(((EditText) v1.findViewById(R.id.edtxtyear)).getText().toString().trim());
                String genre = ((Spinner) v1.findViewById(R.id.spgenre)).getSelectedItem().toString().trim();
                String imdb = ((EditText) v1.findViewById(R.id.edtxtimdblink)).getText().toString().trim();
                int rating = Integer.valueOf(((SeekBar) v1.findViewById(R.id.Sbrating)).getProgress());
                int spinnerid=((Spinner) v1.findViewById(R.id.spgenre)).getSelectedItemPosition();
                if (name.length() > 0 && Desc.length() > 0 && year > 1900 && imdb.length() > 0 && spinnerid>0) {

                    Movie movie = new Movie(name, Desc, genre, imdb, year, rating);
                    mListener.saddmovie(movie);
                }
                else
                {
                    Toast.makeText(v1.getContext(), "Enter all the fields", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IOnAdded) {
            mListener = (IOnAdded) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface  IOnAdded{
        void saddmovie(Movie m);
    }
}
