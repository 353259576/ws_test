package cn.katoumegumi.java.experiment.common;

import cn.katoumegumi.java.common.WsFieldUtils;
import cn.katoumegumi.java.common.WsFileUtils;
import cn.katoumegumi.java.common.WsStringUtils;
import org.opencv.core.*;
import org.opencv.dnn.ClassificationModel;
import org.opencv.dnn.Dnn;
import org.opencv.dnn.Model;
import org.opencv.dnn.Net;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.QRCodeDetector;
import org.opencv.utils.Converters;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class WsImageOpenCVUtils {

    static {
        URL url = WsImageOpenCVUtils.class.getClassLoader().getResource("lib/x64/opencv_java451.dll");
        String urlPath = WsStringUtils.decodeUnicode(url.getPath());
        System.out.println(urlPath);
        System.load(urlPath);
    }

    public static void main(String[] args) {


        /*System.out.println("启动一下而已");
        String filePath = "D:\\DCIM\\1.jpg";
        System.out.println(filePath);
        Mat mat = Imgcodecs.imread(filePath);
        if(mat.empty()){
            throw new RuntimeException("读取文件失败");
        }
        //Mat newMat = new Mat(mat.rows(), mat.cols(), CvType.CV_8SC1);
        //Imgproc.cvtColor(mat,newMat,Imgproc.COLOR_RGB2BGRA);
        //Imgproc.blur(mat,newMat,new Size(10,10));
        BufferedImage bufferedImage = (BufferedImage) HighGui.toBufferedImage(mat);
        Mat mat1 = new Mat(bufferedImage.getHeight(),bufferedImage.getWidth(),CvType.CV_8UC4);
        byte[] b = ((DataBufferByte)bufferedImage.getData().getDataBuffer()).getData();
        byte[] newBytes = new byte[bufferedImage.getHeight()*bufferedImage.getWidth() * 4];
        int length = b.length / 3;
        int bIndex = 0;
        int nbIndex = 0;
        for(int i = 0; i < length; i++){
            newBytes[nbIndex++] = b[bIndex++];
            newBytes[nbIndex++] = b[bIndex++];
            newBytes[nbIndex++] = b[bIndex++];
            newBytes[nbIndex++] = 25;
        }
        mat1.put(0,0,newBytes);
        //Imgproc.putText(mat,"dsfjifdsda",new Point(100,500),Imgproc.FONT_HERSHEY_COMPLEX,20,new Scalar(127,46,98),10);
        File file = WsFileUtils.createFile("D:\\DCIM\\3.png");
        Imgcodecs.imwrite(file.getPath(),mat1);*/
        //recognitionFace();
        //dnnTest();
        matTest();
    }


    private static void matTest(){
        System.out.println(-128<<24>>>24);
        System.out.println((byte)255);
        /*Mat mat = new Mat(1080,1920,CvType.CV_8UC3);
        byte[] bytes = new byte[1920*1080*3];
        //new Random().nextBytes(bytes);

        byte b = 0;
        byte g = 0;
        byte r = 0;



        mat.put(0,0,bytes);

        System.out.println(mat.rows());
        System.out.println(mat.cols());
        Mat mat1 = mat.clone();
        //Imgproc.blur(mat,mat1,new Size(5,5));

        Imgcodecs.imwrite("D://test//1.png",mat1);*/
    }

    /**
     * 识别脸
     */
    public static void recognitionFace(){
        String filePath = "D:\\DCIM\\1.jpg";
        System.out.println(filePath);
        Mat mat = Imgcodecs.imread(filePath);
        Mat gray = new Mat(mat.rows(),mat.cols(),CvType.CV_8SC1);
        Imgproc.cvtColor(mat,gray,Imgproc.COLOR_RGB2GRAY);
        //Imgproc.threshold(gray,gray,100,255,Imgproc.THRESH_BINARY);
        String url = WsImageOpenCVUtils.class.getClassLoader().getResource("lbpcascade_animeface.xml").getPath();
        CascadeClassifier cascadeClassifier = new CascadeClassifier("D:\\DCIM\\lbpcascade_animeface.xml");
        MatOfRect matOfRect = new MatOfRect();
        cascadeClassifier.detectMultiScale(gray,matOfRect,1.1);
        List<Rect> rectList = matOfRect.toList();
        for(Rect rect:rectList){
            Imgproc.rectangle(mat,rect,new Scalar(255,0,255),5);
        }
        Imgcodecs.imwrite("D:\\DCIM\\2.png",mat);
        mat.release();
        gray.release();
        matOfRect.release();
    }

    public static void dnnTest(){
        //System.out.println(Dnn.getInferenceEngineBackendType());
        //System.out.println(Dnn.getInferenceEngineVPUType());
        Net net = Dnn.readNetFromONNX("D:\\DCIM\\det.onnx");
        //ClassificationModel classificationModel = new ClassificationModel(net);

        QRCodeDetector detector = new QRCodeDetector();


    }

}
