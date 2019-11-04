package com.lyz.thumbsup.service.impl;

import com.lyz.thumbsup.config.RedisConsts;
import com.lyz.thumbsup.dataobject.UserInfo;
import com.lyz.thumbsup.enums.TalentsSortEnum;
import com.lyz.thumbsup.repository.UserInfoRepository;
import com.lyz.thumbsup.service.UserService;
import com.lyz.thumbsup.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserInfoRepository userRepository;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public void logout(String userId) {
        //从 Redis 中删除当前用户对应的key
        String key = String.format(RedisConsts.TOKEN_TEMPLATE, userId);
        redisUtils.deleteString(key);
    }

    @Override
    public List<UserInfo> findAllByRoleAndExperience(Integer page, Integer size, Integer sort, Integer role, Integer experience) {
        return userRepository.findAllByRoleAndExperience(genSortedPageable(page, size, sort), role, experience).getContent();
    }

    /**
     * 根据传入的排序标记生成带排序的 Pageable 对象
     *
     * @param page
     * @param size
     * @param sort
     * @return
     */
    private Pageable genSortedPageable(Integer page, Integer size, Integer sort) {
        //sort 为空则返回原列表
        if (sort == null) {
            return PageRequest.of(page, size);
        }
        if (sort.equals(TalentsSortEnum.DEFAULT.getCode())) {
            //默认排序，返回原列表
            return PageRequest.of(page, size);
        } else if (sort.equals(TalentsSortEnum.SORT_BY_EXPERIENCE.getCode())) {
            //根据经验排序.如果经验相同则比较影响力
            Sort orders = Sort.by(Sort.Direction.DESC, "experience", "influence");
            return PageRequest.of(page, size, orders);
        } else if (sort.equals(TalentsSortEnum.SORT_BY_INFLUENCE.getCode())) {
            //根据影响力排序.如果影响力相同则比较经验
            Sort orders = Sort.by(Sort.Direction.DESC, "influence", "experience");
            return PageRequest.of(page, size, orders);
        }
        return PageRequest.of(page, size);
    }
}
