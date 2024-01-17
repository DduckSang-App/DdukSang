# Repository_name : DdukSang

## 🍕 공공API 토이프로젝트

### 📍 Project Role
- Front-End :  first-lounge (Jang-Hyun-Lim)
- Back-End : zidh1(Ki-Uk-Kim)

### 📍 Language for Project
- Front-End : Html, Java Script
- Back-End : Java(FrameWork : Spring Boot) 


### 📍 Development Schedule
- JIRA 기반 Git 관리
- AWS EC2 & RDS
- Github Action or jenkins [미정]
- Monitoring : VisualVM

<hr>

### 📕 구현 예정 기능

- [x] Base on ResultFul API
- [ ] Spring Security를 통한 로그인 기능 ( 토큰 재발급 기능)
- [x] __ 시 검색 기능
- [x] __ 동 검색 기능
- [x] 검색된 지역의 아파트 매물 리스트
- [ ] 지역 매매 추이 API
- [ ] 회원 별 관심 아파트 기능 [ + 알림]

<hr>

### 팀 관련 게시판


<hr>

## Git Convention

### 포맷

```
type: subject

body
```


#### type

- 하나의 커밋에 여러 타입이 존재하는 경우 상위 우선순위의 타입을 사용한다.
- fix: 버스 픽스
- feat: 새로운 기능 추가
- refactor: 리팩토링 (버그픽스나 기능추가없는 코드변화)
- docs: 문서만 변경
- style: 코드의 의미가 변경 안 되는 경우 (띄어쓰기, 포맷팅, 줄바꿈 등)
- test: 테스트코드 추가/수정
- chore: 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우 (프로덕션 코드 변경 X)

#### subject

- 제목은 50글자를 넘지 않도록 한다.
- 개조식 구문 사용
    - 중요하고 핵심적인 요소만 간추려서 (항목별로 나열하듯이) 표현
- 마지막에 특수문자를 넣지 않는다. (마침표, 느낌표, 물음표 등)

#### body (optional)

- 각 라인별로 balled list로 표시한다.
    - 예시) - AA
- 가능하면 한줄당 72자를 넘지 않도록 한다.
- 본문의 양에 구애받지 않고 최대한 상세히 작성
- “어떻게” 보다는 “무엇을" “왜” 변경했는지 설명한다.


<hr>

### ERD 다이어그램

![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/d4716304-7369-4bbe-9ec4-f2b58cba5db8)


<hr>

### 아키텍처 구조

<hr>

<br>

# 중요 ISSUE & Trouble Shooting

## 1. 대용량의 데이터 저장

<details>


<summary> 관련 내용 </summary>

> 1차 계획은 공공포털 API 데이터를 모두 받아온 후, DB로 저장하여 이를 통하여 매매 추이를 보여주려는 계획 이였음.

### 문제점 1: 전지역의 매매 데이터가 20만개 이상으로 이루어져 있고, 이를 년도별로 받을 경우 트래픽 초과로 도중에 서버가 끊기는 문제점 발생


#### 1. 문제해결을 위해 먼저 지역코드를 시/도 & 시/군/구 코드로 나눈다.

#### 2. 해당 코드를 통해서 DB를 저장한다.

#### 3. 저장된 DB를 바탕으로 매매 추이를 계산한다
 - 이미 저장되어 있을 경우, update[1달 기준] 날짜를 통해서 알려준다.


### 문제점 2 : DB Insert 시, 20651개 DB기준 5분 30초가 걸린다.

#### JPA Batch Size, JDBCTemplate Batch Insert, Mybatis batch insert 성능 비교

> 실제로 일반적인 insert 수행 시, 20,651개의 행정코드 주소 저장하는데 5m 5.34s가 걸림 서버 기준 약 30분 <br>
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/b8de1447-f0aa-483c-b16b-3c264c19b2e5)

### * 실제 테스트 내용 [일반적인 Insert 수행 시 제외] - Mockito && Junit 테스트 코드 사용

1. 받아 들어오는 데이터를 List 형태로 저장후 saveAll 명령어 성능

> 코드 비교 <br>
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/f65b4697-3848-4f9b-98ee-aaa3aeda2085)

> 실제 성능은 13m이 걸려서 더욱 더 안좋은 성능을 맞이했다 <br>
> 이 경우, 원래 save() 함수가 트랙잭션을 하나씩 연결시켜 더 오래 걸린다고 알고있다. <br>
> 다시 시도해보고 남길 예정입니다. <br>
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/baba1224-1dbf-4e49-94ab-3ea2f1c89ee6)

2. 응답값을 DTO로 변환시킨후 Builder 패턴 사용
> 테스트 측정 시간 2573ms
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/029a7335-7b26-495c-b121-cdfaf4039ce3)

> 실제 API 테스트 측정 시간 7.82s
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/f1d78810-5cb4-4089-a7e0-c097a9bbd73f)

> 서버 테스트 35m -> 5m
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/7d1c80df-7350-4fa6-bd00-b4ce9da359d5)

> Builder 패턴을 사용할 시, 13000ms -> 5782ms로 놀라운 향상을 보였다.


