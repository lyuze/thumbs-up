package com.lyz.thumbsup.service;

import com.lyz.thumbsup.dataobject.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * 通过角色和经验双重筛选符合条件的用户
     *
     * @param role       用户角色
     * @param experience 用户经验
     * @return
     */
    List<UserInfo> findAllByRoleAndExperience(Integer page, Integer size, Integer sort, Integer role, Integer experience);

    UserInfo findById(String id);

    void updateInfo(UserInfo user);
}
