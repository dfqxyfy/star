package com.ccs.star.service.impl;

import com.ccs.star.service.MovieService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by ccs on 2017/2/23.
 */
@Service
public class MovieServiceImpl implements MovieService{

    static String basicDir = "E:\\电影\\生活大爆炸\\10";
    //static String basicDir = "F:";

    public List<String> getDirName(String dir){
        File f = new File(basicDir);
        System.out.println(f.getAbsoluteFile());
        System.out.println(f.isDirectory());
        System.out.println(f.exists());
        System.out.println(f.list());
        return Arrays.asList(f.list());
    }

//    public static void main(String[] args) {
//        List<String> list = getDirName("");
//        list.forEach(x->{
//            System.out.println(x);
//        });
//    }
}
