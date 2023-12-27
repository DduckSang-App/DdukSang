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
- [ ] __ 시 검색 기능
- [ ] __ 동 검색 기능
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

<hr>

### 아키텍처 구조

<hr>

<br>

# 중요 ISSUE & Trouble Shooting

## 1. 대용량의 데이터 저장

> 1차 계획은 공공포털 API 데이터를 모두 받아온 후, DB로 저장하여 이를 통하여 매매 추이를 보여주려는 계획 이였음.

- 문제점 1: 전지역의 매매 데이터가 20만개 이상으로 이루어져 있고, 이를 년도별로 받을 경우 트래픽 초과로 도중에 서버가 끊기는 문제점 발생


#### 1. 문제해결을 위해 먼저 지역코드를 시/도 & 시/군/구 코드로 나눈다.

#### 2. 해당 코드를 통해서 DB를 저장한다.

#### 3. 저장된 DB를 바탕으로 매매 추이를 계산한다

#### 3-1. 이미 저장되어 있을 경우, update[1달 기준] 날짜를 통해서 알려준다. 