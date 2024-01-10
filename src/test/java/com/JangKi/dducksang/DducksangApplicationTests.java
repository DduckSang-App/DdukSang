//package com.JangKi.dducksang;
//
//import com.JangKi.dducksang.Web.Dto.AddressDto.AddressDto;
//import com.JangKi.dducksang.domain.Address.Repository.*;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.ResourceUtils;
//import static org.assertj.core.api.Assertions.*;
//import static org.assertj.core.api.Assert.*;
//import java.io.*;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
////@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
//@TestPropertySource("classpath:application-test.properties") //test용 properties 파일 설정
//@SpringBootTest(classes = DducksangApplication.class)
//class DducksangApplicationTests {
//
//	@Autowired
//	AddressRepo addressRepo;
//
//	//	@Autowired
////	JDBCAddressRepo jdbcAddressRepo;
//////
////	@Autowired
////	JDBCRepo jdbcRepo;
//
//	@Test
//	@Transactional
//	@DisplayName("일반적인 entity 저장 테스트")
//	void Address_List_일반적인_Entity저장() throws IOException {
//		long curTime = System.currentTimeMillis();
//
//		//given
//		URL resource = getClass().getClassLoader().getResource("AddressList.txt");
//		File file = ResourceUtils.getFile(resource.getFile());
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
//		BufferedReader bufferedReader = new BufferedReader(br);
//		int expectedCount = 0;
//		String line = "";
//		while ((line = bufferedReader.readLine()) != null) {
//			if (line.contains("폐지")) {
//				continue;
//			} else {
//				String[] split = line.split("\t");
//
//				// split 0번은 코드, 1번은 지역 주소
//				Long code = Long.valueOf(split[0]);
//
////				System.out.println(code);
//
//				String tmp = split[1];
//
//				String[] tmp2 = tmp.split(" ");
//
//				String locateNM = tmp; // 제주도 / 북제주군 / 조천면 / 조천리
//
//				String locate[] = {"", "", "", "", ""};
//
//				int idx = 0;
//
//				for (String x : tmp2) {
//					locate[idx] = x;
//					idx++;
//				}
//
//				Address address = new Address();
//
//
//
//				addressRepo.save(address);
//				expectedCount++;
//			}
//		}
//
//		// when
//		System.out.println("테스트 1 시간 측정 : " + (System.currentTimeMillis() - curTime) + "ms");
//
//		//then
//		assertThat(addressRepo.count()).isEqualTo(expectedCount);
//
//	}
//
//
//	@Test
//	@Transactional
//	@DisplayName("일반적인 entity 저장 테스트2 - Builder이용")
//	void Address_List_Builder_Entity저장() throws IOException {
//		long curTime = System.currentTimeMillis();
//
//		List<Address> addresses = new ArrayList<>();
//
//		//given
//		URL resource = getClass().getClassLoader().getResource("AddressList.txt");
//		File file = ResourceUtils.getFile(resource.getFile());
//		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
//		BufferedReader bufferedReader = new BufferedReader(br);
//		int expectedCount = 0;
//		String line = "";
//
//		while ((line = bufferedReader.readLine()) != null) {
//			if (line.contains("폐지")) {
//				continue;
//			} else {
//
//				AddressDto.AddressInfoDto addressInfoDto = new AddressDto.AddressInfoDto();
//
//				String[] split = line.split("\t");
//
//				// split 0번은 코드, 1번은 지역 주소
//				Long code = Long.valueOf(split[0]);
//
////				System.out.println(code);
//
//				String tmp = split[1];
//
//				String[] tmp2 = tmp.split(" ");
//
//				String locateNM = tmp; // 제주도 / 북제주군 / 조천면 / 조천리
//
//				String locate[] = {"", "", "", "", ""};
//
//				int idx = 0;
//
//				for (String x : tmp2) {
//					locate[idx] = x;
//					idx++;
//				}
//
//				addressInfoDto.setCode(code);
//				addressInfoDto.setName(locateNM);
//				addressInfoDto.setName_v1(locate[0]);
//				addressInfoDto.setName_v2(locate[1]);
//				addressInfoDto.setName_v3(locate[2]);
//				addressInfoDto.setName_v4(locate[3]);
//
//				expectedCount++;
//
//				addresses.add(addressInfoDto.toEntity());
//			}
//		}
//
//		addressRepo.saveAll(addresses);
//
//		// when
//		System.out.println("테스트 2 시간 측정 : " + (System.currentTimeMillis() - curTime) + "ms");
//
//		//then
//		assertThat(addressRepo.count()).isEqualTo(expectedCount);
//
//	}
//
////	@Test
////	@Transactional
////	@DisplayName("일반적인 entity 저장 테스트3 - Batch Insert Multi Value 이용")
////	void Address_List_BatchInsert_Entity저장() throws IOException {
////		long curTime = System.currentTimeMillis();
////
////		List<JDBCAddress> addresses = new ArrayList<>();
////		//given
////		URL resource = getClass().getClassLoader().getResource("AddressList.txt");
////		File file = ResourceUtils.getFile(resource.getFile());
////		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
////		BufferedReader bufferedReader = new BufferedReader(br);
////		int expectedCount = 0;
////		String line = "";
////
////		while ((line = bufferedReader.readLine()) != null) {
////			if (line.contains("폐지")) {
////				continue;
////			} else {
////				String[] split = line.split("\t");
////
////				// split 0번은 코드, 1번은 지역 주소
////				Long code = Long.valueOf(split[0]);
////
//////				System.out.println(code);
////
////				String tmp = split[1];
////
////				String[] tmp2 = tmp.split(" ");
////
////				String locateNM = tmp; // 제주도 / 북제주군 / 조천면 / 조천리
////
////				String locate[] = {"", "", "", "", ""};
////
////				int idx = 0;
////
////				for (String x : tmp2) {
////					locate[idx] = x;
////					idx++;
////				}
////
////				AddressDto.AddressInfoDto addressInfoDto = new AddressDto.AddressInfoDto(code, locateNM, locate[0], locate[1], locate[2], locate[3]);
////
////				addresses.add(addressInfoDto.toJDBCEntity());
////
////				expectedCount++;
////			}
////		}
////
////		// when
////		jdbcAddressRepo.saveAll(addresses);
////		System.out.println("테스트 3 시간 측정 : " + (System.currentTimeMillis() - curTime) + "ms");
////
////		//then
//////		System.out.println(jdbcAddressRepo.count() + " " + addresses.size() + " ");
//////		assertThat(jdbcAddressRepo.count()).isEqualTo(expectedCount);
////
////	}
//
//}
