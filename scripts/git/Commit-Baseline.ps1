param([switch]$Push)
$ErrorActionPreference='Stop'
$root=(git rev-parse --show-toplevel).Trim(); Set-Location $root
& './scripts/git/Verify-Baseline-Synchronization.ps1'
if ($LASTEXITCODE -ne 0) { throw 'Baseline synchronization validation failed.' }
$files=Get-Content 'scripts/git/baseline-generated-files.txt' | Where-Object { $_ -and (Test-Path $_) }
foreach($f in $files){ git add -- $f }
if (Test-Path 'frontend/frontend.lib.mgmt/src/counter.js') { git rm -- 'frontend/frontend.lib.mgmt/src/counter.js' }
$staged=@(git diff --cached --name-only)
if ($staged.Count -eq 0) { throw 'Nothing staged for baseline commit.' }
Write-Host 'Creating synchronized baseline commit:' -ForegroundColor Cyan
$staged | ForEach-Object { Write-Host "  $_" }
git commit -m 'BASELINE-01: add synchronized application base layer and student foundation'
if ($Push) { git push }
