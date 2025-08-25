
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

# 회원/인증/프로필 API

| 기능              | Method | URL                                | 요청 Body 예시 | 응답 Body 예시 |
|------------------|--------|-----------------------------------|----------------|----------------|
| 회원가입          | POST   | `localhost:8080/auth/signup`       | ```json<br>{ "username": "이관영", "email": "user@example2.com", "password": "Password123!" }``` | ```json<br>{ "id": 1, "username": "이관영", "email": "user@example2.com" }``` |
| 로그인            | POST   | `localhost:8080/auth/login`        | ```json<br>{ "email": "user@example.com", "password": "Password123!" }``` | ```json<br>{ "status": 404, "error": "Not Found", "message": "유저가 존재하지 않습니다.", "timestamp": "2025-08-25T10:22:57.7273453" }``` |
| 로그아웃          | POST   | `localhost:8080/auth/logout`       | ```json<br>{ "refreshToken": "dGhpc2lzbXlyZWZyZXNodG9rZW4..." }``` | ```json<br>{ "message": "로그아웃 성공" }``` |
| 프로필 조회       | GET    | `localhost:8080/users/me/profile`  | - | ```json<br>{ "id": 1, "username": "이관영", "email": "user@example2.com", "createdAt": "2025-08-25T10:22:53.736564", "modifiedAt": "2025-08-25T10:22:53.736564" }``` |
| 프로필 수정       | PATCH  | `localhost:8080/users/me/profile`  | ```json<br>{ "username": "name", "email": "new_email@example.com" }``` | ```json<br>{ "id": 1, "username": "name", "email": "new_email@example.com", "createdAt": "2025-08-25T10:22:53.736564", "modifiedAt": "2025-08-25T10:22:53.736564" }``` |
| 비밀번호 수정     | PATCH  | `localhost:8080/users/me/password` | ```json<br>{ "oldpassword": "Password123!", "newpassword": "Password123@" }``` | ```json<br>{ "message": "비밀번호를 수정하였습니다." }``` |
| 회원 탈퇴         | DELETE | `localhost:8080/users/me`          | ```json<br>{ "email": "new_email@example.com", "password": "Password123@" }``` | ```json<br>{ "message": "회원 탈퇴 성공" }``` |

#  게시글 API

| 기능                   | Method | URL                               | 요청 Body 예시 | 응답 Body 예시 |
|------------------------|--------|-----------------------------------|----------------|----------------|
| 게시글 등록            | POST   | `http://localhost:8080/posts`      | ```json<br>{ "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈" }``` | ```json<br>{ "id": 1, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:40:00.3592654", "modifiedDate": "2025-08-25T10:40:00.3592654", "userId": 3 }``` |
| 게시글 전체 조회        | GET    | `http://localhost:8080/posts`      | - | ```json<br>{ "posts": [ { "id": 1, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:40:00.359265", "modifiedDate": "2025-08-25T10:40:00.359265", "userId": 3 } ], "pageNumber": 0, "totalPages": 1, "totalElements": 1 }``` |
| 게시글 전체 조회 (이전페이지) | GET | `http://localhost:8080/posts?page=1` | - | ```json<br>{ "posts": [ { "id": 1, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:40:00.359265", "modifiedDate": "2025-08-25T10:40:00.359265", "userId": 3 } ], "pageNumber": 1, "totalPages": 2, "totalElements": 11 }``` |
| 게시글 수정 기준 전체 조회 | GET | `http://localhost:8080/posts/array` | - | ```json<br>{ "posts": [ { "id": 11, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:41:25.41148", "modifiedDate": "2025-08-25T10:41:25.41148", "userId": 3 } ] }``` |
| 게시글 상세 조회         | GET    | `http://localhost:8080/posts/2`    | - | ```json<br>{ "id": 2, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:41:15.061576", "modifiedDate": "2025-08-25T10:41:15.061576", "userId": 3 }``` |
| 게시글 기간별 조회       | POST   | `http://localhost:8080/posts/search` | ```json<br>{ "start": "2025-08-19", "end": "2025-08-20" }``` | ```json<br>{ "posts": [ { "id": 11, "title": "칠성사이다[제로] 내돈내산 후기", "contents": "댕 맛있음, 꼭 사먹어 보셈", "createdDate": "2025-08-25T10:41:25.41148", "modifiedDate": "2025-08-25T10:41:25.41148", "userId": 3 } ] }``` |
| 게시글 수정             | PUT    | `http://localhost:8080/posts/2`    | ```json<br>{ "title": "칠성사이다[제로] 꼭 드셔보세요", "contents": "칠성사이다 보다 칠성사이다[제로]가 더 맛있는듯!?" }``` | ```json<br>*** 게시글 수정 완료 ***``` |
| 게시글 삭제             | DELETE | `http://localhost:8080/posts/1`    | - | ```json<br>*** 게시글 삭제 성공 ***``` |

