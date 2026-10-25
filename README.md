# OMNIBUS API 포트폴리오 이력 PoC

이 저장소는 비공개 팀 프로젝트에서 공개 승인을 받은 일부 코드만 분리하면서도
원래 Git 커밋의 작성자, 작성일, 커밋 메시지와 변경 이력을 보존할 수 있음을
보여주는 포트폴리오용 PoC입니다.

실제 운영 저장소와는 연결되어 있지 않으며 배포 기능도 포함하지 않습니다.

## 공개 범위

- `KstTime`: JVM 기본 시간대와 무관하게 KST 형식을 일관되게 만드는 유틸리티
- `HangulGenerator`: 동일한 시간대 기준을 사용하는 한글 표시 날짜 변환기
- `KstTimeTest`: 실행 환경의 시간대가 달라도 결과가 바뀌지 않는지 검증하는 테스트
- 공개 범위와 연결된 원본 Git 커밋 이력
- 저장소 소유자가 확인한 기여도 요약과 대표 작업 기록

## 제외한 범위

다음 항목은 공개하지 않았습니다.

- 운영 배포 설정과 인프라 정의
- 실제 애플리케이션 설정 및 자격증명
- 비공개 PR·Issue의 본문, 댓글, 리뷰와 첨부물
- 사용자 또는 팀 내부 데이터
- 공개 승인을 받지 않은 소스 모듈과 에셋

## 커밋 이력에 관한 안내

이 저장소는 원본 비공개 저장소에서 허용된 경로만 남기도록 Git 이력을 정제해
만들었습니다. 따라서 커밋 SHA는 원본과 다르지만 다음 정보는 보존했습니다.

- 원래 커밋 작성자 이름과 이메일
- 원래 작성일
- 공개 범위와 관련된 커밋 메시지
- 해당 커밋의 코드 변경 내용

과거 커밋에는 동일한 작성자가 `NCSUAL`, `이석현`, `unknown`이라는 서로 다른
표시 이름으로 기록되어 있습니다. 저장소 소유자는 이 작성자 필드를 임의로
통일하거나 다른 사람의 이름으로 바꾸지 않았습니다.

## 기여도 자료

- [기여도 요약](CONTRIBUTIONS.md)
- [작성자와 담당 영역](CONTRIBUTORS.md)
- [대표 Pull Request](docs/contributions/pull-requests.md)
- [대표 Issue](docs/contributions/issues.md)
- [기능 및 책임 영역 매핑](docs/contributions/feature-map.md)
- [집계 방법과 한계](docs/contributions/methodology.md)
- [KST 시간 일관성 사례 연구](docs/case-studies/kst-time-consistency.md)

기여도 집계는 저장소 소유자가 인증된 GitHub API와 원본 Git 이력을 사용해
생성했습니다. 비공개 대화, 첨부물, 운영 세부 정보 및 미승인 코드는 복사하지
않았습니다.

## 커밋 작성자와 PR 작성자는 다릅니다

Git 커밋의 작성자는 각 커밋의 `Author` 정보로 결정됩니다. 반면 GitHub PR의
작성자는 GitHub에서 실제로 **PR 생성 버튼을 누르거나 `gh pr create`를 실행한
계정**으로 기록됩니다.

