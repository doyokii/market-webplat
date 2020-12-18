package com.example.web.utils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * @author : Zhuang Jialong
 * @description : 文件保存本地功能
 * @date : 2020/12/18 下午 5:29
 * @Copyright: Copyright(c)2019 RedaFlight.com All Rights Reserved
 */
public class FileUtils {
//
//    private static final String FILE_SEPARATOR = "/";
//
//    /**
//     * 字节数组写入文件
//     *
//     * @param filePath
//     * @param bytes
//     */
//    public static void writeByte2File(String filePath, byte[] bytes) throws Exception {
//        writeByte2File(new File(filePath), bytes);
//    }
//
//    /**
//     * 字节数组写入文件
//     *
//     * @param file
//     * @param bytes
//     */
//    public static void writeByte2File(File file, byte[] bytes) throws Exception {
//        FileOutputStream fileOutputStream = null;
//        try {
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//            fileOutputStream = new FileOutputStream(file);
//            fileOutputStream.write(bytes);
//        } finally {
//            if (fileOutputStream != null) {
//                fileOutputStream.close();
//            }
//        }
//    }
//
//    /**
//     * 保存图片
//     *
//     * @param photoPath    照片根路径
//     * @param content      照片内容
//     * @param deviceNumber 智能柜编号
//     * @param loginName    工号
//     * @return 图片保存完整路径
//     * @throws Exception Exception
//     */
//    public static String saveImage(String photoPath, String content, String deviceNumber, String loginName) throws Exception {
//        if (!photoPath.endsWith(FILE_SEPARATOR)) {
//            photoPath = photoPath + FILE_SEPARATOR;
//        }
//        String dirPath = photoPath + T.DateUtils.getYear() + FILE_SEPARATOR + T.DateUtils.getMonth();
//        File file = new File(dirPath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        //图片命名规则修改为：智能柜编号_工号_时间戳格式化时间字符串
//        String filePath = dirPath + "/" + deviceNumber + "_" + loginName + "_" + T.DateUtils.formatDateTime2(new Date()) + ".jpg";
//        byte[] decode = Base64.decode(content);
//        com.reda.sdc.common.utils.FileUtils.writeByte2File(filePath, decode);
//        return filePath;
//    }
//
//    /**
//     * 获取照片返回前端
//     *
//     * @param imageFilePath 照片路径
//     * @return 字节数据
//     */
//    public static byte[] getImageByte(String imageFilePath) {
//        FileInputStream fileIn = null;
//        byte[] bytes = null;
//        try {
//            fileIn = new FileInputStream(new File(imageFilePath));
//            bytes = new byte[fileIn.available()];
//            fileIn.read(bytes);
//            return bytes;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            T.IOUtils.closeQuietly(fileIn);
//        }
//    }
}

