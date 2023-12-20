package com.JangKi.dducksang.Web;

import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
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

    @GetMapping("/jsonAPI")
    public List<MapListDto> search()
    {
        List<MapListDto> list = openAPI.getApi();
        return list;
    }

    /*
        Address 주소 저장 API
    */
    @PostMapping("/SaveAddressCode")
    public Object save_code() {
        try {
            Map<String, String> map = new HashMap<>();

            URL resource = getClass().getClassLoader().getResource("AddressList.txt");
            File file = ResourceUtils.getFile(resource.getFile());
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            BufferedReader bufferedReader = new BufferedReader(br);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains("폐지")) {
                    continue;
                } else {
                    String[] split = line.split("\t");
                    map.put(split[0], split[1]);
                }

//                System.out.println(map.size());
            }
            for (String key : map.keySet()) {

                    Long code = Long.valueOf(key);
                    String addr = map.get(key);
//
//                    System.out.println(code + " " + addr);
                    Address address = new Address(code, addr);
//
                    addressRepo.save(address);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            log.error("IOException", e);}

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }



}