현재 [공개 PR #1](https://github.com/aunai-dev/OMNIBUS-API-PORTFOLIO-POC/pull/1)은
정제된 커밋과 diff를 확인하기 위해 저장소 소유자 계정이 만든 구조 검증용 PR입니다.
PR 안의 커밋 작성자는 원래 개발자로 보존되어 있지만, PR 작성자는 저장소
소유자로 표시됩니다.

이탈 개발자의 GitHub 프로필에 공개 PR 작성 이력까지 남기려면 해당 개발자가
자신의 GitHub 계정으로 별도의 포트폴리오 PR을 직접 생성해야 합니다.

### 이탈 개발자 본인 명의로 PR을 만드는 절차

다음 절차는 원본 비공개 저장소에 대한 접근 없이 공개 PoC 저장소의 준비된
브랜치만 사용합니다. 예시는 PowerShell과 GitHub CLI 기준입니다.

1. 이탈 개발자 본인의 GitHub 계정으로 로그인합니다.

   ```powershell
   gh auth status
   ```

   출력에 표시되는 계정이 반드시 이탈 개발자 본인의 계정인지 확인합니다.

2. 공개 PoC 저장소를 본인 계정으로 Fork하고 복제합니다.

   ```powershell
   gh repo fork aunai-dev/OMNIBUS-API-PORTFOLIO-POC --clone
   Set-Location OMNIBUS-API-PORTFOLIO-POC
   ```

   이미 Fork가 있다면 본인의 Fork를 복제한 후 원본 공개 저장소를 `upstream`으로
   등록하면 됩니다.

   ```powershell
   git clone https://github.com/NCSUAL/OMNIBUS-API-PORTFOLIO-POC.git
   Set-Location OMNIBUS-API-PORTFOLIO-POC
   git remote add upstream https://github.com/aunai-dev/OMNIBUS-API-PORTFOLIO-POC.git
   ```

3. 공개 저장소에 준비된 기준 브랜치와 기능 브랜치를 가져옵니다.

   ```powershell
   git fetch upstream portfolio-history-base portfolio-history-feature
   git switch -c portfolio-history-feature-by-ncsual upstream/portfolio-history-feature
   ```

4. 새 브랜치를 본인의 Fork에 올립니다.

   ```powershell
   git push -u origin portfolio-history-feature-by-ncsual
   ```

5. 본인 계정으로 공개 PR을 생성합니다.

   ```powershell
   $GitHubLogin = "NCSUAL"

   gh pr create `
     --repo aunai-dev/OMNIBUS-API-PORTFOLIO-POC `
     --base portfolio-history-base `
     --head "${GitHubLogin}:portfolio-history-feature-by-ncsual" `
     --title "[포트폴리오] KST 시간 응답 일관성 개선" `
     --body "비공개 팀 프로젝트에서 공개 승인을 받은 KST 처리 코드와 관련 커밋 이력을 재구성한 포트폴리오 PR입니다."
   ```

6. 생성된 PR에서 다음 항목을 확인합니다.

   - PR 작성자가 이탈 개발자 본인의 GitHub 계정으로 표시되는지
   - 커밋 목록의 작성자와 작성일이 보존되어 있는지
   - 변경 파일이 승인된 Java 파일 3개로 제한되는지
   - 운영 설정, 비밀정보 및 비공개 문서가 포함되지 않았는지

### 이 절차로 보존되는 것과 보존되지 않는 것

보존되는 항목:

- 공개 PR 작성자가 이탈 개발자 본인이라는 사실
- 정제된 커밋의 원래 작성자와 작성일
- 승인된 코드의 변경 diff
- 공개 저장소에서의 PR 활동 기록

보존되지 않는 항목:

- 원본 비공개 PR 번호
- 원본 PR 생성일과 병합일
- 원본 리뷰어, 승인 및 댓글
- 원본 첨부물과 타임라인 이벤트

새 공개 PR은 과거 비공개 PR을 그대로 이전한 것이 아니라, 승인된 코드와 커밋을
사용해 현재 시점에 새로 만든 포트폴리오 PR입니다. 원본 PR인 것처럼 날짜나
작성자를 소급해서 표시해서는 안 됩니다.

본인 명의 PR이 생성된 후에는 구조 검증용 PR #1을 닫아 혼동을 줄일 수 있습니다.
본인 명의 PR을 먼저 확인한 다음 닫는 것을 권장합니다.

## 로컬 검증

Java 21이 필요합니다.

macOS/Linux/Git Bash:

```bash
./gradlew test
```

Windows PowerShell:

```powershell
.\gradlew.bat test
```

현재 공개 범위에는 네 개의 회귀 테스트가 포함되어 있습니다.

## 라이선스

이 PoC에는 오픈소스 라이선스를 부여하지 않았습니다. 포트폴리오 및 기술 검토
목적으로 공개한 것이며, GitHub 약관이 허용하는 범위를 넘어 재사용하거나
재배포하려면 별도의 라이선스 결정과 저장소 소유자의 승인이 필요합니다.
