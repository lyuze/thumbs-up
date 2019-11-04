package com.lyz.thumbsup.controller;

import com.lyz.thumbsup.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Author: liuyuze
 * @Date: 2019.11.4 22:20
 */
@Controller
public class LikedController {

    @Autowired
    private LikedService likedService;


}
