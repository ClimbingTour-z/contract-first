package com.one.serivce;

import com.one.dao.CompactMapper;
import com.one.model.Compact;
import com.one.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompactService {
    @Autowired
    private CompactMapper compactMapper;

    /**
     * 保存上传表格的信息
     * @param compact
     */
    public void save(Compact compact) {
        compactMapper.insert(compact);
    }

    /**
     * 查询合同详情信息
     * @param page
     * @param compact
     * @return
     */
    public Page<Compact> findList(Page<Compact> page, Compact compact) {
        compact.setPage(page);
        page.setList(compactMapper.findList(compact));

        return page;

    }

    /**
     * 按ID删除合同详情信息
     * @param compact
     */
    public void delete(Compact compact) {
        compactMapper.delete(compact);
    }
}
