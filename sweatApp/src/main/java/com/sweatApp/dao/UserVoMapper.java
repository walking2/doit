package com.sweatApp.dao;

import com.sweatApp.vo.UserVo;

public interface UserVoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserVo record);

    int insertSelective(UserVo record);

    UserVo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserVo record);

    int updateByPrimaryKey(UserVo record);
}