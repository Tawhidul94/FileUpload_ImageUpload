package com.tawhid.service.Impl;

import com.tawhid.entity.ImageData;
import com.tawhid.repository.ImageDataRepojetory;
import com.tawhid.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataServiceImpl implements  ImageDataService{

    @Autowired private ImageDataRepojetory imageDataRepojetory;

    @Override
    public String uploadImage(MultipartFile file) throws IOException {
        ImageData imageData = imageDataRepojetory.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if(imageData!=null){
            return  "file uploaded successfully : " + file.getOriginalFilename();
        }
        return null;
    }

    @Override
    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImagedata = imageDataRepojetory.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImagedata.get().getImageData());
        return images;
    }
}
