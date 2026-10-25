# Feature and Ownership Map

This map connects representative private records to the engineering areas they
evidence. Only the KST example has source code published in this PoC.

| Area | Evidence | Demonstrated responsibility |
|---|---|---|
| Multi-module architecture | PR #317 | Defined executable-service boundaries and grouped domain/storage modules by technology |
| Engineering process | PR #318 | Introduced ADR, planning, validation, and publication gates |
| API time consistency | PR #259, public PR #1 | Centralized KST serialization and added timezone regression tests |
| Notifications | Issues #296, #298, #313; PR #314 | Designed inbox, device-token, preference, message, and FCM delivery behavior |
| Monetization | Issues #315, #322; PR #316 | Designed and implemented server-side reward verification and entitlement consumption |
| Application security | Issues #325, #327; PR #328 | Implemented hashed application-lock credentials and attempt limiting |
| Data integrity | Issue #319 | Diagnosed and corrected account-deletion FK cleanup gaps |
| Production operations | PR #321 | Maintained ECS task logging behavior |

## Evidence levels

- **Public code evidence:** source, tests, commits, and diff are visible in this
  repository.
- **Owner-published metadata:** the organization owner verified the private PR
  or issue record and published a sanitized summary here.
- **Private authoritative record:** details remain available only to authorized
  members of the source repository.
