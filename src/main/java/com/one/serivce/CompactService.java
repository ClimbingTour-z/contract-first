package com.one.serivce;

import com.one.dao.CompactMapper;
import com.one.model.Compact;
import com.one.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
//    public Page<Compact> findList(Page<Compact> page, Compact compact) {
//        compact.setPage(page);
//        page.setList(compactMapper.findList(compact));
//
//        return page;
//
//    }
    public Page<Compact> findList1(Integer page, Integer rows, Compact compact) {
        // 当前页
        compact.setStart((page-1) * rows) ;
        // 每页数
        compact.setRows(rows);
        // 查询客户列表
        List<Compact> compacts=compactMapper.selectCompactList(compact);
        //查询总数
        Integer count=compactMapper.selectCompactListCount(compact);
        // 创建Page返回对象
        Page<Compact> result = new Page<>();
        result.setPage(page);
        result.setRows(compacts);
        result.setSize(rows);
        result.setTotal(count);

        return result;
    }
    /**
     * 按ID删除合同详情信息
     * @param compact
     */
    public void delete(Compact compact) {
        compactMapper.delete(compact);
    }


}
