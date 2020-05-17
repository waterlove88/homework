# Crawler
## 요구사항

* 입력 : URL, 파싱타입(태그제외, 전체), 출력묶음단위
* 출력 : 알파벳, 숫자에 대한 몫, 나머지
* 모든 문자에 대해 영어, 숫자를 출력
* 오름차순으로 정렬
    * 알파벳 : 사전 + 대문자 ex) AaBbCc...Zz
    * 숫자 : 0, 1, 2, ...
* 교차출력
    * 영문, 숫자, 영문, 숫자 순으로 번갈아가면서 출력
* 출력묶음단위
    * 출력묶음단위로 몫과 나머지를 구함
    * 출력묶음단위가 1이면 몫이 데이터, 나머지는 없음
    * 출력묶음단위가 데이터보다 크면 몫은 없음, 나머지가 데이터
    
## 어플리케이션 버전
* Spring boot : 1.5.9.RELEASE
* 빌드 도구 : maven

## API Request
* 요청 데이터
    * url : 파싱url, 필수
    * type : 파싱타입, 필수(excludeTag:태그제외, allText:전체)
    * outputBundleUnit : 출력묶음단위, 필수(최소입력 값 1)
* 요청 API
    * MEHODE : POST
    * URI : contents
    * Content-Type : application/json
    * example : http://localhost:8080/contents

## API Response
* 응답 데이터
    * code : 응답코드(HTTP CODE 규칙을 따름)
    * message : 응답 메시지
    * Data : 성공일 경우 리턴
        * quotient : 몫
        * remainder : 나머지
