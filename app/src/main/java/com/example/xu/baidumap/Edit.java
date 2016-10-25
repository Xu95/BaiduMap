package com.example.xu.baidumap;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import android.view.Window;
import android.view.WindowManager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Xu on 2016/10/17.
 */
public class Edit extends Activity{
    private ListView list1;
    private Adapter mAdapter;
    public static ArrayList<Friends> mData = new ArrayList<Friends>();
    private Button add_f = null;
    private EditText name1 = null;
    private EditText number1 = null;
    private Button OK = null;
    private Button cancel = null;
    private Button radar = null;
    String files;
    public AlertDialog dialog;
    public static int num;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_list);

        list1 = (ListView) findViewById(R.id.lvw_friends_list);
        mAdapter = new Adapter(Edit.this, mData);
        list1.setAdapter(mAdapter);

        bindView();
    }
    private void bindView(){
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Edit.this, Details.class);
                Friends f1 = (Friends) mData.get(position);
                String s = f1.getname();
                String n = f1.getnumber();
                intent.putExtra("name", s);
                intent.putExtra("number",n);
                startActivityForResult(intent, 1);
            }
        });

        final LayoutInflater friends = LayoutInflater.from(this);
        View view = friends.inflate(R.layout.dialog_add_friend, null, false);
        AlertDialog.Builder builder = new  AlertDialog.Builder(Edit.this);
        dialog = builder.setView(view).create();

        add_f = (Button) findViewById(R.id.btn_friends_list_add);
        add_f.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        name1 = (EditText) view.findViewById(R.id.txt_friend_name);
        number1 = (EditText) view.findViewById(R.id.txt_friend_number);

        // 取得Dialog里的按钮控件添加监听器
        OK = (Button)view.findViewById(R.id.btn_dialog_ok);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num++;
                mData= new ArrayList<Friends>();
                Intent intent = new Intent();
                Friends fr = new Friends(name1.getText().toString(), number1.getText().toString());
                mData.add(fr);
                intent.putExtra("name1", fr.getname());
                intent.putExtra("number1", fr.getnumber());
                files = String.valueOf(name1) + "" + String.valueOf(number1);
                saveObject(files);
                mAdapter.notifyDataSetChanged();
                mAdapter = new Adapter(Edit.this, mData);
                list1.setAdapter(mAdapter);
                dialog.cancel();
            }
        });
        cancel = (Button)view.findViewById(R.id.btn_dialog_close);
        cancel.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        mData=(ArrayList<Friends>)getObject(files);
        mAdapter = new Adapter(this,mData);

        radar = (Button)findViewById(R.id.btn_friends_list_radar);
        radar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit.this, MainActivity.class);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case RESULT_OK: {
                mAdapter.notifyDataSetChanged();
                mAdapter = new Adapter(Edit.this, mData);
                list1.setAdapter(mAdapter);
                break;
            }
            default:
                break;
        }
    }

    private void saveObject(String name){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = this.openFileOutput(name,MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(Edit.mData);
        } catch (Exception e) {
            e.printStackTrace();
            //这里是保存文件产生异常
        } finally {
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    //fos流关闭异常
                    e.printStackTrace();
                }
            }
            if (oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    //oos流关闭异常
                    e.printStackTrace();
                }
            }
        }
    }
    private Object getObject(String name){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = this.openFileInput(name);
            ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            //这里是读取文件产生异常
        } finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    //fis流关闭异常
                    e.printStackTrace();
                }
            }
            if (ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    //ois流关闭异常
                    e.printStackTrace();
                }
            }
        }
        //读取产生异常，返回null
        return null;
    }
}
