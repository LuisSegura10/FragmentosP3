package mx.com.softwell.fragmentos.gui;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.com.softwell.fragmentos.R;
import mx.com.softwell.fragmentos.core.MiscController;
import mx.com.softwell.fragmentos.core.OldSchoolController;
import mx.com.softwell.fragmentos.databinding.FragmentLaViejaEscuelaBinding;
import mx.com.softwell.fragmentos.gui.components.JuegosAdapter;
import mx.com.softwell.fragmentos.gui.components.NavigationIconClickListener;
import mx.com.softwell.fragmentos.model.Juego;

public class LaViejaEscuela extends Fragment {
    private MiscController miscController = MiscController.Instance();
    private OldSchoolController oldSchoolController = OldSchoolController.Instance();
    private FragmentLaViejaEscuelaBinding binding;
    private View view;
    private Context context;
    private static String TAG = "LaViejaEscuela";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        configGlobals();
        configView(inflater,container);
        configToolbar();
        configUI();
        configRecycler();
        actualizar();
        return view;
    }

    private void configGlobals() {
        MainActivity.GLOBALS.put("viejaEscuelaFragment",this);
    }

    private void configView(LayoutInflater inflater, ViewGroup container) {
        binding = FragmentLaViejaEscuelaBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        context = container.getContext();
    }

    private void configToolbar() {
        AppCompatActivity activity = (AppCompatActivity)getActivity();
        if(activity!=null){
            activity.setSupportActionBar(binding.appBar);
        }
        binding.appBar.setNavigationOnClickListener(new NavigationIconClickListener(
                context,
                view.findViewById(R.id.gridLaViejaEscuela),
                new AccelerateDecelerateInterpolator(),
                context.getDrawable(R.drawable.menu),
                context.getDrawable(R.drawable.menu_open)
        ));
    }

    private void configUI() {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            view.findViewById(R.id.gridLaViejaEscuela).setBackground(getContext().getDrawable(R.drawable.product_grid_background_shape));

        }
    }

    private void configRecycler() {
        binding.rclvViejaEscuela.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL,false);
        binding.rclvViejaEscuela.setLayoutManager(layoutManager);
    }
    private void actualizar() {
        miscController.ShowWait(context, "Consultando juegos de la vieja escuela ...");
        oldSchoolController.getAll();
    }

    public void actualizar(List<Juego> juegos) {
        binding.rclvViejaEscuela.setAdapter(new JuegosAdapter(juegos));
        miscController.CloseWait();
    }
}
