package com.nasa.service;

import org.springframework.stereotype.Service;

import com.nasa.bean.PhotoSet;

import com.nasa.client.NasaRestClient;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class PhotoService {
	@Autowired
	NasaRestClient nasaRestClient;
	
	@Autowired
	DateService dateService;
	
    public PhotoSet getPhotos(String name, String date){
    	return nasaRestClient.getPhotoSet(name,date);
    }
    
    public File getPhoto(String imgUrl) throws IOException {
    	String IMAGE_CACHE_PATH = "src/main/resources/nasaimage";
    	
    	byte[] stringBytes = imgUrl.getBytes();

		final String imageFileName = new StringBuilder().append(stringBytes).toString();
		
		File image;
		
		if (Files.exists(Paths.get(imageFileName))) { 
			image = Paths.get(imageFileName).toFile();
		} 
		else {
			InputStream inputStream = nasaRestClient.getPhoto(imgUrl);
			Path cachedFile = Files.createFile(Paths.get(imageFileName));
			Files.copy(inputStream, cachedFile, StandardCopyOption.REPLACE_EXISTING);
			IOUtils.closeQuietly(inputStream);
			image = cachedFile.toFile();
		}
		return image;
	}
    
}
