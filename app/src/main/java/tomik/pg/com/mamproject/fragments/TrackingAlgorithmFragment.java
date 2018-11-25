package tomik.pg.com.mamproject.fragments;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tomik.pg.com.mamproject.R;

public class TrackingAlgorithmFragment extends BaseFragment implements SurfaceHolder.Callback {

    Camera camera;
    SurfaceView surfaceViewLeft;
    SurfaceHolder surfaceHolderLeft;
    Button start;
    Button stop;

    public TrackingAlgorithmFragment() {
        super();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_algorithm, container, false);

        start = (Button)view.findViewById(R.id.button1);
        start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                startCamera();
            }
        });
        stop = (Button)view.findViewById(R.id.button2);
        stop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                stopCamera();
            }
        });

        surfaceViewLeft = (SurfaceView)view.findViewById(R.id.imageViewLeft);
        surfaceHolderLeft = surfaceViewLeft.getHolder();
        surfaceHolderLeft.addCallback(this);
        surfaceHolderLeft.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        return view;
    }

    private void startCamera() {
        try {
            camera = Camera.open();
            camera.setPreviewDisplay(surfaceHolderLeft);
            camera.startPreview();
        } catch (Exception e) {
            Log.e("[Warning]", "init_camera: " + e);
        }
    }

    private void stopCamera() {
        try{
            camera.stopPreview();
            camera.release();
        }catch(RuntimeException e){
            Log.e("[Warning]", "close_camera: " + e);
        }
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // TODO Auto-generated method stub
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }
}