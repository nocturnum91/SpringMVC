package org.nocturnum.sample.controller;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.nocturnum.sample.domain.AttachFileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class UploadController {

    @GetMapping(value = "/uploadFile")
    public void uploadFile() {
        log.info("#################### UPLOAD FILE PAGE");
    }

    @PostMapping(value = "/uploadFilePost")
    public void uploadFilePost(MultipartFile[] uploadFile, Model model) {
        log.info("#################### UPLOAD FILE POST: " + uploadFile.length);

        String uploadPath = "/Users/nocturnum/Dev/upload/tmp";

        for (MultipartFile multipartFile : uploadFile) {
            log.info(multipartFile.getOriginalFilename());
            log.info(multipartFile.getSize());

            File saveFile = new File(uploadPath, multipartFile.getOriginalFilename());

            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    @GetMapping("/uploadHttp")
    public void uploadHttp() {
        log.info("#################### UPLOAD HTTPREQUEST...");
    }

    @PostMapping(value = "/uploadHttp", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<List<AttachFileDTO>> uploadHttp(MultipartFile[] uploadFile) {
        log.info("#################### UPLOAD FILE HTTPREQUEST: " + uploadFile.length);

        List<AttachFileDTO> list = new ArrayList<>();
        String path = "/Users/nocturnum/Dev/upload/tmp";

        // make folder
        File uploadPath = new File(path, getFolder());
        log.info("upload path: " + uploadPath);

        if (uploadPath.exists() == false) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            log.info("UPLOAD FILE NAME: " + multipartFile.getOriginalFilename());
            log.info("UPLOAD FILE SIZE: " + multipartFile.getSize());

            AttachFileDTO attachFileDTO = new AttachFileDTO();
            attachFileDTO.setUploadPath(getFolder());

            String fileName = multipartFile.getOriginalFilename();
            String uploadFileName = fileName.substring(0, fileName.lastIndexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);

            // IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
            log.info("only file name: " + uploadFileName + "." + extension);
            attachFileDTO.setFileName(uploadFileName + "." + extension);

            UUID uuid = UUID.randomUUID();
            uploadFileName = uploadFileName + "_" + uuid + "." + extension;
            attachFileDTO.setUuid(uuid.toString());

            try {
                File saveFile = new File(uploadPath, uploadFileName);
                log.info(saveFile.toPath());
                multipartFile.transferTo(saveFile);

                if (checkImageType(saveFile)) {
                    attachFileDTO.setImage(true);
                    FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath, "s_" + uploadFileName));
                    Thumbnailator.createThumbnail(multipartFile.getInputStream(), thumbnail, 150, 150);
                    thumbnail.close();
                }

                list.add(attachFileDTO);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    private String getFolder() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String str = sdf.format(date);
        return str.replace("-", File.separator);
    }

    private boolean checkImageType(File file) {
        MimetypesFileTypeMap mimeTypeMap = new MimetypesFileTypeMap();
        String contentType = mimeTypeMap.getContentType(file);
        return contentType.startsWith("image");
    }

}
