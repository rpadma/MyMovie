package f1.com.example.rohit.homework2;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Editfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Editfragment extends Fragment {

   static Movie m;
   static int position;
    private IEdit mListener;

    public Editfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle b=this.getArguments();
        m=(Movie) b.getSerializable("efeditmovie");
        position=Integer.valueOf(b.getString("position"));

        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_editfragment, container, false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.movie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SeekBar esb=(SeekBar)v.findViewById(R.id.editSbrating);
        Spinner esp =(Spinner)v.findViewById(R.id.editspgenre);
        esp.setAdapter(adapter);
        esb.setProgress(m.getRating());
        esp.setSelection(adapter.getPosition(m.getGenre()));
        ((EditText)v.findViewById(R.id.editedtxtimdblink)).setText(m.getImdb());
        ((EditText)v.findViewById(R.id.editedtxtyear)).setText(String.valueOf(m.getYear()));
        ((EditText)v.findViewById(R.id.editedtxdesc)).setText(m.getDescription());
        ((EditText)v.findViewById(R.id.editedtxtname)).setText(m.getName());

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v=getView();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),
                R.array.movie_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        ((Button)v.findViewById(R.id.btneditmovie)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Movie m1 = new Movie();
                View v2=getView();


                String name = ((EditText) v2.findViewById(R.id.editedtxtname)).getText().toString();
                String descp = ((EditText) v2.findViewById(R.id.editedtxdesc)).getText().toString();
                //  m1.setGenre(esp.getSelectedItem().toString());
                String imdbs = ((EditText) v2.findViewById(R.id.editedtxtimdblink)).getText().toString();
                //m1.setRating( Integer.valueOf(esb.getProgress()));
                int year = Integer.valueOf(((EditText) v2.findViewById(R.id.editedtxtyear)).getText().toString());
                int spinnerid=((Spinner)v2.findViewById(R.id.editspgenre)).getSelectedItemPosition();
                if (name.length() > 0 && descp.length() > 0 && imdbs.length() > 0 && year > 1900 && spinnerid>0) {

                    m1.setName(((EditText) v2.findViewById(R.id.editedtxtname)).getText().toString());
                    m1.setDescription(((EditText) v2.findViewById(R.id.editedtxdesc)).getText().toString());
                    m1.setGenre(((Spinner)v2.findViewById(R.id.editspgenre)).getSelectedItem().toString());
                    m1.setImdb(((EditText) v2.findViewById(R.id.editedtxtimdblink)).getText().toString().trim());
                    m1.setRating(Integer.valueOf(((SeekBar)v2.findViewById(R.id.editSbrating)).getProgress()));
                    m1.setYear(Integer.valueOf(((EditText) v2.findViewById(R.id.editedtxtyear)).getText().toString().trim()));


                    mListener.sendeditedmovie(m1,position);
                }
                else
                {
                    Toast.makeText(v.getContext(), "Enter all the fields", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IEdit) {
            mListener = (IEdit) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public  interface IEdit {
        // TODO: Update argument type and name
        void sendeditedmovie(Movie m,int p);
    }
}
