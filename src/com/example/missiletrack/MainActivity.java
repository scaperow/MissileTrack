package com.example.missiletrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener;
import org.opencv.android.Utils;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.TermCriteria;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements CvCameraViewListener,
		OnClickListener {

	CameraBridgeViewBase surface_camera;
	Point center_screen;
	Point selection_left_top;
	Point selection_right_bottom;
	Scalar selection_color = new Scalar(0, 200, 200);
	private BaseLoaderCallback loader_callback = new BaseLoaderCallback(this) {
		@Override
		public void onManagerConnected(int status) {
			switch (status) {
			case LoaderCallbackInterface.SUCCESS: {
				surface_camera.enableView();
			}
				break;
			default: {
				super.onManagerConnected(status);
			}
				break;
			}
		}
	};

	// Mat mat_current;

	@Override
	public Mat onCameraFrame(Mat frame) {
		// TODO Auto-generated method stub
		if (rect_selection == null) {
			// mat_current = frame;
			Core.rectangle(frame, selection_left_top, selection_right_bottom,
					selection_color, 2);

			return frame;
		} else {
			return startTrack(frame, frame);
		}

	}

	@Override
	public void onCameraViewStopped() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onResume() {
		super.onResume();
		OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3, this,
				loader_callback);
	}


	

	@Override
	public void onCameraViewStarted(int width, int height) {
		// TODO Auto-generated method stub
		if (center_screen == null) {
			center_screen = new Point(width / 2, height / 2);
			selection_left_top = new Point(center_screen.x - 80,
					center_screen.y - 60);
			selection_right_bottom = new Point(center_screen.x + 80,
					center_screen.y + 60);

		}
		if (mat_hsv == null) {
			mat_hsv = Mat.zeros(new Size(width, height), CvType.CV_8UC3);
		}
		if (mat_hue == null) {
			mat_hue = Mat.zeros(new Size(width, height), CvType.CV_8UC3);
		}
		if (mat_from_to == null) {
			mat_from_to = new MatOfInt(0, 0);
		}
		if (mat_mask == null) {
			mat_mask = new Mat();
		}
		if (mat_back == null) {
			mat_back = new Mat();
		}
		if (mat_roi == null) {
			mat_roi = new Mat();
		}

	}
}
