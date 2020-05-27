package com.nasa.controller;

import com.nasa.bean.PhotoSet;
import com.nasa.service.PhotoService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhotoController {
	@Autowired
	PhotoService photoService;
	
	@CrossOrigin
	@GetMapping(value = "api/v1/{name}/photo", produces = {"application/json"})
	public PhotoSet getPhotoSet(@PathVariable String name, @RequestParam("earth_date") String date) {
		return photoService.getPhotos(name, date);
	}
	@CrossOrigin
	@GetMapping(value = "api/v1/{name}/photo/{id}")
	public ResponseEntity<byte[]> getImgUrl(@PathVariable String id, @RequestParam("img_src") String imgSrc) throws IOException {
		File file = photoService.getPhoto(imgSrc);
		return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_JPEG)
				.body(Files.readAllBytes(file.toPath()));
	}
}

