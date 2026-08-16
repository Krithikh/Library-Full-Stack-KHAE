$ErrorActionPreference='Stop'
$root=(git rev-parse --show-toplevel).Trim(); Set-Location $root
$plan=Import-Csv 'scripts/git/track-plan.csv'
if ($plan.Count -ne 60) { throw "Expected 60 tracks, found $($plan.Count)." }
$codes=$plan.serviceCode | Sort-Object -Unique
if ($codes.Count -ne 60) { throw 'Service codes are not unique.' }
$expected=1..60 | ForEach-Object { '{0:D2}' -f $_ }
if ((Compare-Object $expected $codes)) { throw 'Service codes must run from 01 through 60.' }
$required=@(
  'BASELINE_README.md',
  'backend/backend.lib.mgmt/src/main/resources/db/migration/V001__create_library_training_schema.sql',
  'backend/backend.lib.mgmt/src/main/resources/db/migration/V002__seed_training_data.sql',
  'frontend/frontend.lib.mgmt/vite.config.js'
)
foreach($f in $required){ if(-not (Test-Path $f)){ throw "Missing required baseline file: $f" } }
Write-Host 'Baseline synchronization checks passed.' -ForegroundColor Green
