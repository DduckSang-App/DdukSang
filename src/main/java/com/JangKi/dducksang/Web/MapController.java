package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import com.JangKi.dducksang.domain.Building.Repository.Building;
import com.JangKi.dducksang.domain.Building.Repository.BuildingRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.util.*;

@Slf4j
@RestController
public class MapController {

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private OpenAPI openAPI;

    @Autowired
    private BuildingRepo buildingRepo;

    @GetMapping("/jsonAPI")
    public List<MapListDto> search()
    {
        List<MapListDto> list = openAPI.getApi("11110", "201502", "1");

        log.info("list sizeE : " + list.size());

        return list;
    }

}
