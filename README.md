## 프로젝트 구조 (멀티 모듈 설계)

Clean Architecture 기반으로 **Presentation**, **Domain**, **Data**, **App** 으로 구성

---

### Presentation

사용자 인터페이스 관련 로직 처리, 사용자 입력 수신, Domain Layer와 상호작용

| 패키지 | 설명 |
|---|---|
| `view` | Jetpack Compose와 같은 UI 요소 |
| `viewModel` | UI 데이터를 가져와 처리하고 UI로 전달하는 역할 |
| `viewState` | Loading, Data, Error 등과 같은 UI의 상태를 나타내는 데이터 클래스 |

---

### Domain

핵심 비즈니스 로직 처리, Presentation과 Data 간의 중재 역할

| 패키지 | 설명 |
|---|---|
| `usecase` | 특정 비즈니스 로직을 처리하는 단위, 핵심 로직을 포함 |
| `repository` | Repository의 인터페이스(추상화) 정의, Data Layer의 구현체와 분리하여 의존성 역전 원칙(DIP) 준수 |
| `model` | 비즈니스 로직에서 사용되는 핵심 데이터 모델, UI나 DB/API 스키마에 의존하지 않는 순수 도메인 객체 |

---

### Data

외부 데이터소스(API, Local DB 등)와의 통신을 담당, Domain Layer에 데이터를 제공

| 패키지 | 설명 |
|---|---|
| `local/dao` | Room Database 접근을 위한 Data Access Object, 로컬 데이터의 CRUD 쿼리 정의 |
| `local/database` | Room Database 인스턴스 및 설정, DB 버전 관리와 마이그레이션 포함 |
| `local/entity` | Room DB 테이블과 매핑되는 데이터 클래스, DB 스키마에 종속적인 구조 |
| `remote` | API 통신을 위한 Retrofit 인터페이스 및 DTO(Data Transfer Object), 서버 응답을 Domain Model로 변환하는 Mapper 포함 |
| `repository` | Domain Layer에 정의된 Repository 인터페이스의 실제 구현체, Local/Remote 데이터소스를 조합하여 데이터 제공 |

---

### App

앱의 전반적인 설정과 DI(Dependency Injection) 및 앱의 생명주기를 관리

| 패키지 | 설명 |
|---|---|
| `Application` | 앱의 초기 설정 및 전역 상태 관리 |
| `DI 모듈` | 의존성 주입을 위한 설정, 각 레이어의 구성 요소를 연결 |
