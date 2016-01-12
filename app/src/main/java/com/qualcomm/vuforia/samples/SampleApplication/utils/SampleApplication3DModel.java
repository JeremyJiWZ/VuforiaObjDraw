/*===============================================================================
Copyright (c) 2012-2014 Qualcomm Connected Experiences, Inc. All Rights Reserved.

Vuforia is a trademark of PTC Inc., registered in the United States and other 
countries.
===============================================================================*/

package com.qualcomm.vuforia.samples.SampleApplication.utils;

import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.nio.ByteBuffer;


public class SampleApplication3DModel extends MeshObject
{
    
    private ByteBuffer verts;
    private ByteBuffer textCoords;
    private ByteBuffer norms;
    int numVerts = 0;
    
    
    public void loadModel(AssetManager assetManager, String filename)
        throws IOException
    {
        InputStream is = null;
        try
        {
            is = assetManager.open(filename);
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(is));
            
            String line = null;
//
//            int floatsToRead = Integer.parseInt(line);
//            numVerts = floatsToRead / 3;

            while (reader.readLine()!=null){
                line=line.trim();
                if (line.startsWith("v")) {
                    String[] parts = line.split(" ");
                    verts.putFloat(Float.parseFloat(parts[1]));
                    verts.putFloat(Float.parseFloat(parts[2]));
                    verts.putFloat(Float.parseFloat(parts[3]));
                    numVerts++;
                }
                if (line.startsWith("vt")) {
                    String[] parts = line.split(" ");
                    int length = parts.length;
                    for (int i = 1;i<length;i++)
                        textCoords.putFloat(Float.parseFloat(parts[i]));
                }
                if (line.startsWith("vn")) {
                    String[] parts = line.split(" ");
                    norms.putFloat(Float.parseFloat(parts[1]));
                    norms.putFloat(Float.parseFloat(parts[2]));
                    norms.putFloat(Float.parseFloat(parts[3]));
                }
            }

//            //read v
//            verts = ByteBuffer.allocateDirect(floatsToRead * 4);
//            verts.order(ByteOrder.nativeOrder());
//            for (int i = 0; i < floatsToRead; i++)
//            {
//                verts.putFloat(Float.parseFloat(reader.readLine()));
//            }
//            verts.rewind();
//
//            line = reader.readLine();
//            floatsToRead = Integer.parseInt(line);
//
//            //read vn
//            norms = ByteBuffer.allocateDirect(floatsToRead * 4);
//            norms.order(ByteOrder.nativeOrder());
//            for (int i = 0; i < floatsToRead; i++)
//            {
//                norms.putFloat(Float.parseFloat(reader.readLine()));
//            }
//            norms.rewind();
//
//            line = reader.readLine();
//            floatsToRead = Integer.parseInt(line);
//
//            //read vt
//            textCoords = ByteBuffer.allocateDirect(floatsToRead * 4);
//            textCoords.order(ByteOrder.nativeOrder());
//            for (int i = 0; i < floatsToRead; i++)
//            {
//                textCoords.putFloat(Float.parseFloat(reader.readLine()));
//            }
//            textCoords.rewind();
            
        } finally
        {
            if (is != null)
                is.close();
        }
    }
    
    
    @Override
    public Buffer getBuffer(BUFFER_TYPE bufferType)
    {
        Buffer result = null;
        switch (bufferType)
        {
            case BUFFER_TYPE_VERTEX:
                result = verts;
                break;
            case BUFFER_TYPE_TEXTURE_COORD:
                result = textCoords;
                break;
            case BUFFER_TYPE_NORMALS:
                result = norms;
            default:
                break;
        }
        return result;
    }
    
    
    @Override
    public int getNumObjectVertex()
    {
        return numVerts;
    }
    
    
    @Override
    public int getNumObjectIndex()
    {
        return 0;
    }
    
}