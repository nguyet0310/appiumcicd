package com.qa.utils;

import com.qa.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestUtils {
    public static final long WAIT = 10;

    public static HashMap<String, String> parseStringXML(InputStream file) throws Exception {
        HashMap<String, String> stringMap = new HashMap<String, String>();
        //Get Document Builder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //Build Document
        Document document = builder.parse(file);

        //Normalize the XML Structure; It's just too important !!
        document.getDocumentElement().normalize();

        //Here comes the root node
        Element root = document.getDocumentElement();

        //Get all elements
        NodeList nList = document.getElementsByTagName("string");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                // Store each element key value in map
                stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
            }
        }
        return stringMap;
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }

//    public void log(String txt) {
//        BaseTest base = new BaseTest();
//        String msg = Thread.currentThread().getId() + ":" + base.getPlatform() + ":" + base.getDeviceName() + ":"
//                + Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
//
//        System.out.println(msg);
//        String strFile = "logs" + File.separator + base.getPlatform() + "_" + removeSpecialString(base.getDeviceName())
//                + File.separator + base.getDateTime();
//
//        File logFile = new File(strFile);
//
//        if (!logFile.exists()) {
//            logFile.mkdirs();
//        }
//
//        FileWriter fileWriter = null;
//        try {
//            fileWriter = new FileWriter(logFile + File.separator + "log.txt", true);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        PrintWriter printWriter = new PrintWriter(fileWriter);
//        printWriter.println(msg);
//        printWriter.close();
//    }

    public String removeSpecialString(String s) {
        if (s == null || s.trim().isEmpty()) {
            System.out.println("Incorrect format of string");
            return s;
        }
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(s);
        // boolean b = m.matches();
        boolean b = m.find();
        if (b) {
            System.out.println("There is a special character in my string ");
            System.out.println("Start to remove them");
            return s.replaceAll("[.*-+^]*", "").trim();
        } else {
            return s;
        }
    }
    public Logger log() {
        return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
    }
}
