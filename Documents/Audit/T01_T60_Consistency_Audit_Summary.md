# T01-T60 Consistency Audit Summary

Contract: `LIBRARY-FULLSTACK-V1`

Scope: 60 student tracks, 12 modules, 180 student DOCX guide files, REST/service-code matrix, Presenter list support, Thymeleaf ownership, student STUB implementation boundary, Flyway training data, DAO dependency guards, PostgreSQL/Flyway verification and Vite build.

## Outcome

- Tracks audited: **60 / 60**
- Modules audited: **12 / 12**
- Student guide DOCX files expected / verified: **180 / 180**
- Student service codes: **01-60**, contiguous
- Presenter list codes: **P01-P12**, contiguous
- Tracks recorded as PASS without a track-level correction: **46**
- Tracks recorded as FIXED during the audit: **14**
- REVIEW REQUIRED: **0**
- Student solution leakage into the baseline: **none found**

## REST and lifecycle matrix

| Module | Tracks | Presenter list | State action |
|---|---:|---:|---|
| Department | T01-T05 | P01 | Deactivate |
| Member | T06-T10 | P02 | Deactivate |
| Author | T11-T15 | P03 | Deactivate |
| Category | T16-T20 | P04 | Deactivate |
| Publisher | T21-T25 | P05 | Deactivate |
| Book | T26-T30 | P06 | Deactivate |
| Book Copy | T31-T35 | P07 | Withdraw |
| Membership | T36-T40 | P08 | Deactivate |
| Book Issue | T41-T45 | P09 | Cancel |
| Book Return | T46-T50 | P10 | Void |
| Reservation | T51-T55 | P11 | Cancel |
| Fine | T56-T60 | P12 | Void |

## Corrections made during the audit

### T04 Department successful Deactivate row

The final aggregate seed originally had active Members in every active Department used by the earlier T04 material. That meant the dependency-block scenario was valid, but there was no deterministic active Department available for the successful Deactivate path.

`V008__audit_department_t04_success_row.sql` adds:

- Department ID 5
- code `IT`
- name `Information Technology`
- ACTIVE
- no active Member

T04 now uses Department 5 / IT as the successful Deactivate integration case and Department 1 / CSE as the persisted dependency-block case. The T04 DOCX material is post-processed reproducibly by `postprocess_department_t04_audit_alignment.py`, and `TrainingDataContractTest` locks both rows.

### Author Presenter read ownership

Author detail/edit had started to depend on the student-owned Read service. This was corrected so the Presenter-owned `AuthorViewService` supplies list/detail/edit data while Student Search remains responsible for Search.

### Category Thymeleaf shell

`CategoryPageController` had regressed to List/Detail only and supplied model names that did not match the templates. The complete Presenter shell was restored: List, Search, Create, Read, Update and Deactivate. List/detail/edit use `CategoryViewService`; Search delegates to the student Search service.

### Cross-module final-state training-data alignment

Later module supplements had consumed rows that earlier guides use as successful logical-state-change examples. `V007__audit_training_data_alignment.sql` aligns the aggregate final state without rewriting earlier migrations:

- T09: Member 4 is again a safe Deactivate row.
- T14: Author 4 / `AUT-004` is again an active unused Author.
- T34: Book Copy 4 / `ACC-0004` is again a safe Withdraw row; the supplemental active Issue uses new `ACC-0005`.
- T49: a new independent Return 4 / `RET-0004` is the successful no-Fine Void row because `RET-0003` is legitimately used by `FINE-0003`.

The T49 Book Return guide generator applies the same final-state alignment reproducibly, so the document and Flyway state cannot drift on regeneration.

### T01 approved master comparison

The Git T01 Progressive guide and the accepted 52-page v21 master were compared structurally. All non-media DOCX/OOXML members are identical. The Git copy differs only in optimized embedded PNG media and preserves the 52-page render, so the approved content and layout are retained.

## Deterministic database contract guard

A Presenter-owned `TrainingDataContractTest` protects the database assumptions used by the guides. It verifies:

1. physical-but-non-current rows used by Read exercises;
2. safe and dependency-blocked rows used by state-change exercises, including T04 Department 5 / Department 1;
3. persisted business keys used by Create/Update duplicate exercises.

The test does not implement any student service solution.

## Student solution boundary

Student-owned `service/impl` classes on `Frontend-backend-Baseline` remain STUB/sample implementations only. They do not contain the final Mapper/DAO/PostgreSQL algorithms, validation, duplicate logic or dependency logic. Presenter solution code is generated only in the successive Presenter solution branches.

## Freeze gates

- `Student-Guides` contains all 60 tracks and three DOCX files per track.
- `Frontend-backend-Baseline` remains a successor of `Student-Guides`.
- REST codes, endpoints and lifecycle terminology are consistent.
- Presenter list/detail/edit support is independent of student Read completion.
- Student solution algorithms are absent from the baseline.
- `V007`, `V008` and `TrainingDataContractTest` lock the final aggregate training-data contract.
- Verify Student Baseline run 94 passed the corrected V008 application code, PostgreSQL/Flyway/Spring Boot tests and Vite build before the final guide-lineage rechain.
- Presenter solution verification run 24 subsequently passed while inheriting the corrected frozen baseline.
