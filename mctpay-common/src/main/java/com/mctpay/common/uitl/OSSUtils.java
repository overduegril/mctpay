package com.mctpay.common.uitl;

import com.aliyun.oss.*;
import com.aliyun.oss.model.ObjectMetadata;
import com.mctpay.common.config.OSSConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * @Author: guodongwei
 * @Description: 使用阿里云OSS存储对象上传文件
 * @Date: 2020/2/27 11:32
 */
@Slf4j
public class OSSUtils {

    private static OSS client = null;

    static {
        ClientBuilderConfiguration config = new ClientBuilderConfiguration();
        config.setConnectionTimeout(30 * 1000);
        config.setMaxConnections(60);
        config.setMaxErrorRetry(3);
        config.setSocketTimeout(10 * 1000);
        client = new OSSClientBuilder().build(OSSConfig.endpoint, OSSConfig.accessKeyId, OSSConfig.accessKeySecret, config);
    }

    public static String uploadFile(String bucketName, String srcFile, String dstFile) {
        // 判断源文件是否存在
        File file = new File(srcFile);
        if (!file.exists()) {
            log.debug(srcFile + ":文件未找到");
            return null;
        }
        BufferedInputStream is = null;
        try {
            // 读取文件
            is = new BufferedInputStream(new FileInputStream(file));
            ObjectMetadata thuObjectMeta = new ObjectMetadata();
            thuObjectMeta.setContentLength(file.length());
            String fileName = file.getName();
            thuObjectMeta.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            // 上传到oss
            client.putObject(bucketName, dstFile, is, thuObjectMeta);
            return bucketName + "@" + dstFile;
        } catch (IOException e) {
            log.debug(e.getMessage(), e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                log.debug(e.getMessage(), e);
            }
        }
        return "";
    }

    /**
     * @Description 通过文件流上传到OSS
     * @Date 16:48 2020/2/27
     **/
    public static String uploadFileInputStream(String bucketName, String objectName, InputStream inputStream) {
        try {
            client.putObject(bucketName, objectName, inputStream);
            return bucketName + "@" + objectName;
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
            return "";
        }

    }

    /**
     * @Description 判断OSS服务文件上传时文件的contentType
     * @Date 16:38 2020/2/27
     **/
    public static String getcontentType(String filenameExtension) {
        if (filenameExtension.equalsIgnoreCase(".bmp")) {
            return "image/bmp";
        }
        if (filenameExtension.equalsIgnoreCase(".gif")) {
            return "image/gif";
        }
        if (filenameExtension.equalsIgnoreCase(".jpeg") ||
                filenameExtension.equalsIgnoreCase(".jpg") ||
                filenameExtension.equalsIgnoreCase(".png")) {
            return "image/jpeg";
        }
        if (filenameExtension.equalsIgnoreCase(".html")) {
            return "text/html";
        }
        if (filenameExtension.equalsIgnoreCase(".txt")) {
            return "text/plain";
        }
        if (filenameExtension.equalsIgnoreCase(".vsd")) {
            return "application/vnd.visio";
        }
        if (filenameExtension.equalsIgnoreCase(".pptx") ||
                filenameExtension.equalsIgnoreCase(".ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (filenameExtension.equalsIgnoreCase(".docx") ||
                filenameExtension.equalsIgnoreCase(".doc")) {
            return "application/msword";
        }
        if (filenameExtension.equalsIgnoreCase(".xml")) {
            return "text/xml";
        }
        if (filenameExtension.equalsIgnoreCase(".json")) {
            return "application/json;charset=utf-8";
        }
        if (filenameExtension.equalsIgnoreCase(".pdf")) {
            return "application/pdf;charset=utf-8";
        }
        return "application/octet-stream";
    }

    /**
     * @Description 删除目标文件
     * @Date 16:49 2020/2/27
     **/
    public static void deleteFile(String bucketName, String dstFile) {
        try {
            client.deleteObject(bucketName, dstFile);
        } catch (Exception e) {
            log.debug(e.getMessage(), e);
        }
    }

}

