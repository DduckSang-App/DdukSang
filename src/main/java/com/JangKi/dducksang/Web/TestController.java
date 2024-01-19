package com.JangKi.dducksang.Web;

// 여기는 테스트 컨트롤러로 GIT에 포함되지 않는다.


import com.JangKi.dducksang.APImodel.OpenAPI;
import com.JangKi.dducksang.Service.Address.AddressService;
import com.JangKi.dducksang.Service.Building.BuildingService;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
import com.JangKi.dducksang.Web.Dto.AddressDto.AddressRequestDto;
import com.JangKi.dducksang.Web.Dto.BuildingDto.BuildingResponseDto;
import com.JangKi.dducksang.Web.Dto.Map.MapListDto;
import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
    public class TestController {
    /*
    Address 주소 저장 API
    초기 INIT 설정임
*/
    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private OpenAPI openAPI;

    @Autowired
    private AddressService addressService;

    @Autowired
    private BuildingService buildingService;

    @PostMapping("/SaveAddressCode")
    public Object save_code() {
        try {
            List<Address> addresses = new ArrayList<>();
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

//                    log.info(code + " ");

                    String tmp = split[1];

                    String[] tmp2 = tmp.split(" ");

                    String locateNM = tmp; // 제주도 / 북제주군 / 조천면 / 조천리

                    String locate[] = {"", "", "", "", ""};

                    int idx = 0;

                    for (String x : tmp2) {
                        locate[idx] = x;
                        idx++;
                    }

                    AddressDto.AddressInfoDto addressInfoDto = new AddressDto.AddressInfoDto(code, locateNM, locate[0], locate[1], locate[2]);

                    addresses.add(addressInfoDto.toEntity());
                }
            }
            addressRepo.saveAll(addresses);

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

        try{

            for(Address ad : addresses)
            {
                Long code = ad.getCode();

                String str_code = code.toString().substring(0, 5);

                codeSet.add(str_code);
            }

            for(String setCode : codeSet) {

                List<MapListDto> saveList = openAPI.getApi("setCode", "201502", "1");

                log.info(setCode + " " + "svaveList Size : " + saveList.size() );
            }
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping("/searchAllTest")
//    public void time1()
//    {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        Set<String> ret = addressService.searchAllService();
//
//        stopWatch.stop();
//        log.info("All Search Query Execution TIME : " + stopWatch.prettyPrint());
//
//        stopWatch = new StopWatch();
//        stopWatch.start();
//
//        List<String> ret2 = addressService.searchAllByDISTINCT();
//
//        stopWatch.stop();
//        log.info("DISTINCT Query Execution TIME : " + stopWatch.prettyPrint());
//
//        stopWatch = new StopWatch();
//        stopWatch.start();
//
//        List<String> ret3 = addressService.searchAllByGroupBY();
//
//        stopWatch.stop();
//        log.info("GroupBY Query Execution TIME : " + stopWatch.prettyPrint());
//    }

}
