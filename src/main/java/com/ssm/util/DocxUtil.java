package com.ssm.util;

import com.ssm.config.CustomOoxmlClassLoader;
import com.ssm.controller.FileController;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFHeader;

import java.util.List;

public class DocxUtil {

    public static void read(String path){
        OPCPackage opcPackage = null;
        try {
            String poiOoxmlLiteJarPath = FileController.class.getClassLoader().getResources("../lib/poi-ooxml-lite-5.2.3.jar").nextElement().getPath(); // 替换为实际路径
            CustomOoxmlClassLoader.setCustomClassLoaderIfNecessary(poiOoxmlLiteJarPath);

            opcPackage = POIXMLDocument.openPackage(path);
            XWPFDocument document = new XWPFDocument(opcPackage);  // 触发错误的行
            List<XWPFHeader> headers = document.getHeaderList();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (opcPackage != null) {
                try {
                    opcPackage.close();
                } catch (Exception var29) {
                    var29.printStackTrace();
                    opcPackage = null;
                }
            }

        }
    }
}
