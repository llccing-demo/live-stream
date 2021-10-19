package com.llccing.livestream.service;

import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacv.*;

import java.io.File;
import java.io.IOException;

public class RecordVideoThread extends Thread{
    public String streamURL;
    public String filePath;
    public Integer id;

    public void setStreamURL(String streamURL) {
        this.streamURL = streamURL;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        System.out.println(streamURL);

        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(streamURL);
        FFmpegFrameRecorder recorder = null;

        try {
            grabber.start();
            Frame frame = grabber.grabFrame();
            if (frame != null) {
                File outFile = new File(filePath);
                if (!outFile.isFile()) {
                    try {
                        outFile.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                // 流媒体输出地址, 分辨率(长、高) 是否录制音频（0: 不录制, 1: 录制）
                recorder = new FFmpegFrameRecorder(filePath, 1080, 1440, 1);
                // 直播流格式
                recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
                // 录制视频格式
                recorder.setFormat("flv");
                // 帧数
                recorder.setFrameRate(25);
                // 百度翻译的比特率, 默认400000，调成 800000 较为合适
                recorder.setVideoBitrate(800000);
                recorder.start();
                while (frame != null) {
                    recorder.record(frame);
                    frame = grabber.grabFrame();
                }
                recorder.record(frame);
                recorder.stop();
                grabber.stop();
            }
        } catch (FrameGrabber.Exception e) {
            e.printStackTrace();
        } catch (FrameRecorder.Exception e) {
            e.printStackTrace();
        } finally {
            if (null != grabber) {
                try {
                    grabber.stop();
                } catch (FrameGrabber.Exception e) {
                    e.printStackTrace();
                }
            }
            if (recorder != null) {
                try {
                    recorder.stop();
                } catch (FrameRecorder.Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
