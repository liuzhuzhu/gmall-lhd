package com.atguigu.gmall.manage;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {

    @Test
    public void contextLoads() throws IOException, MyException {
        //加载fastDFS配置文件
        String path = GmallManageWebApplicationTests.class.getClassLoader().getResource("tracker.conf").getPath();
        ClientGlobal.init(path);
        //创建tracker链接
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer connection = trackerClient.getConnection();

        //从tracker中返回一个可用的storage
        StorageClient storageClient = new StorageClient(connection, null);

        //上传文件
        String[] jpgs = storageClient.upload_file("c:/me.mp4", "mp4", null);

        //返回上传结果
        String imgUrl = "http://140.143.232.120";
    for (String jpg:jpgs){
        imgUrl = imgUrl + "/" + jpg;
    }
        System.out.println(imgUrl);
    }

}
