package com.foo.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.FormDataParam;


@Path("/file")
public class FileUpload {

    public FileUpload() {
        System.out.println("in file upload");
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public void post(@FormDataParam("file")File file, @FormDataParam("fileType")String fileType) throws FileNotFoundException
    {
        //Your local disk path where you want to store the file
        String uploadedFileLocation = "/tmp/" + file.getName();
        System.out.println(uploadedFileLocation);
        System.out.println(fileType);
        // save it
        File objFile=new File(uploadedFileLocation);
        if(objFile.exists())
        {
            objFile.delete();
        }

        saveToFile(new FileInputStream(file), uploadedFileLocation);
    }
    @GET
//    @Path("/get")
    @Produces(MediaType.TEXT_PLAIN)
    public String get(){
        return "its working!!";
    }

    private void saveToFile(InputStream uploadedInputStream,
            String uploadedFileLocation) {
        try {
            OutputStream out = null;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File(uploadedFileLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

}
