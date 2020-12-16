package com.jess.gdfp.Controller;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.jess.gdfp.MainActivity;
import com.jess.gdfp.Pop_up_Menu;

import static android.content.ContentValues.TAG;


public class Read_Usb_Device {
    public static String str;


    public static long readusb(Context context) {
        long i = 0;
        UsbMassStorageDevice[] devices = UsbMassStorageDevice.getMassStorageDevices(context);
        for (UsbMassStorageDevice device : devices) {
            permession(context, device);
            try {
                device.init();
                FileSystem curentFs = device.getPartitions().get(0).getFileSystem();
                System.out.println(curentFs.getCapacity());
                System.out.println(curentFs.getFreeSpace());
                i = curentFs.getCapacity();
                str = readApkfile(curentFs);
                Intent intent = new Intent(context, Pop_up_Menu.class);
                intent.putExtra("ApkString", str);
                device.close();
            } catch (Exception e) {
                e.printStackTrace();


            }

        }
        return i;
    }

    private static String readApkfile(FileSystem currentFs) {
        String str = " nix ";
        String apk = " ";
        int count = 0;
        UsbFile root = currentFs.getRootDirectory();
        try {
            UsbFile[] files = root.listFiles();
            for (UsbFile file : files) {
                Log.d(TAG, file.getName());
                int i = findApk(file.getName());
                if (i == 1) {
                    count++;
                    apk = file.getName();
                }
                Log.d(TAG, file.getAbsolutePath());
                if (file.isDirectory()) {
                    // Log.d(TAG, file.getLength());
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        if (count == 1) {
            str = " apk found  " + apk;
        } else if (count == 0) {
            str = " Error :apk not found";
        } else if (count > 1) {
            str = " Error; more than 1 available apk ";
        }
        return str;
    }

    public static void permession(Context context, UsbMassStorageDevice device) {
        UsbManager usbManager = (UsbManager) context.getSystemService(Context.USB_SERVICE);
        final String ACTION_USB_PERMISSION =
                "com.android.example.USB_PERMISSION";


        PendingIntent permissionIntent = PendingIntent.getBroadcast(context, 0, new Intent(ACTION_USB_PERMISSION), 0);
        usbManager.requestPermission(device.getUsbDevice(), permissionIntent);


    }


    public static int findApk(String str) {
        if (str.contains(".apk"))
            return 1;
        else
            return 0;

    }




}
