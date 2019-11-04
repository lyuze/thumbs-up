package com.lyz.thumbsup.repository;

import com.lyz.thumbsup.dataobject.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, String> {

    @Override
    Page<UserInfo> findAll(Pageable pageable);

    Page<UserInfo> findAllByRoleAndExperience(Pageable pageable, Integer role, Integer experience);
}
