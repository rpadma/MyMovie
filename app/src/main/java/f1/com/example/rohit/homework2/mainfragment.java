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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link mainfragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class mainfragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    Button btnAdd,btnEdit,btnDelete,btnShowy,btnShowr;
    public mainfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_mainfragment, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.btnadd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toaddmovie();
            }
        });

        getActivity().findViewById(R.id.btndelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.todeletemovie();
            }
        });
        getActivity().findViewById(R.id.btnedit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toeditmovie();
            }
        });
        //getActivity().findViewById(R.id.btndelete);
        getActivity().findViewById(R.id.btnshowlist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toyearmovie();
            }
        });
        getActivity().findViewById(R.id.btnshowlistR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.toratingmovie();
            }
        });



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void toaddmovie();
        void toeditmovie();
        void toratingmovie();
        void todeletemovie();
        void toyearmovie();
    }
}