# 댓글(Comment) API

| 기능             | Method | URL                                | 요청 Body 예시 | 응답 Body 예시 |
|-----------------|--------|-----------------------------------|----------------|----------------|
| 댓글 등록        | POST   | `http://localhost:8080/posts/1/comments` | ```json<br>{ "comment": "testComment" }``` | ```json<br>{ "comment": "testComment" }``` |
| 댓글 단건 조회   | GET    | `http://localhost:8080/posts/comments/1` | - | ```json<br>{ "comment": "testComment" }``` |
| 댓글 전체 조회   | GET    | `http://localhost:8080/posts/1/comments` | - | ```json<br>[ { "comment": "testComment" } ]``` |
| 댓글 수정        | PUT    | `http://localhost:8080/posts/comments/1` | ```json<br>{ "comment": "hello world" }``` | ```json<br>{ "comment": "hello world" }``` |
| 댓글 삭제        | DELETE | `http://localhost:8080/posts/comments/1` | - | ```json<br>*** 댓글 삭제 성공 ***``` |

# 팔로우(Follow) API

| 기능       | Method | URL                         | 요청 Body 예시 | 응답 Body 예시 |
|:---|:---|:---|:---|:---|
| 이웃 추가 | `POST` | `localhost:8080/users/1/follows` | - | ```json<br>{<br>"id": 1 <br>}``` |
| 팔로잉 조회 | `GET` | `localhost:8080/users/me/followings` | - | ```json<br>{<br>"followingId": 1,<br>"followingUsername": "example",<br>"followingEmail": "user@example4.com"<br>}``` |
| 팔로워 조회 | `GET` | `localhost:8080/users/me/followers` | - | ```json<br>{<br>"followingId": 1,<br>"followingUsername": "example",<br>"followingEmail": "user@example4.com"<br>}``` |
| 이웃 삭제 | `DELETE` | `localhost:8080/users/1/follows` | - | ```json<br>{<br>"id": 1<br>}``` |

# 좋아요(Like) API

| 기능       | Method | URL                         | 요청 Body 예시 | 응답 Body 예시 |
|:---|:---|:---|:---|:---|
| 게시글 좋아요 추가 | `POST` | `localhost:8080//posts/1/likes` | - | ```json<br>{<br>"likeId": 1,"<br>userId": 1,"<br>postId": 1,<br>"message": "좋아요가 완료되었습니다."<br>"createdDate": "2025-08-20T18:30:00",<br>"modifiedDate": "2025-08-20T18:30:00"<br>}``` |
| 게시글 좋아요 취소 | `DELETE` | `localhost:8080//posts/likes/1` | - | ```json<br>{<br>"likeId": 1,"<br>userId": 1,"<br>postId": 1,<br>"message": "좋아요가 취소되었습니다."<br>"createdDate": null<br>"modifiedDate": "2025-08-20T18:40:00"<br>}``` |
