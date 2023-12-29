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
        List<MapListDto> list = openAPI.getApi();

        System.out.println(list.size());

        return list;
    }

    /*
        Address 주소 저장 API
        초기 INIT 설정임
    */
    @PostMapping("/SaveAddressCode")
    public Object save_code() {
        try {
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

                    // split 0번은 코드, 1번은 지역 주소
                    Long code = Long.valueOf(split[0]);

                    String tmp = split[1];
//                    StringTokenizer st = new StringTokenizer(split[1]);

                    String[] tmp2 = tmp.split(" ");

                    String locateNM = tmp; // 제주도 / 북제주군 / 조천면 / 조천리

                    String locate[] = {"", "", "", "", ""};

                    int idx = 0;

                    for(String x : tmp2)
                    {
                        locate[idx] = x;
                        idx++;
                    }

                    Address address = new Address(code, locateNM, locate[0], locate[1], locate[2], locate[3], locate[4]);

                    addressRepo.save(address);
                }
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            log.error("IOException", e);}

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*
        현재 테스트용 -> 2015년 매매 거래 기록
        행정코드 기준으로 저장
    */

    @PostMapping("saveRecordBuilding")
    public Object saveBuilding()
    {
        List<Address> addresses = addressRepo.AddressList();

        Set<String> codeSet = new HashSet<>();

//        1111010100 서울특별시 청운동
        // 11110 << 서울특별시 행정코드
        // 10100 << 청운동 코드

        // API 행정코드 5자리

        try{

            for(Address ad : addresses)
            {
                Long code = ad.getCode();

                String str_code = code.toString().substring(0, 5);

               codeSet.add(str_code);
            }

            for(String setCode : codeSet) {

                List<MapListDto> saveList = openAPI.getApi();

                log.info(setCode + " " + "svaveList Size : " + saveList.size() );
//                for (MapListDto building : saveList) {
//                    log.info(building.getAptName() + " " + building.getBuildYear());
////                    Building building1 = new Building(building.getAmount(), building.getBuildYear(), building.getDealYear(), building.getDealMonth(),
////                                                        building.getDealDay(),
////                                                        building.getSigunguCode(), building.getEupmyundongCode(), building.getSigungu(),
////                                                        building.getDong(), building.getAptName(), building.getDedicatedArea(), building.getFloor());
////
////                    buildingRepo.save(building1);
//                }
            }
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }


}
