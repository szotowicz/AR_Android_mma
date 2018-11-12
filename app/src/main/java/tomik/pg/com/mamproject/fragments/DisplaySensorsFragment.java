package tomik.pg.com.mamproject.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import tomik.pg.com.mamproject.MainActivity;
import tomik.pg.com.mamproject.R;

public class DisplaySensorsFragment extends BaseFragment {

    private CountDownTimer timer;
    TextView accelerometer1TextView;
    TextView accelerometer2TextView;
    TextView accelerometer3TextView;
    TextView magnetic1TextView;
    TextView magnetic2TextView;
    TextView magnetic3TextView;
    TextView gravity1TextView;
    TextView gravity2TextView;
    TextView gravity3TextView;

    public DisplaySensorsFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_sensors, container, false);

        accelerometer1TextView = view.findViewById(R.id.accelerometer1textView);
        accelerometer2TextView = view.findViewById(R.id.accelerometer2textView);
        accelerometer3TextView = view.findViewById(R.id.accelerometer3textView);

        magnetic1TextView = view.findViewById(R.id.magnetic1textView);
        magnetic2TextView = view.findViewById(R.id.magnetic2textView);
        magnetic3TextView = view.findViewById(R.id.magnetic3textView);

        gravity1TextView = view.findViewById(R.id.gravity1textView);
        gravity2TextView = view.findViewById(R.id.gravity2textView);
        gravity3TextView = view.findViewById(R.id.gravity3textView);

        timer = new CountDownTimer(2000, 20) {
            @Override
            public void onTick(long millisUntilFinished) { }

            @Override
            public void onFinish() {
                try{
                    setValuesFromSensors();
                }catch(Exception e){
                    Log.e("Error", "Error: " + e.toString());
                }
            }
        }.start();

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setValuesFromSensors();
    }

    private void setValuesFromSensors() {
        final MainActivity mainActivity = getMyActivity();

        float[] accelerometer = mainActivity.getAccelerometerCurrentValues();
        accelerometer1TextView.setText(Float.toString(accelerometer[0]));
        accelerometer2TextView.setText(Float.toString(accelerometer[1]));
        accelerometer3TextView.setText(Float.toString(accelerometer[2]));

        float[] magnetic = mainActivity.getMagneticCurrentValues();
        magnetic1TextView.setText(Float.toString(magnetic[0]));
        magnetic2TextView.setText(Float.toString(magnetic[1]));
        magnetic3TextView.setText(Float.toString(magnetic[2]));

        float[] gravity = mainActivity.getGravityCurrentValues();
        gravity1TextView.setText(Float.toString(gravity[0]));
        gravity2TextView.setText(Float.toString(gravity[1]));
        gravity3TextView.setText(Float.toString(gravity[2]));

        timer.start();
    }
}