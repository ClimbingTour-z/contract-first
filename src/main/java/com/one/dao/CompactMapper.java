package com.one.dao;

import com.one.model.Compact;

import java.util.List;

public interface CompactMapper {

    /**
     * 保存上传表格的信息
     * @param compact
     */
    void insert(Compact compact);

    /**
     * 查询合同详情信息
     * @param compact
     * @return
     */
    List<Compact> selectCompactList(Compact compact);

    /**
     * 查询合同总数
     * @param compact
     * @return
     */
    Integer selectCompactListCount(Compact compact);

    /**
     * 按ID删除合同详情信息
     * @param compact
     */
    void delete(Compact compact);

}
