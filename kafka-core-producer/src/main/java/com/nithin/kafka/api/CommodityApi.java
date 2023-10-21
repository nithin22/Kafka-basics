package com.nithin.kafka.api;

import com.nithin.kafka.entity.Commodity;
import com.nithin.kafka.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {


    @Autowired
    private CommodityService commodityService;

    @GetMapping("/all")
    public List<Commodity> geenerateAllCommodoties(){
        return commodityService.createDummyCommodities();
    }
}
