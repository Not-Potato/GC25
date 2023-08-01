# GC25 : Semi Team Project
> "강사, 시설, 커리큘럼 등의 **항목별 평가**를 **직관적**으로 입력하고, 열람해 보자!"
>
> **IT 학원 평가 웹 사이트** 개발 프로젝트

<br />   

### 사용된 기술 스택
- JSP/Servlet
- JSTL
- JDBC
- Oracle

<br />   

### 팀원 소개 & 담당 기능 소개
- **김자연**  `팀장`
  - 학원명 자동 완성 검색창 구현
  - 게시글 작성
  - 게시글 정렬 및 페이징 처리
  - 모든 view page의 레이아웃 구성 및 css 작업
     
- **이형주**
  - KakaoMap API + REST API를 이용한 학원 관련 DATA 추출
  - 게시글 열람/수정/삭제
  - 게시글 추천/댓글
 
- **서수현**
  - 회원가입/인증 메일 발송/로그인
  - 회원 정보 업데이트/프로필 사진 저장 및 로드
 
<br />    

### 주요 기능
#### [프로젝트 요구사항]
```
1. 사이트에는 "준회원"과 "정회원", 총 두 종류의 사용자가 있습니다.
2. 회원 가입 시, 자동으로 부여되는 등급은 "준회원" 등급입니다.
3. "준회원"은 상세 글 보기 화면, 글 작성 화면에 접근할 수 없습니다.
4. 게시글을 한 개 이상 작성하면 "정회원" 등급으로 업그레이드됩니다.
5. 회원은 각각의 게시판에서 최신/댓글수/추천수/조회수 순으로 목록을 조회할 수 있습니다.
6. 회원은 게시글에 댓글을 작성할 수 있으며, 각 게시글마다 최초 1 번 추천할 수 있습니다.
7. 회원은 학원 찾기 메뉴에서 학원을 조회할 수 있습니다.
8. 회원은 마이페이지 내에서 프로필 사진, 비밀번호, 닉네임을 변경할 수 있으며, 탈퇴할 수 있습니다.
9. 작성된 글은 작성 회원 본인만이 수정/삭제할 수 있습니다.
```
----
#### [게시글 관련] `김자연`   
> 학원명 자동 완성 검색창 구현
  1. `html` "학원명" input 칸에 사용자가 입력한 데이터를 받아옵니다.
  2. `JS` 받아온 값이 비어 있지 않다면 ajax를 이용하여 controller와 통신합니다.
  3. `Java` DB로 접근하여 %LIKE%문을 통해 사용자가 입력한 값을 포함하는 학원 명을 검색하고, 받아온 리스트를 JSONArray 형태로 담아서 반환합니다.
  4. `JS` 받아온 배열이 존재하면 화면에 출력하도록 하고, 키보드를 이용해 목록에서 이동하고 선택할 수 있도록 합니다.
  5. `HTML` 리스트 내에서 항목 선택 시 값이 들어가도록 합니다.
     
> 게시글 작성
  1. `JS` 작성 페이지에서 "작성" 버튼 클릭 시, 사용자 입력 값을 받아와서 데이터가 비어 있는지 검사합니다.
  2. `JS` 비어 있는 값이 존재하지 않는다면 ajax를 이용하여 controller와 통신합니다. 이번 통신에서는 학원 테이블에 입력받은 학원 명이 존재하는지 여부를 확인합니다.
  3. `JS` 모든 입력 사항이 글자수 범위를 초과하지 않고, 비어 있지 않으며, 학원 명 또한 적절히 입력되었다면 POST 방식으로 데이터가 전송됩니다.
  4. `Java` 넘어온 정보를 적절하게 포매팅합니다. (날짜 -> 규격에 맞게 / 전공·비전공 여부 등은 체크 여부에 일치하게끔)
  5. `Java` 정제된 모든 정보를 게시글 DTO에 담고, service - dao를 통해 DB에 insert하도록 합니다.

> 게시글 정렬
  1. `HTML` 사용자가 페이지 혹은 드롭다운 내의 정렬 기준을 클릭하면 GET 방식으로 통신하게 됩니다.
  2. `Java` 글 목록을 조회하는 controller는 정렬 기준과 현재 페이지 번호를 항상 수신합니다. 만약 존재하지 않는다면 각각 "최신순"과 "1"로 설정되도록 합니다.
  3. `Java` 정렬 기준과 현재 페이지 번호를 가지고 service - dao를 통해 DB에서 검색하고, 목록을 반환하도록 합니다. 이때 페이지 번호는 PreparedStatement를 통해 쿼리 안에 삽입하고, 정렬 기준은 조건문을 통해 다른 쿼리를 전송하도록 하였습니다.

> 모든 view page의 레이아웃 구성 및 css 작업   

----
#### [회원 관련] `서수현`  
> 회원가입
  1. `html` 사용자가 input 창에 기입한 정보들을 받아서 js 함수와 연결시켜줍니다.
  2. `js` 이메일확인, 이메일인증확인,닉네임 확인 버튼이 눌렸는지 확인하는 변수를 만들어 반드시 모두 확인한 후에 가입이 가능하도록 합니다.
  3. 입력한 정보들이 정규식에 맞는지 확인하여 알림메시지를 띄우고, 맞지 않는데 가입 시도 시 alert 창을 띄워 return 시킵니다.
  4. ajax를 통해 서버와 통신하여 입력한 정보들이 DB와 중복되는 지 확인합니다.
  5. `JAVA` 이메일 인증시 Gmail SMTP를 사용하여 이메일을 SHA256 암호화 처리한 코드를 포함한 링크를 전송하고, 사용자가 링크를 눌러서 인증을 하도록 합니다.
  6. 클라이언트에서 확인한 정보들이 서버에도 실제로 유효한지 한 번 더 검사합니다.
   
> 로그인
  1. `js` 아이디나 비밀번호를 입력했는지 확인하고 입력 안 한 항목이 있다면 alert 창을 띄우고 return 합니다.
  2. `JAVA` 아이디와 비밀번호가 일치하는지 DB에서 확인하고 일치하지 않는다면 script로 alert 창을 띄우고 return, 일치한다면 세션에 로그인 정보를 저장한 뒤 메인페이지로 이동시킵니다.  

> 마이페이지
  1. `js` 닉네임, 프로필 사진은 변수가 변경 되었는지 확인하여 기존과 같아도 그대로 저장이 가능합니다.
  2. `html` file input에서 accept로 파일 확장자를 제한합니다.
  3. `java`  파일경로에서 확장자 부분 잘라서 한 번 더 확인합니다. DiskFileItemFactory로 파일 정보 확인 후 FileItem 객체 만들어 업로드 처리합니다.

----
#### [학원 찾기 기능 관련] `이형주`  


