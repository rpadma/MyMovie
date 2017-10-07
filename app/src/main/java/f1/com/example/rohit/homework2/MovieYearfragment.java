package f1.com.example.rohit.homework2;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieYearfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MovieYearfragment extends Fragment {

    private Iyear mListener;

    static int curr;
    ArrayList<Movie> mlist=new ArrayList<Movie>();
    public MovieYearfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        Bundle b=this.getArguments();
        mlist=(ArrayList<Movie>) b.getSerializable("orderbyyear");


        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_movie_yearfragment, container, false);


    return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        View v=getView();
        binddate(v,0);

((Button)v.findViewById(R.id.btnmyfirst)).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        if(curr==0)
        {
            Toast.makeText(getContext(),"Your on first movie",Toast.LENGTH_SHORT).show();
        }
        else {
            curr = 0;
            binddate(getView(),curr);
        }

    }
});

        ((Button)v.findViewById(R.id.btnmyprevious)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(curr==0)
                {
                    Toast.makeText(getContext(),"Your on first movie",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    curr=curr-1;
                    binddate(getView(),curr);
                }
            }
        });

        ((Button)v.findViewById(R.id.btnmynext)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(curr==mlist.size()-1)
                {
                    Toast.makeText(getContext(),"Your on last movie",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    curr=curr+1;
                    binddate(getView(),curr);           }

            }
        });

        ((Button)v.findViewById(R.id.btnmylast)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(curr==mlist.size()-1)
                {
                    Toast.makeText(v.getContext(),"Your on last movie",Toast.LENGTH_SHORT).show();
                }
                else {
                    curr = mlist.size() - 1;
                    binddate(getView(),curr);
                }
            }
        });


        ((Button)v.findViewById(R.id.btnmyfinish)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              mListener.onfinishclick();
            }
        });



    }


    public void binddate(View v,int position)
    {
        Movie m=mlist.get(position);

        ((TextView)v.findViewById(R.id.mygrating)).setText(String.valueOf(m.rating)+"/5");
        ((TextView)v.findViewById(R.id.mygenre)).setText(m.genre);
        ((TextView)v.findViewById(R.id.myimdb)).setText(m.imdb);
        ((TextView)v.findViewById(R.id.mydesc)).setText(m.description);
        ((TextView)v.findViewById(R.id.mytitle)).setText(m.name);
        ((TextView)v.findViewById(R.id.myyear)).setText(String.valueOf(m.year));

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Iyear) {
            mListener = (Iyear) context;
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
    public interface Iyear {
        // TODO: Update argument type and name
        void onfinishclick();
    }
}
