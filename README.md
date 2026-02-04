## 요구 사항 및 구현 사항

- [x] 기본적인 콘솔 기반 CRUD
- [x] 사용자 입력값에 대한 검증 및 예외 처리
    - 형식 및 명령어 유효성
    - 특수 기호 사용 여부
- [x] MVC , Command 패턴
    - 추상화 기반 Map -> annotaion & reflection 리팩토링
- [x] Tell, don't ask 고려

***

### 배운점

- 파일 기반 작업 시 파일 폴더는 프로젝트 폴더 바로 하위에 위치
- 파일 외부 자원에 대해 초기 상태 메서드 정의 (파일이 비어있을 경우 초기화, 생성 ...)
- Optional<T> , Stream<T> 간의 체이닝 (ex. filter().findFirst().orElseThrow(~) )
    - Comparator도 functional interface -> lambda가능 , 정적 메서드 파라미터로 Function ..
- Jackson 라이브러리 , json
    - @JsonCreator
    - @JsonProperty
    - json - record와 함께 사용
- reflection을 통한 메서드 호출 시 컴파일 시점에는 발생할 에외를 알 수 없다
    - InvocationTargetException 으로 감싸서 해결