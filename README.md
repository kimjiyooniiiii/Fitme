<img width="800" alt="image" src="https://github.com/kimjiyooniiiii/Shopping-Mall/assets/117561820/e2670930-e134-4156-9796-416ed320c342">

<br>

## ✔ 개발 목표
카테고리 별로 상품을 찾고, 장바구니에 담아 모바일 결제까지 가능한 여성 웹 쇼핑몰. <br>
비회원 사용자에게도 장바구니 기능을 제공하고, 상품 상세 페이지에서도 미리 가격 계산을 할 수 있는 기능을 제공한다.

<br>

#### 🕐 개발 기간 : 2023.08 ~ 2023.11 (2개월)

#### 👤 개발 인원 (1명) : 김지윤 (Full-Stack)

<br>

## ⚙ Tech Stack
#### Back-End
<img height="25px" src="https://img.shields.io/badge/java 17-CC6699?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/Spring boot 3.1.2-CC6699?style=for-the-badge&logo=springboot&logoColor=white"> <img height="25px" src="https://img.shields.io/badge/Spring Security 6.1.2-CC6699?style=for-the-badge&logo=springsecurity&logoColor=white"> <img height="25px" src="https://img.shields.io/badge/MyBatis-FF9900?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/MariaDB-FF9900?style=for-the-badge&logo=mariadb&logoColor=white">

#### Front-End
<img height="25px" src="https://img.shields.io/badge/JavaScript-4479A1?style=for-the-badge&logo=javascript&logoColor=white"> <img height="25px" src="https://img.shields.io/badge/HTML-4479A1?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/CSS-4479A1?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/JQuery-6DB33F?style=for-the-badge&logo=jquery&logoColor=white"> <img height="25px" src="https://img.shields.io/badge/Thymeleaf-6DB33F?style=for-the-badge">

#### ETC
<img height="25px" src="https://img.shields.io/badge/Git-232F3E?style=for-the-badge&logo=git&logoColor=white"> <img height="25px" src="https://img.shields.io/badge/CoolSMS-232F3E?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/PortOne-232F3E?style=for-the-badge"> <img height="25px" src="https://img.shields.io/badge/AWS S3-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

<br>

## ✔ 개발 내용
- “장바구니 기능”의 비회원 확장을 위해, Client 저장소(Web Storage)를 사용. 그 결과, 기능 확장 및 DB 자원 절약.
- “주문 상태” 자료구조 개선. 문자열 → Enum Type으로 Refactoring 하여 유지보수성을 높임.  [Details](https://www.notion.so/Enum-9d8c1cf130be47d1b0258c94e27d964b?pvs=21)

- Server-Side Rendering 개발 환경에 적합한 “Session인증 방식”을 사용해 “사용자 인증기능” 개발
- 쇼핑 과정의 “사용자 경험”을 고려해 “상품 페이지”에 “옵션 가격 계산” 기능 추가
- 주문자 정보의 정확성을 위해 CoolSMS API를 사용해 “본인 인증” 기능 개발
- 그 외 이슈 해결 과정
  -  Spring Security 도입 후 생긴 Ajax 이슈 해결  [Details](https://www.notion.so/Ajax-Spring-Security-8223e0b6bd4943bda85787aa805d6aa9?pvs=21)

  -  Spring Security 인증과 로그인 Modal 동시사용 이슈  [Details](https://www.notion.so/Modal-Spring-Security-3fa1959914744b13877e7b0866d32b6d?pvs=21)

  -  JS 파일 간의 데이터 공유를 위해 시도한 방법들  [Details](https://www.notion.so/JS-1fe7e289d1e04780a0a1d964527b2d13?pvs=21)

<br>

## ✔ 설계
<img width="900" alt="image" src="https://github.com/kimjiyooniiiii/Fitme-WebService/assets/117561820/f62305a5-30b0-41c6-8777-b68581393e1c">

<br><br>

### ERD
<img width="817" alt="image" src="https://github.com/kimjiyooniiiii/Fitme-WebService/assets/117561820/741167ef-3898-4357-8e03-70ac809523a0">

<br><br><br>

## ✔ 시연 영상
### 🎞 회원가입 및 로그인
![ezgif com-video-to-gif-converter](https://github.com/kimjiyooniiiii/Fitme-WebService/assets/117561820/d3adef08-8fa0-487a-97e1-2385f658ed1d)

<br>

### 🎞 장바구니
![ezgif com-video-to-gif-converter (1)](https://github.com/kimjiyooniiiii/Fitme-WebService/assets/117561820/a56e6ec1-4ce8-414c-8219-8f46be511599)

<br>

### 🎞 결제
![ezgif com-video-to-gif-converter (4)](https://github.com/kimjiyooniiiii/Fitme-WebService/assets/117561820/f97b726a-b5e0-41bf-b278-4d48df460cba)


