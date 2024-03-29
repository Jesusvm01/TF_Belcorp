package com.example.navigationdrawerexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragmento2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragmento2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragmento2 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Fragmento2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragmento2.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragmento2 newInstance(String param1, String param2) {
        Fragmento2 fragment = new Fragmento2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragmentView = inflater.inflate(R.layout.fragment_fragmento2, container, false);

        MedicionDAO dao = new MedicionDAO(getActivity().getBaseContext());
        try {
            ArrayList<Medicion> resultados = dao.listar();

            final String[] encontrados = new String[resultados.size()];
            int i = 0;
            for (Medicion gm : resultados){
                encontrados[i++] =  String.valueOf(gm.getId()) + " - " + gm.getNombre() + " - " + gm.getMarca();
                Log.d("ProductPickerActivity","Got Values : "+gm.getNombre());
            }


            ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this.getActivity().getApplicationContext(),
                    android.R.layout.simple_list_item_1,
                    encontrados);

            final ListView listaResultados = (ListView) myFragmentView.findViewById(R.id.listaResultados);
            getActivity().runOnUiThread(  new Runnable() {
                public void run() {
                    ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Fragmento2.this.getActivity(),
                            android.R.layout.simple_list_item_1, encontrados);
                    listaResultados.setAdapter(adaptador);
                }
            });



        } catch (DAOException e) {
            Log.i("GeneroMusicalBuscarAc", "====> " + e.getMessage());
        }

        return myFragmentView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
        void onFragmentInteraction(Uri uri);
    }


}
