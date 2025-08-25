
# Newsfeed project

# ** 팀 소개 & 역할**

### 팀 명 : 칠성사이다 [제로]

- 🧑 백도현 (팀장) : Follow CRUD, 팔로우 기반 post 조회 기능
- 🧑 유운선 (팀원) : User CRUD, 비밀번호 암호화, 인증/인가
- 🧑 이관영 (팀원) : Like CRUD
- 🧑 윤석호 (팀원) : Comment CRUD
- 🧑 장태욱 (팀원) : Post CRUD, 예외 처리

# 📄 Concept (contents)

### 칠성사이다[제로]를 마신 사람들이 후기를 나눌 수 있는 공간을 구현

## 📄구현 기능

### **회원 관리**

- **회원 가입**
- **로그인 & 로그아웃**
- **회원 조회**
- **회원 수정**
- **비밀번호 수정**
- **회원 삭제**

### 게시물 관리

- **게시물 작성**
- **게시물 조회**
- **게시물 수정**
- **게시물 삭제**
- Follow 기반 게시물 조회

### **댓글 관리**

- **댓글 작성**
- **댓글 조회**
- **댓글 수정**
- **댓글 삭제**

### **친구 관리**

- **FOLLOW 추가**
- **FOLLOWER 조회**

- 나를 팔로우 하는 사람들 조회

- **FOLLOWING 조회**

-내가 팔로우 하는 사람들을 조회

- **FOLLOW 삭제**

### **좋아요 관리**

- **게시물 좋아요 추가**
- **게시물 좋아요 삭제**

### 공통 사항

- **전역 예외 처리기**
- **로그인 필터**
- **비밀번호 인코딩**

# API 명세서
**Base URL: http://localhost:8080**



| 기능       | Method | URL                         | 요청 Body 예시 | 응답 Body 예시 |
|------------|--------|-----------------------------|----------------|----------------|
| 회원가입   | POST   | `localhost:8080/auth/signup` | ```json<br>{ "username": "김아무개", "email": "user@example2.com", "password": "Password123!" }``` | ```json<br>{ "id": 1, "username": "김아무개", "email": "user@example2.com" }``` |
| 로그인     | POST   | `localhost:8080/auth/login`  | ```json<br>{ "email": "user@example.com", "password": "Password123!" }``` | ```json<br>{ "status": 404, "error": "Not Found", "message": "유저가 존재하지 않습니다.", "timestamp": "2025-08-25T10:22:57.7273453" }``` |
| 로그아웃   | POST   | `localhost:8080/auth/logout` | ```json<br>{ "refreshToken": "dGhpc2lzbXlyZWZyZXNodG9rZW4..." }``` | ```json<br>{ "message": "로그아웃 성공" }``` |