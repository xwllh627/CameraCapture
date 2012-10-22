package com.example.cameracapture;

import java.io.IOException;

import com.example.cameracapture.CameraActivity.PreviewFrameCallBack;

import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback{
	Camera c;
	SurfaceHolder holder;
	
	public CameraPreview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public CameraPreview(Context context,Camera camera){
		super(context);
		Log.d("debug preview : ", "constructor");
		c = camera;
		holder = this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		/*// TODO Auto-generated method stub
		Log.d("debug surface change : ", "surface Change");
		if(holder.getSurface()==null){
			Log.d("debug : ", "no surface to change!!!");
			return;
		}
		
		if(c!=null){
			try{
				c.stopPreview();
			}catch(Exception e){
				Log.d("debug surface : ", "stop preview error " + e.getMessage());
			}
			try {
				c.setPreviewDisplay(holder);
				c.startPreview();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("debug surface: ", "change preview error " + e.getMessage());
			}
		}*/
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
		if(c!=null){
			Log.d("Debug surface create1: ", "start preview!!!");
			try{
				setCamera(c);
			}catch(Exception e){
				Log.d("debug surface create: ", "Error setting camera preview: " + e.getMessage()); 
			}
		}
	}
	
	public void setCamera(Camera c){
		this.c =c;
		if(c!=null){
			try {
				Log.d("debug set Camera: ", "set camera"); 
				c.setPreviewDisplay(holder);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.startPreview();
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//c.release();
		if(c!=null)
			c.stopPreview();
		Log.d("debug surface destroy : ", "release camera " );
		
	}

}
