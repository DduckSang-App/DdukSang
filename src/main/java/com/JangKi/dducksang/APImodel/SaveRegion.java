package com.JangKi.dducksang.APImodel;

import com.JangKi.dducksang.domain.Address.Repository.Address;
import com.JangKi.dducksang.domain.Address.Repository.AddressRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class SaveRegion {
    public void saveRegionMain()
    {
        try{
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
//                    System.out.println(line);
                    String[] split = line.split("\t");
//                    System.out.println(split[0] + " " + split[1]);
                    map.put(split[0], split[1]);
//                    regionCodeItemRepository.save(new RegionCodeItem(split[0], split[1]));
                }
            }

//            JSONObject jsonpObject = new JSONObject(map);

            for(String key : map.keySet())
            {
//                JSONObject tmp = (JSONObject)jsonpObject.get(i);

                Long code = Long.valueOf(key);
                String addr = map.get(key);

                Address address = new Address(code, addr);
            }

        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            log.error("IOException", e);}
    }

}
