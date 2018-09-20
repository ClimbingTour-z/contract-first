package com.one.serivce;

import com.one.dao.EChartsMapper;
import com.one.model.Compact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EChartsService {
    @Autowired
    private EChartsMapper eChartsMapper;

    public List<Compact> selectCA() {
        return eChartsMapper.selectCA();
    }

    public List<Compact> selectRS() {
        return eChartsMapper.selectRS();
    }
}
