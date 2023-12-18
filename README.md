# Shopping-Mall

Frontend : Thymelef + Javascript + Jquery + html + css + bootstrap4 <Br>
Backend : Java + Springboot + Mybatis + MariaDB + Spring Security <br>
Image Storage : Amazon S3 <br>
library : coolsms, PortOne <br><Br>

1. 회원 비밀번호 유출 방지와 불특정한 사용자의 Server 접근을 막기 위해 Spring Security를 사용하여 해쉬 암호화, 페이지 접근시 인증 요구<br>
2. DB에서 상품의 이미지 파일을 가져올 때, 복잡성을 낮추기 위해 상품 테이블과 이미지 파일 테이블 분리. Join query를 사용하여 가독성, 유지보수성 높임
4. 다량의 상품 이미지 관리 효율성과 배포 문제를 위해 원격 Storage인 S3에 저장 <br>
6. 불필요한 DB 접근을 막기위해 장바구니 기능에 사용자 개인 저장소인 Web Local Storage 사용 -> DB 낭비를 막고 query 사용을 줄여 성능 향상 <br>

2. Client에 따른 웹페이지 동적 생성을 위해 Thymeleaf 의존성 추가, Springboot MVC 패턴, Ajax로 Server와 통신 <br>
1. 회원, 상품, 주문내역의 저장과 관리를 위해 Maria DB 이용, 통신은 Mybatis 사용 <br>

2023-07-23 : 프로젝트 생성, 홈페이지 "메인 화면" 피그마 작업

2023-07-29 : Maria DB로 데이터베이스 생성, 서버와 연결

2023-08-11 : ERD 설계 (고객, 장바구니, 카테고리, 상품, 주문, 상세주문내역)

2023-08-12 : ORM 매핑, html 학습 (mainPage title 이미지 설정)

2023-08-14 : Figma 작업 (상품 상세페이지, 카테코리 페이지, 회원가입 페이지, 로그인 페이지)

2023-08-17 : 메인 페이지 UI 완성

2023-08-19 :  회원가입 UI 완성

2023-08-22 : Amazon S3 버킷 생성

2023-08-23 : html의 이미지 경로 DB에서 불러오기 (수정)

2023-08-28 : 상품 상세페이지의 '색상','사이즈' 옵션 목록, DB에서 가져와 mapping 시키기

2023-09-08 : 회원가입 - 아이디 중복 체크, 비밀번호 정확도 체크

2023-09-10 : 회원가입 - coolsms으로 휴대폰 인증, 아이디 중복 미체크 시 가입불가, 휴대폰 인증 미실시 시 가입불가, 회원가입 DB 저장 로직 완성

2023-09-13 : 로그인 - UI modal 형식으로 구현

2023-09-16 : Spring Security - 초기설정, 로그인 완료

2023-09-20 : Spring Security - 비밀번호 암호화, 로그아웃 완료, 로그인 없이 ajax 통신 시 에러 발생 - 접근 권한 수정하여 해결

2023-09-24 : Thymeleaf Layout 적용

2023-09-27 : 장바구니 페이지 피그마 작업, UI 중 테이블 구현

2023-10-09 : 상품 상세페이지 - 상품선택 결과 테이블로 수정

2023-10-10 : 상품 상세페이지 - Jqeury 추가, 옵션 테이블 CSS, 테이블에 상품 삭제 버튼 추가

2023-10-11 : 상품 상세페이지 - 상품 선택 테이블 자료구조 변경, 상품 삭제 버튼 기능 구현

2023-10-14 : 장바구니 담기 - Ajax Request

2023-11-03 : 장바구니 - Local Storage 사용 / 테이블 동적 생성 / 페이지마다 MyBasket 버튼 활성화

2023-11-07 : 장바구니 - Refactoring, 총 결제 금액 계산, 결제 API 테스트

2023-11-08 : 권한 필요한 페이지 접근시 이동 URL 새로 구현

2023-11-10 : js 파일간 데이터 공유 -> WebPack 설치

2023-11-11 : js 파일간 데이터 공유 -> WebPack 삭제, local Storage 사용   /   주문서 페이지 - 상품 테이블, 주문자 정보 테이블 Front 작업

2023-11-14 : Spring Security - UserDetails 수정 / 상품 주문 화면 구현

2023-11-15 : 주문내역, 주문 아이템 상세 테이블 수정, mapper 수정

2023-11-20 : CustomerMapper, OrderMapper Refactoring

2023-11-21 : 주문완료 -> DB저장 완료, MyPage.html 페이지 생성

2023-11-22 : 회원 주문 내역 SELECT 완성, 결제내역 Figma 완성, 결제 내역 html 페이지 진행 중

2023-11-29 : 주문 완료 페이지 완성

커넥션 누수 방지하기
이미지 외부 CDN 사용 전후 비교

<br><br>

## Trouble Shooting [Link](https://sky-pail-416.notion.site/Trouble-Shooting-f4dfed6ea5e74e429cf9a77326927e49?pvs=4)

❗ [Ajax와 Spring Security 이슈](https://sky-pail-416.notion.site/Ajax-Spring-Security-8223e0b6bd4943bda85787aa805d6aa9?pvs=4)

❗ [Session Storage 이슈](https://sky-pail-416.notion.site/Session-Storage-22f01785b0a0492b9fe5eb7915b0dfcf?pvs=4)

❗ [Login Modal과 Spring Security 이슈](https://sky-pail-416.notion.site/Login-Modal-Spring-Security-3fa1959914744b13877e7b0866d32b6d?pvs=4)

❗ [JS 파일 간의 데이터 전송](https://sky-pail-416.notion.site/JS-1fe7e289d1e04780a0a1d964527b2d13?pvs=4)

❗ [관계 매핑 과정에서 겪은 Mybatis 이슈](https://sky-pail-416.notion.site/Mybatis-59c011c4c6454de4ac79ed46b8e202d6?pvs=4)
