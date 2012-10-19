package com.example.cameracapture;

import java.io.IOException;

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
		c = camera;
		holder = this.getHolder();
		holder.addCallback(this);
		holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); 
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		if(holder.getSurface()==null){
			Log.d("debug : ", "no surface to change!!!");
			return;
		}
		try{
			c.stopPreview();
		}catch(Exception e){
			Log.d("debug : ", "stop preview error " + e.getMessage());
		}
		try {
			c.setPreviewDisplay(holder);
			c.startPreview();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.d("debug : ", "change preview error " + e.getMessage());
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		try{
			c.setPreviewDisplay(holder);
			c.startPreview();
			Log.d("Debug : ", "start preview!!!");
		}catch(Exception e){
			Log.d("debug : ", "Error setting camera preview: " + e.getMessage()); 
		}
	}
	
	public void setCamera(Camera c){
		this.c =c;
		try {
			c.setPreviewDisplay(holder);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.startPreview();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		c.release();
		Log.d("debug : ", "release camera " );
		
	}

}
