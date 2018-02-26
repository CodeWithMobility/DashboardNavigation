package com.mobiledev.dashboardnavigation.pie;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mobiledev.dashboardnavigation.R;

import java.util.ArrayList;

/**
 * Created by manu on 1/7/2018.
 */

public class FragmentPie extends Fragment {
    private TextView textView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pie,container, false);
        textView = (TextView) rootView.findViewById(R.id.textView);
        final PieView pieView = (PieView) rootView.findViewById(R.id.pie_view);
        Button button = (Button) rootView.findViewById(R.id.pie_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                randomSet(pieView);
            }
        });
        set(pieView);

        return rootView;
    }
    private void randomSet(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        ArrayList<Integer> intList = new ArrayList<Integer>();
        int totalNum = (int) (5 * Math.random()) + 5;

        int totalInt = 0;
        for (int i = 0; i < totalNum; i++) {
            int ranInt = (int) (Math.random() * 10) + 1;
            intList.add(ranInt);
            totalInt += ranInt;
        }
        for (int i = 0; i < totalNum; i++) {
            pieHelperArrayList.add(new PieHelper(100f * intList.get(i) / totalInt));
        }

        pieView.selectedPie(PieView.NO_SELECTED_INDEX);
        pieView.showPercentLabel(true);
        pieView.setDate(pieHelperArrayList);
    }

    private void set(PieView pieView) {
        ArrayList<PieHelper> pieHelperArrayList = new ArrayList<PieHelper>();
        pieHelperArrayList.add(new PieHelper(20, Color.BLACK));
        pieHelperArrayList.add(new PieHelper(6));
        pieHelperArrayList.add(new PieHelper(30));
        pieHelperArrayList.add(new PieHelper(12));
        pieHelperArrayList.add(new PieHelper(32));

        pieView.setDate(pieHelperArrayList);
        pieView.setOnPieClickListener(new PieView.OnPieClickListener() {
            @Override public void onPieClick(int index) {
                if (index != PieView.NO_SELECTED_INDEX) {
                    textView.setText(index + " selected");
                } else {
                    textView.setText("No selected pie");
                }
            }
        });
        pieView.selectedPie(2);
    }

}
