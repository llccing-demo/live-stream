package com.llccing.livestream.controller;

import com.llccing.livestream.service.FileService;
import com.llccing.livestream.service.RecordVideoThread;
import com.llccing.livestream.service.ShellService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class HelloController {
    @GetMapping("/")
    public String Index() {
        try {
            ClassPathResource resource = new ClassPathResource("static" + File.separator+ "big_buck_bunny.mp4");
            File inputFile = resource.getFile();
            String videoPath = inputFile.getAbsolutePath();


//            String shell = "ffmpeg -i " + videoPath + " -b:v 640k " + "/Users/llccing/Project/llccing-demo/live-stream/target/classes/static/big_buck_bunny.flv";
//            ShellService.execShell(shell);

            RecordVideoThread thread = new RecordVideoThread();
            thread.setFilePath(videoPath);
            thread.setStreamURL("rtmp://127.0.0.1:8000/live/home");
            thread.start();
        } catch (Exception e) {
            System.out.println("copy file controller, "+e);
        }

        return "Greetings from llccing!";
    }
}
