package com.example.stockhelp;

import android.graphics.Color;
import android.os.DropBoxManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartGestureListener;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Map;

public class graficos extends AppCompatActivity {

    private static final String TAG = "graficos";
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graficos);

        mChart = (LineChart) findViewById(R.id.grafico);

        //mChart.setOnChartGestureListener((OnChartGestureListener) graficos.this);
        //mChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) graficos.this);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        ArrayList<Entry> yValoresA = new ArrayList<>();
        yValoresA.add(new Entry(1, 90f));
        yValoresA.add(new Entry(2, 95f));
        yValoresA.add(new Entry(3, 80f));
        yValoresA.add(new Entry(4, 72f));
        yValoresA.add(new Entry(5, 79f));
        yValoresA.add(new Entry(6, 68f));

        LineDataSet set1 = new LineDataSet(yValoresA, "Itens Adicionados");

        set1.setFillAlpha(110);

        set1.setColor(Color.BLUE);
        set1.setLineWidth(2f);
        set1.setValueTextSize(10f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(dataSets);

        mChart.setData(data);
    }
}