3. 추가적인 작업
> JPA Batch Insert를 통해서 대량의 SQL 그룹을 만들어서 보내면 더 빨라지지 않을까라고 생각하였다. [multi-value insert]
> 그런데 JPA Batch Insert에서는 쓰기 지연을 통해서 동작한다. -> 기존 데이터베이스 ID 저장 방식은 저장 된 뒤 id가 할당된다. -> IDENTITY 방식 도입이 불가능하다.
> 기존 ID 전략 vs Batch Insert ID 전략에 대해서는 생각해보아야한다. [Mysql이 Sequence 전략을 사용 못하기 떄문]
> 따라서, 기존 IDENTITY 전략을 이용한 JDBC Template를 활용해서 Batch Insert를 진행해본다.
> 현재 코드 오류로 인해서 추가 수정 할 예정

<hr>

 - 고민해 볼 부분 : 만약 업데이트 됐을 때 - 기존값과 추가된 값을 어떻게 표시할 건지
> 이 부분에 대해서는 지역 주소가 담긴 부분에 대해서 1:N으로 매물을 년/월 별로 나눠줄 예정
</details>

## 2. 공공API -> 주소만 가지고 있는 문제
<details>

<summary> 관련 내용 </summary>

> DB에서 조회한 걸 어떻게 반환할 지 고민이 많아졌음

### 해결 방안 [ 해보는 중 ]

- KaKao 지도 API를 이용하므로 실매매 주소를 저장할 때 카카오 API 위도,경도 API를 이용해서
DB에 y,x 값 저장 예정

</details>

## 3. DB의 연결성 문제
<details>

<summary>관련 내용</summary>

1. Building [건물 정보] -> Sales[판매 정보]
> 이 부분에 대해서는 다양한 시도를 해볼 예정이다.
 
1.건물 정보를 저장할 때 count()나 last_insert_id()를 통해서 구현 [ 불가능 ]
> 불가능인 부분은 last_insert_id()가 multi-insert query에서는 count가 하나로 체크가 된다. <br> -> 이 부분은 어렵게 접근하였다.
> 마지막에 ID를 반환해주는 것으로 쉽게 해결된다....! 

> count()를 했을 때, 매번 행의 개수를 세준다면 -> 엄청 큰 데이터들이 모여있다면 낭비가 심하다.<br>
> 또한, 해당 값을 통해서 중복된 건물에 대해서 불가피한 쿼리가 나타난다.

<br>
따라서 Exists를 이용하는 방안으로 1차 접근. <br>
-> 하지만, JPA의 경우 EXISTS를 지원하지 않으므로, QueryDSL 사용

