package tomik.pg.com.mamproject;

import android.app.Activity;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.vision.CameraSource;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class VRactivity extends Activity implements SurfaceHolder.Callback {
    TextView testView;

    Camera camera;
    Camera camera2;
    SurfaceView surfaceViewLeft;
    SurfaceHolder surfaceHolderLeft;
    SurfaceView surfaceViewRight;
    SurfaceHolder surfaceHolderRight;

    private final String tag = "VideoServer";

    Button start;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_vrglasses);

        start = (Button)findViewById(R.id.button1);
        start.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View arg0) {
                startCamera();
            }
        });

        surfaceViewLeft = (SurfaceView)findViewById(R.id.imageViewLeft);
        surfaceHolderLeft = surfaceViewLeft.getHolder();
        surfaceHolderLeft.addCallback(this);
        surfaceHolderLeft.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        surfaceViewRight = (SurfaceView)findViewById(R.id.imageViewRight);
        surfaceHolderRight = surfaceViewRight.getHolder();
        surfaceHolderRight.addCallback(this);
        surfaceHolderRight.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void startCamera() {
        try{
            camera = Camera.open();
        }catch(RuntimeException e){
            Log.e(tag, "init_camera: " + e);
            return;
        }
        try {
            camera.setPreviewDisplay(surfaceHolderLeft);
            camera.startPreview();
        } catch (Exception e) {
            Log.e(tag, "init_camera: " + e);
            return;
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
