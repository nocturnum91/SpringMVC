package org.nocturnum.sample.controller;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.nocturnum.sample.domain.AttachFileDTO;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class FileController {

    @GetMapping(value = "/uploadFile")
    public void uploadFile() {
        log.info("#################### UPLOAD FILE PAGE");
    }

    @PostMapping(value = "/uploadFilePost")
    public void uploadFilePost(MultipartFile[] uploadFile) {
        log.info("#################### UPLOAD FILE POST: " + uploadFile.length);

        String uploadPath = "/Users/nocturnum/Dev/upload/tmp";

        for (MultipartFile multipartFile : uploadFile) {
            log.debug(multipartFile.getOriginalFilename());
            log.debug(multipartFile.getSize());

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
        log.debug("upload path: " + uploadPath);

        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }

        for (MultipartFile multipartFile : uploadFile) {
            log.debug("UPLOAD FILE NAME: " + multipartFile.getOriginalFilename());
            log.debug("UPLOAD FILE SIZE: " + multipartFile.getSize());

            AttachFileDTO attachFileDTO = new AttachFileDTO();
            attachFileDTO.setUploadPath(getFolder());

            String fileName = multipartFile.getOriginalFilename();
            String uploadFileName = fileName.substring(0, fileName.lastIndexOf("."));
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
            attachFileDTO.setFileName(uploadFileName);
            attachFileDTO.setExtension(extension);

            // IE has file path
            uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
            log.info("only file name: " + uploadFileName + "." + extension);


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

    //http://localhost:8080/SpringMVC/sample/display?fileName=2023/01/15/%EC%97%AC%EC%9A%B0_add21868-e503-4c9d-a8cd-e433c5e8e5ca.jpg
    @GetMapping("/display")
    @ResponseBody
    public ResponseEntity<byte[]> getFile(String fileName) {
        log.info("#################### GET FILE: " + fileName);

        String path = "/Users/nocturnum/Dev/upload/tmp/";
        File file = new File(path + fileName);
        log.debug("file: " + file);

        ResponseEntity<byte[]> result = null;

        try {
            HttpHeaders header = new HttpHeaders();
            header.add("Content-Type", MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(file));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping(value = "/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> downloadFile(String fileName, @RequestHeader("User-Agent") String userAgent) {
        log.info("#################### DOWNLOAD FILE: " + fileName);
        ResponseEntity<Resource> result = null;
        String path = "/Users/nocturnum/Dev/upload/tmp/";

        Resource resource = new FileSystemResource(path + fileName);
        log.debug("resource: " + resource);
        if (!resource.exists()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        String resourceName = resource.getFilename();
        String extension = resourceName.substring(resourceName.lastIndexOf(".") + 1);

        //remove UUID
        String resourceOriginalName = resourceName.substring(0, resourceName.indexOf(".")).substring(0, resourceName.lastIndexOf("_")) + "." + extension;

        try {
            HttpHeaders header = new HttpHeaders();

            String downloadName = null;
            log.debug(userAgent);

            if (userAgent.contains("Trident")) {
                log.debug("IE browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8").replaceAll("\\+", " ");
            } else if (userAgent.contains("Edge") || userAgent.contains("Edg")) {
                log.debug("Edge browser");
                downloadName = URLEncoder.encode(resourceOriginalName, "UTF-8");
            } else {
                log.debug("Chrome browser");
                downloadName = new String(resourceOriginalName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
            }
            header.add("Content-Disposition", "attachment; filename=" + downloadName);
            result = new ResponseEntity<>(resource, header, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
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