>  /*
> SELECT EXISTS( <br>
SELECT 1<br>
FROM address<br>
WHERE located_nm LIKE "%서울특별시 종로구1%"<br>
)*

<br><br>

2. 아파트 이름을 기준점으로 삼아서 Sales 데이터를 넣는다. [ 진행중 ]
> 현재 이 방법으로 unique한 Building과 Sales 관계를 1 : N으로 엮을 수 있다고 판단. <br>
> 하지만 이 방법은 건설사에 따라 아파트명이 똑같은 경우들이 많기 때문에, 시군구코드와, 읍면동코드, 아파트이름 3개를 묶어서 판단 시키기로 결정했다.

3. 공공API를 받아왔을 때, Address-Building-Sales를 효율적으로 연결시키는 방안
> 현재 API를 받아왔을 때, Address-Building / Building-Sales를 DB에 접근해서 있는지 확인하는 과정이 필요<br>
> 이 부분을 Redis나 Elastic Search를 이용해서 조금 더 효율적으로 짤 수 있지 않을까? 라는 고민중....

</details>

## 4. 순환 참조 문제
<details>
<summary>관련 내용</summary>

![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/81dd2ae8-a24e-4d8c-b71d-31c904f5c398)

> 위의 부분에서 single Circular References [순환 참조] 문제가 발생하였다. <br>
> 그 이유는, BuildingService는 Bean으로 등록이 되려면 해당 Service안에 BuildingService가 Bean으로 등록이 되어야한다. <br>
> 이렇게 되면 서로를 순환참조 하기 때문에 어떠한 Bean도 생성하지 못한다.

</details>

## 5. Group By vs DISTINCT / VisualVM을 통한 모니터링
<details>
<summary>관련 내용</summary>

[관련 링크](https://stackoverflow.com/questions/7943957/huge-performance-difference-when-using-group-by-vs-distinct)

> 위의 링크에 따르면 기본적으로 Group By와 DISTINCT에 대해서는 장단점이 있다.<br>

> 먼저 DISTINCT의 작동방식은 <br>
>> 1. DISTINCT로 나누려는 key에 대한 값을 임시 저장소에 저장한다.<br>
>>2. 임시저장소를 정렬한다.<br>
>>3. 임시저장소를 Sacn한 후, 이전 값과 다른 값에 한해서 return 해준다. <br>

-> 이 경우, Memory 사용량을 최적화 시키기 좋은 방법이라고 알려져있다.

> 다음으로 Group By의 작동방식은<br>
>> 1.테이블을 전체 Scan 한 후, 각각의 value에 대해서 hash table에 대해서 저장한다.<br>
>> 2.hash table의 값을 반환한다.

-> 이 경우, 속도를 최적화 시키기 위한 방법이라고 알려져있다. 하지만, 다른 키들이 많을 수록 높은 메모리 사용량을 요구한다고 한다.

<br>
** 이 부분에 대해서 추후에 자세히 알아보자

<br><br>
> Building에 대한 SalesList를 담기위해서 먼저 API를 불러올 시군구 코드를 불러와야한다.<br>
> 
> 해당 부분에서 먼저 25000개 데이터에 대한 주소를 찾아오는 성능을 올리고 싶어 대표적인 3개를 비교해보고자 하였다.

### 수행 비교 결과

1. Spring boot의 stopWatch를 이용한 결과<br>
![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/3c9a2e0c-b94e-4dd9-b259-2fe2ba74184c)

> 해당 결과에서는 Query를 모두 날린 결과 DISTINCT 와 GroupBy에서는 차이가 많이 나지 않았지만, 모든 절을 쿼리로 뽑는 것과 10배 차이가 났다.

2. VisualVM을 이용한 결과<br>
![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/4ac3b8fe-a124-4508-a6f7-9252ad270875)

> VisualVM의 결과를 확인해보면 비율상 DISTINCT 와 GroupBy에 비해 3배 가량 TotalTime이 기록되었다.





</details>

<hr>

## 실수하면 안되는 부분 & 새롭게 알아가는 부분

### 1. String -> LocalDate Parsing
<details>
<summary>관련 내용</summary>

> Integer 형태의 변수를 Date Type으로 파싱하려던 중 오류를 맞이하였다. <br>
> DateTimeFormatter.ofPattern 사용 할 시, 원하는 형태로 출력을 마무리할 수 있다. <br>
> 하지만, 날짜의 경우 "yyyy-MM-dd" ISO_LOCAL_DATE의 기본 타입을 유지해야하기 때문에, 2019-3-05 같은 경우 index parsing 에러가 발생. <br>
> 따라서 LocalDate의 객체를 만들어 줌으로써 해결할 수 있었다.

하지만, 년도만 입력 받을 때는 에러가 발생한다.
> 기본적으로 LocalDate(Date)의 기본적인 형식은 "yyyy-mm-dd" 형식으로 들어가기 때문에, mm-dd형태를 0으로 잡는 형식으로 진행. <br>
> -> Year type으로 받으면 해결되는 문제였다..


</details>

### 2. QuereyDSL

<details>
<summary>관련 내용</summary>

> SELECT Count 쿼리 절과 Exists의 성능 차이에 대해서 고민을 하게 되었다.<br>
> 따라서 입력된 데이터를 기준으로 성능 체크를 해 본 결과를 나타낸다. 

[QueryDSL 도입 관련](https://velog.io/@soyeon207/QueryDSL-Spring-Boot-%EC%97%90%EC%84%9C-QueryDSL-JPA-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0)

</details>

### 3. ViewResolver (feat. @Controller vs @RestController)
<details>
<summary>관련 내용</summary>

- Address에 대한 조회 성능을 체크하기 위해서 Controllre Annotation에서 진행하는데 Check your ViewResolver setup! 라는 오류가 발생하였다.

> RestController는 Controller에 ResponseBody를 결합한 형태와 동일하게 나타난다. <br>
> ![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/0bca10ca-c2ab-4795-ae93-57363328c8c7) <br>
> 위의 그림과 같이 <br>
>> 1.Client는 URI 형식으로 웹 서비스에 요청을 보낸다.<br>
>> 2.DispatcherServlet이 요청을 처리할 대상을 찾는다.<br>
>> 3.HandlerAdapter을 통해 요청을 Controller로 위임한다.<br>
>> 4.Controller는 요청을 처리한 후에 객체를 반환한다.<br>
>> 5.반환되는 객체는 Json으로 Serialize되어 사용자에게 반환된다.<br>

하지만, Controller Annotation을 사용할 때, ResponseBody를 사용하지 않으면
>![image](https://github.com/DduckSang-App/DdukSang/assets/75063989/dc25ec25-73fc-4747-a0d5-863b1d8b2fe3)<br>
>> 1.Client는 URI 형식으로 웹 서비스에 요청을 보낸다.
>> 2.DispatcherServlet이 요청을 처리할 대상을 찾는다.
>> 3.HandlerAdapter을 통해 요청을 Controller로 위임한다.
>> 4.Controller는 요청을 처리한 후에 ViewName을 반환한다.
>> 5.DispatcherServlet은 ViewResolver를 통해 ViewName에 해당하는 View를 찾아 사용자에게 반환한다.
<br>

위의 형식 처럼 진행이 되는데,
<script src="https://gist.github.com/KiUkKim/e2c587f730de69ee6a274c3468433e47.js"></script>

<br>
해당 코드 처럼, 반환이 되게 된다면 View에 대한 생성되지 않았으므로, 오류가 발생했다.


</details>
