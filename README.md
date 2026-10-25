# OMNIBUS API Portfolio History PoC

This repository demonstrates that an approved subset of a private repository can
be published while retaining the original Git author, author date, and relevant
commit history.

## Included scope

- `KstTime`: consistent KST formatting independent of the JVM default timezone
- `HangulGenerator`: Korean display-date formatting using the same timezone
- `KstTimeTest`: regression tests for timezone-independent output

## Excluded scope

The original deployment configuration, infrastructure definitions, application
configuration, credentials, business documents, data, assets, and unrelated
source modules are not included.

## History notice

This is an independently published, path-filtered proof of concept derived from
the private project with authorization from its owner. Commit hashes differ from
the private repository because the history was rewritten to retain only the
approved paths. Original author names, author email addresses, author dates, and
relevant commit messages are retained for attribution.

This repository is not connected to the production service and contains no
deployment workflow.

## Contribution evidence

- [Contribution record](CONTRIBUTIONS.md)
- [Contributor identity and attribution](CONTRIBUTORS.md)
- [Representative pull requests](docs/contributions/pull-requests.md)
- [Representative issues](docs/contributions/issues.md)
- [Feature and ownership map](docs/contributions/feature-map.md)
- [Collection methodology](docs/contributions/methodology.md)
- [KST consistency case study](docs/case-studies/kst-time-consistency.md)

The aggregate record was generated from the private source repository by its
organization owner. Private discussions, attachments, deployment details, and
unapproved source code were not copied.

## Verify locally

Java 21 is required.

```bash
./gradlew test
```

## License

No open-source license is granted by this proof of concept. Publication is for
portfolio and technical-review purposes. A separate license decision is required
before reuse or redistribution beyond the permissions provided by GitHub's terms.
