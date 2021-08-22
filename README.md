# beacon_Android

한이음 공모전 beacon 부분 코드

비콘 foreground 서비스만 추가한 예제입니다.

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

이 레포지토리는 백업용으로 생성된 파일이고, gitlab에 나머지 부분은 추가되어 있습니다.

