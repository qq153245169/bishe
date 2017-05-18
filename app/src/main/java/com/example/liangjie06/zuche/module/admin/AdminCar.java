package com.example.liangjie06.zuche.module.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.ETC1;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.liangjie06.zuche.MyApplication;
import com.example.liangjie06.zuche.R;
import com.example.liangjie06.zuche.activity.PayActivity;
import com.example.liangjie06.zuche.base.BaseFragment;
import com.example.liangjie06.zuche.bean.Car;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import me.iwf.photopicker.PhotoPicker;

import static android.app.Activity.RESULT_OK;
import static android.os.Build.VERSION_CODES.M;

/**
 * Created by liangjie06 on 17/5/18.
 */

public class AdminCar extends BaseFragment {

    private static final int CAMERA_CODE = 1;
    private static final int GALLERY_CODE = 2;
    private static final int CROP_CODE = 3;

    private ImageView ivCar;

    private EditText etCarName;
    private EditText etCarLeixing;
    private EditText etCarXiangshu;
    private EditText etCarPailiang;
    private EditText etCarChengzuo;
    private EditText etCarNew;
    private EditText etCarHot;
    private EditText etCarTe;
    private EditText etCarIce;
    private EditText etCarPrice;
    private Button btnUpdate;

    private String carName;
    private String carLeixing;
    private String carXiangshu;
    private String carPailiang;
    private String carChengzuo;
    private String carNew;
    private String carHot;
    private String carTe;
    private String carIce;
    private String carPrice;

    private File file;

    private Car car;

    @Override
    protected void initData() {

    }

    @Override
    protected View initView() {
        View view = View.inflate(mActivity, R.layout.admin_car, null);

        ivCar = (ImageView) view.findViewById(R.id.car_icon);
        etCarName = (EditText) view.findViewById(R.id.car_name);
        etCarLeixing = (EditText) view.findViewById(R.id.car_leixing);
        etCarXiangshu = (EditText) view.findViewById(R.id.car_xiangshu);
        etCarPailiang = (EditText) view.findViewById(R.id.car_pailiang);
        etCarChengzuo = (EditText) view.findViewById(R.id.car_chengzuo);
        etCarNew = (EditText) view.findViewById(R.id.car_xin);
        etCarHot = (EditText) view.findViewById(R.id.car_re);
        etCarTe = (EditText) view.findViewById(R.id.car_te);
        etCarIce = (EditText) view.findViewById(R.id.car_ice);
        etCarPrice = (EditText) view.findViewById(R.id.car_money);
        btnUpdate = (Button) view.findViewById(R.id.btn_updae);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataCar(AdminActivity.path);
            }
        });

        ivCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPicker.builder()
                        .setPhotoCount(1)
                        .setShowCamera(true)
                        .setShowGif(true)
                        .setPreviewEnabled(false)
                        .start(mActivity, PhotoPicker.REQUEST_CODE);
            }
        });
        return view;
    }

    private void updataCar(final String path) {
        if (TextUtils.isEmpty(path)){
            Toast.makeText(mActivity, "请上传图片", Toast.LENGTH_SHORT).show();
            return;
        }
        car = new Car();
        final BmobFile icon= new BmobFile(new File(path));
        icon.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){

                car.setIcon(icon);
                carName = String.valueOf(etCarName.getText());
                car.setCarName(carName);
                carLeixing = String.valueOf(etCarLeixing.getText());
                if("经济型".equals(carLeixing)){
                    car.setLeiXing("1");
                }else if ("商务型".equals(carLeixing)){
                    car.setLeiXing("2");
                }else if ("豪华型".equals(carLeixing)){
                    car.setLeiXing("3");
                }else if ("SUV".equals(carLeixing)){
                    car.setLeiXing("4");
                }else {
                    car.setLeiXing("1");
                }
                carXiangshu = String.valueOf(etCarXiangshu.getText());
                car.setXiangShu(carXiangshu);
                carPailiang = String.valueOf(etCarPailiang.getText());
                car.setPaiLiang(carPailiang);
                carChengzuo = String.valueOf(etCarChengzuo.getText());
                car.setChengZuo(carChengzuo);

                carNew = String.valueOf(etCarNew.getText());
                if ("是".equals(carNew)){
                    car.setXin(true);
                }else {
                    car.setXin(false);
                }

                carHot = String.valueOf(etCarHot.getText());
                if ("是".equals(carNew)){
                    car.setHot(true);
                }else {
                    car.setHot(false);
                }

                carTe = String.valueOf(etCarTe.getText());
                if ("是".equals(carNew)){
                    car.setTe(true);
                }else {
                    car.setTe(false);
                }

                carIce = String.valueOf(etCarIce.getText());
                if ("是".equals(carNew)){
                    car.setIce(true);
                }else {
                    car.setIce(false);
                }

                carPrice = String.valueOf(etCarPrice.getText());
                car.setPrice(Float.valueOf(carPrice));
                car.setType(0);

                car.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if (e == null){
                            Toast.makeText(mActivity, "上传成功", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mActivity, "上传失败", Toast.LENGTH_SHORT).show();
                            Log.e("lj","上传失败"+e.toString());

                        }
                    }
                });
                }else {
                    Log.e("lj","图片上传失败"+e.toString());
                }
            }
        });

    }

    public void setImage(String path) {
        Glide.with(mActivity).load(new File(path)).placeholder(R.drawable.car_image).into(ivCar);
    }

}

