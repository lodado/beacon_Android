# beacon_Android

한이음 공모전 beacon 부분 코드

비콘 foreground 서비스만 추가한 예제입니다.

![image](https://user-images.githubusercontent.com/40421183/130344834-f31a2ff1-35db-42e0-8f83-5948a2d50b41.png)

에뮬레이터는 블루투스 부분이 없으므로 당연히 에뮬레이터론 실행 안되고, bluetooth 4.0 이상 기능이 존재하는 안드로이드 스마트폰으로 작동하면 

주변 비콘의 정보와 거리를 log로 출력합니다.

## target SDK

target 29

컴파일 버젼 29

집 핸드폰 버전이 29라 29로 맞춰놨는데 큰 차이는 없습니다.

api 23 이상이면 됩니다.

## HM-10 (아두이노) 사용

 Ibeacon 포멧을 사용했습니다.
 
 
```
AT+RENEW ß 공장 초기화

AT+RESET ß HM-10 리붓

AT ß 시험 작동

AT+MARJ0x1234 ß iBeacon의 Major number설정 (0x1234는 임의값 설정 가능)

AT+MINO0xFA02 ß iBeacon의 Minor number설정 (0xFA02는 임의값 설정 가능)

AT+ADVI5 ß advertising(신호 송출) 주기를 5로 설정(약 0.5초)

AT+NAMEBBANGPAN ß HM-10 이름 정의 (BBANGPAN은 임의값 정의 가능)

AT+ADTY3 ß 전원 절약을 위해 맺지않음(non-connectable)모드로 설정

AT+IBEA1 ß iBeacon을 활성화

AT+DELO2 ß iBeacon의 broadcast-only 로 설정

AT+PWRM0 ß 전원 절약을 위해 auto-sleep으로 설정(최소 절전 모드)

AT+RESET ß 리붓하여 반영
```
 
출처: https://bbangpan.tistory.com/17 [빵판닷컴 (bbangpan.com)]

## 비콘 라이브러리(altbeacon)

https://github.com/AltBeacon/android-beacon-library

이 레포지토리는 백업용으로 생성된 파일이고, gitlab에 나머지 부분은 계속 업데이트 되어 있습니다.

