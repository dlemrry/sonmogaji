# 📌 **Git 컨벤션**

### **기본**

- init 제외하고 **git add . 금지! (수정한 소스 파일만 add 해주세요)
- master,dev branch 부터는 CI/CD(Jenkins) 연결 후 자동 빌드

### **브랜치 양식**

- git flow에서 제공하는대로
    - master branch→ `main`
    - development branch→ `dev`
    - release → `release/`
    - hotfix → `hotfix/`
    - bugfix → `bugfix/`
    - feature → `feature/`
- feature branch
    - 프론트엔드의 경우 `FE/` 백엔드의 경우 `BE/`를 쓰고 `기능이름`
        
        ex) `git flow feature start BE/login`
        
- release branch
    
    ex) `git flow release start 1.0.0`
    
    - 설명 및 예제
        
        `<major>.<minor>.<etc>`
        
        **major** : 호환이 안되는 변경, Framework 변경, 함수 삭제, 이름 변경 등의 커다란 변경사항을 말합니다. 구조 자체가 변화
        
        **minor** : 호환이 가능한 변경, 기능 추가, 컴포넌트 추가, 클래스 추가, 함수 추가 등, 변경사항이 이전의 버전에서 추가되는 것을 뜻합니다.
        
        **etc** : 버그 수정, 약간의 디자인 변경, 사소한 변동사항 등을 뜻합니다.
        
        **예제**
        
        1.0.0 : 버전 1 릴리즈 되고 버그 수정 등이 한번도 안된 초기버전(그나마 1인 것은 정식 배포가 이뤄진 것을 뜻합니다.)
        
        1.0.3 : 버전 1 릴리즈 되고 버그 수정 등이 3번 있었던 버전
        
        1.3.0 : 버전 1에, 새로운 기능 추가, 1.0, 1.1, 1.2 버전과 호환이 가능합니다. 한번도 버그 수정이 없었네요.
        
        2.1.11: 버전 2 릴리즈 되고 버그 수정 등이 11번 있었고, 2.0 과 호환이 가능한 버전입니다.
        
- branch naming
    - / 하위 브랜치로
        - dev/frontend/기능명(login ……. )
        - dev/backend/기능명(login ……. )

### **커밋 양식**

- feature
    - [#지라이슈번호][BE/FE] feat: <메시지>
        
        ex) `[#S07P12A5-1][FE] feat: add login form`
        
- fix
    - [#지라이슈번호][BE/FE] fix: <메시>
- refactor
    - [#지라이슈번호][BE/FE] refactor: <수정한 파일이름>
        
        ex) `[#2][FE] refactor: HelloWorld.vue`
        
- Markdown, Image 등 문서를 생성 혹은 수정한 경우
    - docs: <메시지>
- 중괄호, 세미콜론 위치 등의 간단한 변경(style guide)
    - style: <수정한 파일이름>
- 테스트를 추가, 변경하는 경우
    - test: <수정한 파일이름>
- 기타 모든 잡무: 설정파일(package.json, application.json 등)을 변경한 경우
    - chore: <수정한 파일이름>
- 만약 위에 해당하는게 없다고 생각되면 아래 표를 참고해서 커밋할 것
    - 태그이름: <메시지> 혹은 <수정한 파일이름>
        
        ![image](/uploads/2fee80fdd3cab6a1c657ac1c3d4f23c1/image.png)
        

# 📌 **JIRA 태스크 컨벤션**

- **에픽**
    - 기획
    - 설계
    - 개발(FE): `<기능이름>`
    - 개발(BE): `<기능이름>`
    

# 📌 Style Guide
<aside>
💡 Git, Jira 컨벤션 - 나머지는 공식문서 style guide를 따릅니다

</aside>

[Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

[Style Guide | Vue.js](https://vuejs.org/style-guide/)
