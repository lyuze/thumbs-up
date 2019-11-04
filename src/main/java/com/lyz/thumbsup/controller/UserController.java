package com.lyz.thumbsup.controller;

import com.lyz.thumbsup.VO.ResultVO;
import com.lyz.thumbsup.service.LikedService;
import com.lyz.thumbsup.service.RedisService;
import com.lyz.thumbsup.service.UserService;
import com.lyz.thumbsup.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LikedService likedService;

    @Autowired
    RedisService redisService;

    @Autowired
    RedisTemplate redisTemplate;

    @PostMapping("/like")
    public ResultVO like(@RequestParam("likedUserId") String likedUserId,
                         @RequestParam("likedPostId") String likedPostId) {
        //先把数据存到Redis里,再定时存回数据库
        redisService.saveLiked2Redis(likedUserId, likedPostId);
        redisService.incrementLikedCount(likedUserId);
        return ResultVOUtils.success();
    }

    @PostMapping("/unlike")
    public ResultVO unlike(@RequestParam("likedUserId") String likedUserId,
                           @RequestParam("likedPostId") String likedPostId) {
        //取消点赞,先存到Redis里面，再定时写到数据库里
        redisService.unlikeFromRedis(likedUserId, likedPostId);
        redisService.decrementLikedCount(likedUserId);
        return ResultVOUtils.success();
    }

    /**
     * 登出，注销登录
     * @return
     */
    @PostMapping("/logout/{userId}")
    public ResultVO logout(@PathVariable("userId") String userId){
        //即使删除redis中token失败，也返回成功。因为前端已经清除掉cookie中信息了
        userService.logout(userId);
        return ResultVOUtils.success();
    }

    @GetMapping("/test")
    public String test() {
        return "hello coderiver";
    }
}
