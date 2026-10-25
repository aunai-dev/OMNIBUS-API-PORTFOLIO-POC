# Collection Methodology

## Scope

This record was produced on 2026-10-25 from the private
`aunai-dev/OMNIBUS-API` repository using an authenticated organization-owner
account.

## GitHub activity counts

Counts were obtained through GitHub's issue and pull-request search API with
the `NCSUAL` login:

- Authored PRs: repository + `type:pr author:ncsual`
- Merged PRs: repository + `type:pr author:ncsual is:merged`
- Reviewed PRs: repository + `type:pr reviewed-by:ncsual`
- Authored issues: repository + `type:issue author:ncsual`
- Assigned issues: repository + `type:issue assignee:ncsual`

Pull requests are represented in GitHub's issue-search index, so `type:issue`
was used to keep issue counts separate from pull requests.

## Commit count

The commit count was measured on the source repository's default-branch history
at commit `2bb3df083b6d1e3fecb8a198433816dafa0f7857`. Commits were matched against
the two author email identities already associated with NCSUAL in the private
history. Merge commits are included; commits reachable only from unmerged
branches are excluded.

Historic display names were not normalized. This is why the public commit graph
may show `NCSUAL`, `이석현`, and `unknown` even when the underlying known author
identity matches.

## Representative records

Representative PR and issue tables contain only titles, dates, state, and
coarse change statistics approved for this portfolio proof of concept. Bodies,
comments, reviews, attachments, user data, secrets, and private links were not
copied.

Change size is contextual evidence, not a quality score. Large rename-heavy
architecture changes can report many changed files without representing the
same effort as an equivalent number of newly implemented files.

## Reproducibility and limits

- The private source repository is the authoritative record.
- External readers cannot independently open private source PRs or issues.
- The organization-owned public repository attests that these figures were
  compared against the private source at the stated snapshot.
- A future update should record a new snapshot date and source commit rather
  than silently replacing these figures.
