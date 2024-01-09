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
- AWS or GCP 클라우드 [미정]
- Github Action or jenkins [미정]

<hr>

### 📕 구현 예정 기능

- [x] Base on ResultFul API
- [ ] Spring Security를 통한 로그인 기능 ( 토큰 재발급 기능)
- [x] __ 시 검색 기능
- [x] __ 동 검색 기능
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

2. 아파트 이름을 기준점으로 삼아서 Sales 데이터를 넣는다. [ 진행중 ]
> 현재 이 방법으로 unique한 Building과 Sales 관계를 1 : N으로 엮을 수 있다고 판단. <br>
> 하지만 이 방법은 건설사에 따라 아파트명이 똑같은 경우들이 많기 때문에, 시군구코드와, 읍면동코드, 아파트이름 3개를 묶어서 판단 시키기로 결정했다.


</details>

<hr>

## 실수하면 안되는 부분 & 새롭게 알아가는 부분

#### 1. String -> LocalDate Parsing
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