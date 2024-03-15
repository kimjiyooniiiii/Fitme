<img width="800" alt="image" src="https://github.com/kimjiyooniiiii/Shopping-Mall/assets/117561820/e2670930-e134-4156-9796-416ed320c342">

<br>

## ✔ 개발 목표
카테고리 별로 상품을 찾고, 장바구니에 담아 모바일 결제까지 가능한 여성 웹 쇼핑몰. <br>
비회원 사용자에게도 장바구니 기능을 제공하고, 상품 상세 페이지에서도 미리 가격 계산을 할 수 있는 기능을 제공한다.

<br>

### 🕐 개발 기간 : 2023.08 ~ 2023.11 (2개월)

### 👤 개발 인원 (1명) : 김지윤 (Full-Stack)

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
- “장바구니”기능에서 DB 접근 횟수를 줄이고 프로그램 결합도를 낮추기 위해 Client 저장소인 Web Storage를 사용. 그 결과 Server의 복잡도 개선과 DB 접근이 없어지고 비회원도 장바구니 이용 가능하게 됨.

- 사용자 “인증, 인가” 방식으로, Framework가 한 개인 개발 환경에 맞게 Session 인증 방식을 사용
- “주문 상태”를 문자열로 저장했을 때, 오타로 인한 문제 발생. 이를 개선하기 위해 Enum Type으로 리팩토링하여 유지보수성을 높임.  [Details](https://www.notion.so/Enum-9d8c1cf130be47d1b0258c94e27d964b?pvs=21)
- 향상된 사용자 경험을 위해 “상품 상세 페이지”에서 빠르게 가격 계산을 해주는 기능 필요. 이를 위해 Web Storage에 선택 옵션을 저장하고 Client에서 가격 계산하는 기능을 추가함.
- 주문 정보의 정확성을 위하여 “본인 인증” 기능 개발 필요. CoolSMS API를 사용하여 구현.
- 그 외 이슈 해결 과정
  -  Spring Security 도입 후 생긴 Ajax 이슈  [Details](https://www.notion.so/Ajax-Spring-Security-8223e0b6bd4943bda85787aa805d6aa9?pvs=21)

  -  Spring Security에 Modal 사용해 보기  [Details](https://www.notion.so/Modal-Spring-Security-3fa1959914744b13877e7b0866d32b6d?pvs=21)

  -  JS 파일 간의 데이터 공유를 위해 사용한 방법들  [Details](https://www.notion.so/JS-1fe7e289d1e04780a0a1d964527b2d13?pvs=21)

<br>

## ✔ 설계
